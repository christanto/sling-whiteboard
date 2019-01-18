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

import java.util.List;

import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.types.Context;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.data.Property;
import org.apache.sling.types.data.spi.PropertyHandler;
import org.apache.sling.types.data.validation.ValidationError;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;

/**
 * The service interface to do property processing.
 * <p>
 * To participate of the processing, one needs to register
 * {@link PropertyHandler}.
 * </p>
 *
 * @since 1.0
 */
@ProviderType
public interface PropertyService {

	/**
	 * Returns the value of the given property from the persistence layer.
	 *
	 * @param      <V> the type of the value of the property
	 * @param ctx  the context of the processing
	 * @param prop the property to retrieve its value
	 * @return the array of values. If the property is a single value property,
	 *         array of one element is returned.
	 * @throws TypeException when error occurs related to type
	 */
	@NotNull
	<V> V[] getValue(@NotNull Context<Resource> ctx, @NotNull Property<V> prop) throws TypeException;

	/**
	 * Persists the value of the given property to the persistence layer.
	 * <p>
	 * This method will perform validation process first before persisting.
	 * <p>
	 *
	 * @param        <T> the type of the property
	 * @param        <V> the type of the value of the property
	 * @param ctx    the context of the processing
	 * @param prop   the property to persist its value
	 * @param params the new values to persist
	 * @return the list of errors. Empty list is returned when validation is
	 *         successful.
	 * @throws TypeException when error occurs related to type
	 */
	@NotNull
	<T extends Property<V>, V> List<@NotNull ValidationError<T, V>> setValue(@NotNull Context<Resource> ctx,
			@NotNull T prop, RequestParameter... params) throws TypeException;
}
