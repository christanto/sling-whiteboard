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
package org.apache.sling.sample.types.entity.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.sling.api.adapter.AdapterFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.sample.types.entity.Entity;
import org.apache.sling.sample.types.entity.Link;
import org.apache.sling.sample.types.entity.Prop;
import org.apache.sling.sample.types.entity.spi.LinkProvider;
import org.apache.sling.sample.types.entity.spi.PropProvider;
import org.apache.sling.types.spi.ExtensionProviderManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        AdapterFactory.ADAPTABLE_CLASSES + "=org.apache.sling.api.resource.Resource",
        AdapterFactory.ADAPTER_CLASSES + "=org.apache.sling.sample.types.entity.Entity"
    }
)
public class EntityAdapterFactory implements AdapterFactory {

	@Reference
    private volatile Collection<ServiceReference<PropProvider>> propProviders;

	@Reference
    private volatile Collection<ServiceReference<LinkProvider>> linkProviders;

    @Reference
    private ExtensionProviderManager filters;

    private BundleContext bundleContext;

    @Activate
    protected void activate(BundleContext ctx) {
        this.bundleContext = ctx;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    @Nullable
    public <AdapterType> AdapterType getAdapter(@NotNull Object adaptable, @NotNull Class<AdapterType> type) {
        Resource resource = (Resource) adaptable;

        @SuppressWarnings("null")
        @NotNull
        List<@NotNull Prop> props = filters.filter(propProviders, resource)
    		.map(bundleContext::getService)
            .map(PropProvider::getProperties)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        @SuppressWarnings("null")
        @NotNull
        List<@NotNull Link> links = filters.filter(linkProviders, resource)
    		.map(bundleContext::getService)
            .map(LinkProvider::getLinks)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        return (AdapterType) new Entity() {
        	@Override
			@NotNull
        	public Collection<@NotNull Prop> getProperties() {
        		return props;
        	}

			@Override
			@NotNull
			public Collection<@NotNull Link> getLinks() {
				return links;
			}
        };
    }
}
