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
package org.apache.sling.types.attributes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a set of attributes, where an attribute is simply a key-value pair.
 *
 * @since 1.0
 */
@ProviderType
public interface Attributes {

	/**
	 * Returns the value of the attribute with the given name. The value is
	 * converted into the given type.
	 *
	 * @param      <T> the type to convert the value into
	 * @param name the name of the attribute to get
	 * @param type the class of the type to convert the value into
	 * @return the value converted into the given type. Return {@code null} if the
	 *         attribute is not found.
	 */
	@Nullable
	<T> T get(@NotNull String name, @NotNull Class<T> type);

	/**
	 * Returns the value of the attribute with the given name. If the value is null
	 * or not found then the given defaultValue is returned, otherwise the value is
	 * converted into the type of the default value.
	 *
	 * @param              <T> the type of the default value
	 * @param name         the name of the attribute to get
	 * @param defaultValue the default value to return when the attribute is not
	 *                     found
	 * @return the value converted into the type of the given default value. Return
	 *         the given defaultValue if the attribute is not found.
	 */
	@NotNull
	<T> T get(@NotNull String name, @NotNull T defaultValue);

	/**
	 * Returns {@code true} when there is an attribute with the given name;
	 * {@code false} otherwise.
	 *
	 * @param name the name of the attribute to check
	 * @return {@code true} when there is an attribute with the given name,
	 *         {@code false} otherwise
	 */
	boolean containsName(@NotNull String name);

	/**
	 * Returns the definitions of these attributes.
	 *
	 * @return the definitions of these attributes
	 */
	@NotNull
	AttributeDefinitions getDefinitions();
}
