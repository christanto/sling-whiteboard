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
package org.apache.sling.types.data;

import java.util.Optional;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.types.attributes.AttributesProvider;
import org.apache.sling.types.attributes.annotations.Attribute;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Represents a property in a {@link DataType}.
 *
 * @param <V> the type of the value of the property
 *
 * @since 1.0
 */
@ConsumerType
public interface Property<V> extends AttributesProvider {

	/**
	 * Returns the ID of the property.
	 * <p>
	 * It needs to be unique within a DataType instance.
	 * </p>
	 *
	 * @return the ID of the property
	 */
	@Attribute("sling:id")
	@NotNull
	default String getId() {
		String id = getAttributes().get("sling:id", String.class);

		if (id != null) {
			return id;
		} else {
			throw new IllegalStateException("The required `sling:id` attribute is not found");
		}
	}

	/**
	 * Returns the name of the property.
	 * <p>
	 * It doesn't have a specific semantic meaning within the DataType. It is mainly
	 * used to indicate the name or path of the property of the underlying
	 * persistence object, such as {@link Resource}).
	 * </p>
	 * <p>
	 * For example, name = {@code info/title} can be used to indicate that it points
	 * to Resource's property at relative path = {@code info/title}.
	 * </p>
	 *
	 * @return the name of the property
	 */
	@Attribute("sling:name")
	@NotNull
	default String getName() {
		String name = getAttributes().get("sling:name", String.class);

		if (name != null) {
			return name;
		} else {
			throw new IllegalStateException("The required `sling:name` attribute is not found");
		}
	}

	/**
	 * Returns the type of the property.
	 *
	 * @return the type of the property
	 */
	@Attribute("sling:type")
	@NotNull
	default String getType() {
		String type = getAttributes().get("sling:type", String.class);

		if (type != null) {
			return type;
		} else {
			throw new IllegalStateException("The required `sling:type` attribute is not found");
		}
	}

	/**
	 * Returns the human-friendly title describing this property.
	 *
	 * @return the title of property
	 */
	@SuppressWarnings("null")
	@Attribute("sling:title")
	@NotNull
	default Optional<String> getTitle() {
		return Optional.<String>ofNullable(getAttributes().get("sling:title", String.class));
	}

	/**
	 * Checks if this property accepts multiple values.
	 *
	 * @return {@code true} if this property accepts multiple values, {@code false}
	 *         otherwise
	 */
	@Attribute("sling:multiple")
	default boolean isMultiple() {
		return getAttributes().get("sling:multiple", false);
	}

	/**
	 * Checks if this property is read-only.
	 *
	 * @return {@code true} if this property is read-only, {@code false} otherwise
	 */
	@Attribute("sling:readonly")
	default boolean isReadonly() {
		return getAttributes().get("sling:readonly", false);
	}

	/**
	 * Checks if this property is required to have value.
	 *
	 * @return {@code true} if this property is required, {@code false} otherwise
	 */
	@Attribute("sling:required")
	default boolean isRequired() {
		return getAttributes().get("sling:required", false);
	}

	/**
	 * Returns the validation names that validate this property.
	 *
	 * @return the array of validation names, such as {@code sling:email},
	 *         {@code sling:jcr:name}
	 */
	@SuppressWarnings("null")
	@Attribute("sling:validations")
	@NotNull
	default String[] getValidations() {
		return getAttributes().get("sling:validations", new String[0]);
	}
}
