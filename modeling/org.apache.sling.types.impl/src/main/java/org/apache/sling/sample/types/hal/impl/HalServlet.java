/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.sample.types.hal.impl;

import static de.otto.edison.hal.Link.linkBuilder;
import static de.otto.edison.hal.Links.linkingTo;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_EXTENSIONS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_SELECTORS;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.jackrabbit.util.Text;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.sample.types.entity.Entity;
import org.apache.sling.sample.types.entity.Link.Href;
import org.apache.sling.sample.types.entity.Prop;
import org.apache.sling.sample.types.entity.commons.EntityService;
import org.apache.sling.types.Context;
import org.apache.sling.types.TypeSystem;
import org.apache.sling.types.data.commons.PropertyService;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.otto.edison.hal.HalRepresentation;
import de.otto.edison.hal.Links;
import de.otto.edison.hal.Links.Builder;

/**
 * The servlet to expose HAL response driven by {@link Entity}.
 *
 * @see https://tools.ietf.org/html/draft-kelly-json-hal-08
 */
@Component(
    service = Servlet.class,
    property = {
        SLING_SERVLET_RESOURCE_TYPES + "=sling/servlet/default",
        SLING_SERVLET_SELECTORS + "=hal",
        SLING_SERVLET_EXTENSIONS + "=json"
    }
)
public class HalServlet extends SlingSafeMethodsServlet {
	private static final long serialVersionUID = 3758035004857738715L;

	private static final Logger log = LoggerFactory.getLogger(HalServlet.class);

	@Reference
	private EntityService entityService;

	@Reference
	private PropertyService propertyService;

	private ObjectMapper objectMapper;

	@Activate
	protected void activate() {
		objectMapper = new ObjectMapper();
	}

	@Override
    protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response)
            throws ServletException, IOException {
        Resource content = request.getResource();

        TypeSystem typeSystem = content.adaptTo(TypeSystem.class);

        if (typeSystem == null) {
            log.warn("Resource is not adaptable to TypeSystem: {}", content.getPath());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Entity entity = content.adaptTo(Entity.class);

        if (entity == null) {
            log.warn("Resource is not adaptable to Entity: {}", content.getPath());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/hal+json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        @SuppressWarnings("null")
		Context<Resource> ctx = new ContextImpl(content, typeSystem, request.getLocale());

		objectMapper.writeValue(response.getWriter(), new EntityRepresentation(ctx, entity, request));
    }

	private class EntityRepresentation extends HalRepresentation {
		@NotNull
	    private Context<Resource> ctx;

		@NotNull
		private Entity entity;

		@NotNull
		private SlingHttpServletRequest request;

		public EntityRepresentation(@NotNull Context<Resource> ctx, @NotNull Entity entity,
				@NotNull SlingHttpServletRequest request) {
			this.ctx = ctx;
			this.entity = entity;
			this.request = request;

			add(generateLinks());
		}

		private Links generateLinks() {
			Builder links = linkingTo()
				.self(resolvePath(ctx.getAdaptable().getPath()));

			entity.getLinks().stream().forEach(l -> {
				for (Href href : l.getHref(ctx.getAdaptable())) {
					de.otto.edison.hal.Link.Builder link = linkBuilder(l.getRel(), resolveHref(href));
					l.getDeprecation().map(this::resolveHref).ifPresent(link::withDeprecation);
					l.getHrefLang().ifPresent(link::withHrefLang);
					l.getName().ifPresent(link::withName);
					l.getProfile().ifPresent(link::withProfile);
					l.getTitle().ifPresent(link::withTitle);
					l.getType().ifPresent(link::withType);

					if (l.isMultipleHref()) {
						links.array(link.build());
					} else {
						links.single(link.build());
					}
				}
			});

			return links.build();
		}

		@NotNull
		private String resolvePath(@NotNull String path) {
			String path1 = path;
			if (!path.startsWith("/")) {
				path1 = ResourceUtil.normalize(ctx.getAdaptable().getPath() + "/" + path);
			}
			return resolveHref(Text.escapePath(path1) + ".hal.json");
		}

		@NotNull
		private String resolveHref(@NotNull String href) {
			if (href.startsWith("/")) {
				return request.getContextPath() + href;
			} else {
				return href;
			}
		}

		@SuppressWarnings("null")
		@NotNull
		private String resolveHref(@NotNull Href href) {
			if (href.isPath()) {
				return resolvePath(href.getValue());
			} else {
				return resolveHref(href.getValue());
			}
		}

		@SuppressWarnings("null")
		@JsonAnyGetter
        private Map<String, Object> getProperties() {
            Map<String, Object> map = new LinkedHashMap<>();

        	for (Prop p : entity.getProperties()) {
        		entityService.getProperty(ctx.getTypeSystem(), p)
        			.ifPresent(property -> propertyService.getValue(ctx, property)
    					.ifPresent(v -> map.put(property.getId(), v))
					);
        	}

            return map;
        }
	}
}
