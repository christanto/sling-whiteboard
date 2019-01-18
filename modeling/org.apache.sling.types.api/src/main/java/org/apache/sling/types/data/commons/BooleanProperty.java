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

import java.util.Optional;

import org.apache.sling.types.attributes.annotations.Attribute;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;

/**
 * The property to represent boolean.
 *
 * @since 1.0
 */
@ProviderType
public interface BooleanProperty extends WritableProperty<BooleanProperty, Boolean> {

	/**
	 * The property type name.
	 */
	@NotNull
	String TYPE = "sling:boolean";

	/**
	 * Returns the value to persist to the persistence layer when this property is
	 * {@code true}.
	 * <p>
	 * For example, if this method returns {@code on} and the property value is
	 * {@code true} then {@code on} will be saved instead of boolean {@code true}.
	 * </p>
	 * <p>
	 * It is a similar mechanic to how <a href=
	 * "https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox">HTML
	 * checkbox</a> works.
	 * <p>
	 *
	 * @return the string to persist when this property is {@code true}
	 */
	@Attribute
	@NotNull
	Optional<String> getCheckedValue();

	/**
	 * The setter of {@link #getCheckedValue()}.
	 *
	 * @param checkedValue the string to persist when this property is {@code true}
	 * @return {@code this} instance for chaining
	 */
	@NotNull
	BooleanProperty withCheckedValue(@NotNull String checkedValue);
}
