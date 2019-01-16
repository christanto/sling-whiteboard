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
import org.osgi.annotation.versioning.ProviderType;

/**
 * The definition of an attribute.
 *
 * @since 1.0
 */
@ProviderType
public interface AttributeDefinition {

	/**
	 * Returns the name of the attribute.
	 *
	 * @return the name of the attribute
	 */
	@NotNull
	String getName();

	/**
	 * Returns the type of the attribute.
	 * <p>
	 * Contrast this with {@link #getTypeClass()}, this type is used to represent
	 * the semantic type of the attribute, such as "string", "boolean", and "json",
	 * while {@link #getTypeClass()} is used to represent the type as Java class.
	 * </p>
	 * <p>
	 * For example, the semantic type can be "json", while the type class is
	 * {@link String}, which means that the JSON is represented as String during
	 * runtime.
	 * </p>
	 *
	 * @return the type of the attribute
	 */
	@NotNull
	String getType();

	/**
	 * Returns the class of the attribute.
	 * <p>
	 * The value returned by this method is designed to work with the type parameter
	 * of {@link Attributes#get(String, Class)}.
	 * </p>
	 *
	 * @return the class of the attribute
	 */
	@NotNull
	Class<?> getTypeClass();

	/**
	 * Checks if the attribute supports multiple values.
	 *
	 * @return {@code true} if the attribute supports multiple values, {@code false}
	 *         otherwise
	 */
	boolean isMultiple();

	/**
	 * Checks if the attribute is required to be present.
	 * <p>
	 * If an attribute is required, when {@link Attributes#containsName(String)} is
	 * called with name of the attribute, it will return {@code true}.
	 * </p>
	 *
	 * @return {@code true} if the attribute is required, {@code false} otherwise
	 */
	boolean isRequired();
}
