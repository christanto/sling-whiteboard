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
package org.apache.sling.types.data.commons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.types.Context;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.data.Property;
import org.apache.sling.types.data.spi.PropertyHandler;
import org.apache.sling.types.data.validation.ValidationError;
import org.apache.sling.types.data.validation.commons.Errors;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The convenience base class for {@link PropertyHandler}.
 *
 * @param <T> the type of the property that this handler can handle
 * @param <V> the type of the value of the property
 *
 * @since 1.0
 */
public abstract class SimplePropertyHandler<T extends Property<V>, V> implements PropertyHandler<T, V> {
	private static final Logger log = LoggerFactory.getLogger(SimplePropertyHandler.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public V[] getValue(@NotNull Context<Resource> ctx, @NotNull T property) throws TypeException {
		String name = property.getName();

		log.debug("Getting value with name: " + name);

		return getValue(property, ctx.getAdaptable().getValueMap());
	}

	/**
	 * Returns the value of the given property from the given value map.
	 *
	 * @param property the property to get its value
	 * @param vm       the value map that stores the values
	 * @return the array of values of the given property
	 * @throws TypeException when error occurs related to type
	 */
	@NotNull
	protected abstract V[] getValue(@NotNull T property, @NotNull ValueMap vm) throws TypeException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(@NotNull Context<Resource> ctx, @NotNull T property, RequestParameter... params)
			throws TypeException {
		String name = property.getName();

		log.debug("Setting value with name: " + name);

		if (property.isReadonly()) {
			return;
		}

		// name must be normalized relative path
		String normalizedName = ResourceUtil.normalize(name);
		if (normalizedName == null) {
			throw new AssertionError("Illegal property name: " + name);
		}
		assert !normalizedName.startsWith("/");
		assert !normalizedName.endsWith("/");

		String relPath = ResourceUtil.getParent(normalizedName);
		String propName = ResourceUtil.getName(normalizedName);

		Resource parent = ctx.getAdaptable();
		if (relPath != null) {
			String parentPath = ctx.getAdaptable().getPath() + "/" + relPath;
			ResourceResolver resolver = ctx.getAdaptable().getResourceResolver();
			try {
				parent = ResourceUtil.getOrCreateResource(resolver, parentPath, (String) null, null, false);
			} catch (PersistenceException e) {
				throw new TypeException("Error occurs creating resource: " + parentPath, e);
			}
		}

		ModifiableValueMap vm = parent.adaptTo(ModifiableValueMap.class);

		if (vm == null) {
			throw new TypeException("The resource is not adaptable to ModifiableValueMap: " + parent.getPath());
		}

		V[] values = convertParams(property, params);

		if (property.isMultiple()) {
			vm.put(propName, values);
		} else {
			vm.put(propName, values[0]);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("null")
	@Override
	@NotNull
	public List<@NotNull ValidationError<T, V>> validate(@NotNull Context<Resource> ctx, @NotNull T property,
			RequestParameter... params) throws TypeException {
		if (property.isReadonly()) {
			return Collections.emptyList();
		}

		if (property.isRequired()) {
			if (params.length == 0 || params[0].getSize() == 0) {
				return Arrays.asList(getErrors().required(property, params));
			}
		}

		return Collections.emptyList();
	}

	/**
	 * Converts from the given request params to the property values in the context
	 * of the given property. These values will be used as the new values of the
	 * property.
	 *
	 * @param property the property that the new values will be set into
	 * @param params   the request params to convert
	 * @return the converted values suitable for the given property
	 * @throws TypeException when error occurs related to type
	 */
	protected abstract V[] convertParams(@NotNull T property, RequestParameter... params) throws TypeException;

	/**
	 * Returns the errors factory.
	 *
	 * @return the instance of {@link Errors}
	 */
	protected abstract Errors getErrors();
}
