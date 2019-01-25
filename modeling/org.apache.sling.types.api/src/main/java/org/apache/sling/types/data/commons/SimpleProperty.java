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

import java.util.Objects;

import org.apache.sling.types.attributes.Attributes;
import org.apache.sling.types.attributes.commons.AttributesBuilder;
import org.apache.sling.types.attributes.commons.AttributesFactory;
import org.apache.sling.types.data.Property;
import org.jetbrains.annotations.NotNull;

/**
 * The base class to implement {@link Property}.
 *
 * @param <T> the type of the property
 * @param <V> the type of the value of the property
 *
 * @since 1.0
 */
public abstract class SimpleProperty<T extends Property<V>, V> implements WritableProperty<T, V> {

	/**
	 * The attributes of this property.
	 * <p>
	 * All the attributes are stored here.
	 * </p>
	 */
	protected AttributesBuilder<?, T> attrs;

	/**
	 * Instantiates a new property.
	 *
	 * @param attrsFactory the factory to create a new {@link Attributes}
	 * @param id           the {@link Property#getId()}
	 * @param name         the {@link Property#getName()}
	 * @param type         the {@link Property#getType()}
	 */
	@SuppressWarnings("unchecked")
	public SimpleProperty(@NotNull AttributesFactory attrsFactory, @NotNull String id, @NotNull String name,
			@NotNull String type) {
		attrs = (AttributesBuilder<?, T>) attrsFactory.builder(getClass());

		attrs.put("sling:id", id);
		attrs.put("sling:name", name);
		attrs.put("sling:type", type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public T withTitle(@NotNull String title) {
		attrs.put("sling:title", title);
		return getSelf();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public T withRequired(boolean required) {
		attrs.put("sling:required", required);
		return getSelf();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public T withValidations(String... validations) {
		attrs.put("sling:validations", validations);
		return getSelf();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public T withMultiple(boolean multiple) {
		attrs.put("sling:multiple", multiple);
		return getSelf();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public T withReadonly(boolean readonly) {
		attrs.put("sling:readonly", readonly);
		return getSelf();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Attributes getAttributes() {
		return attrs.build();
	}

	/**
	 * Returns {@code this} instance.
	 *
	 * @return {@code this} instance
	 */
	@NotNull
	protected abstract T getSelf();

	@Override
	public boolean equals(Object o) {
		if (o instanceof Property) {
			// TODO Finalize the definition of `equals`.
			return ((Property<?>) o).getId().equals(getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
