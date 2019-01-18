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
package org.apache.sling.types.data.spi;

import java.util.Collections;
import java.util.List;

import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.types.Context;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.data.Property;
import org.apache.sling.types.data.validation.ValidationError;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * The SPI to be implemented by OSGi services to do property processing based on
 * the property type.
 *
 * @param <T> the type of the property
 * @param <V> the type of the value of the property
 *
 * @since 1.0
 */
@ConsumerType
public interface PropertyHandler<T extends Property<V>, V> {

	/**
	 * The OSGi service property name to indicate the property type
	 * ({@link Property#getType()}) that this handler is applicable to handle.
	 */
	String PROPERTY_TYPE = "sling.type";

	/**
	 * Returns the value of the given property from the persistence layer.
	 *
	 * @param ctx      the context of the processing
	 * @param property the property to retrieve its value
	 * @return the array of values. If the property is a single value property,
	 *         array of one element is returned.
	 * @throws TypeException when error occurs related to type
	 */
	@NotNull
	V[] getValue(@NotNull Context<Resource> ctx, @NotNull T property) throws TypeException;

	/**
	 * Persists the value of the given property to the persistence layer.
	 * <p>
	 * This method is called after successful call to
	 * {@link #validate(Context, Property, RequestParameter...)}.
	 * <p>
	 *
	 * @param ctx      the context of the processing
	 * @param property the property to persist its value
	 * @param params   the new values to persist
	 * @throws TypeException when error occurs related to type
	 */
	void setValue(@NotNull Context<Resource> ctx, @NotNull T property, RequestParameter... params) throws TypeException;

	/**
	 * Validates the new values of the given property before persisting by calling
	 * {@link #setValue(Context, Property, RequestParameter...)}.
	 *
	 * @param ctx      the context of the processing
	 * @param property the property to persist its value
	 * @param params   the new values to persist
	 * @return the list of errors. Empty list is returned when validation is
	 *         successful.
	 * @throws TypeException when error occurs related to type
	 */
	@SuppressWarnings("null")
	@NotNull
	default List<@NotNull ValidationError<T, V>> validate(@NotNull Context<Resource> ctx, @NotNull T property,
			RequestParameter... params) throws TypeException {
		return Collections.emptyList();
	}
}
