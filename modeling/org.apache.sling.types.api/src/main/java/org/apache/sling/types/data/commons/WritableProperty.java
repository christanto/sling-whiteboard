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

import org.apache.sling.types.data.Property;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;

/**
 * The writable version of {@link Property}.
 *
 * @param <T> the type of the property
 * @param <V> the type of the value of the property
 *
 * @since 1.0
 */
@ProviderType
public interface WritableProperty<T extends Property<V>, V> extends Property<V> {

	/**
	 * Setter of {@link #getTitle()}.
	 *
	 * @param title the title of the property
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	T withTitle(@NotNull String title);

	/**
	 * The setter of {@link #isMultiple()} with {@code true} value.
	 *
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	default T withMultiple() {
		return withMultiple(true);
	}

	/**
	 * Setter of {@link #isMultiple()}.
	 *
	 * @param multiple {@code true} if this property accepts multiple values,
	 *                 {@code false} otherwise
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	T withMultiple(boolean multiple);

	/**
	 * The setter of {@link #isReadonly()} with {@code true} value.
	 *
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	default T withReadonly() {
		return withReadonly(true);
	}

	/**
	 * Setter of {@link #isReadonly()}.
	 *
	 * @param readonly {@code true} to make this property as read-only,
	 *                 {@code false} otherwise
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	T withReadonly(boolean readonly);

	/**
	 * The setter of {@link #isRequired()} with {@code true} value.
	 *
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	default T withRequired() {
		return withRequired(true);
	}

	/**
	 * Setter of {@link #isRequired()}.
	 *
	 * @param required {@code true} to make this property as required, {@code false}
	 *                 otherwise
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	T withRequired(boolean required);

	/**
	 * Setter of {@link #getValidations()}.
	 *
	 * @param validations the array of validation names, such as
	 *                    {@code sling:email}, {@code sling:jcr:name}
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	T withValidations(String... validations);
}
