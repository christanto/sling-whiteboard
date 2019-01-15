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
package org.apache.sling.types;

import java.util.Collection;
import java.util.Optional;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.types.data.DataType;
import org.apache.sling.types.spi.ExtensionProvider;
import org.apache.sling.types.spi.TypeProvider;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * The entry point to inspect the type system of a particular {@link Adaptable}.
 * <p>
 * In order to get the instance of {@link TypeSystem}, simply perform an
 * {@link Adaptable#adaptTo(Class)} against this interface:
 * {@code adaptable.adaptTo(TypeSystem.class)}
 * </p>
 *
 * <h2>Creating a New Type</h2>
 *
 * <h3>Motivation to Create Types</h3>
 *
 * <p>
 * It is up to one's imagination on what type should be created. Most likely the
 * typing effort done in a project would be driven by business requirements in
 * that project. It can also cover many different aspects and layers available
 * on the system.
 * </p>
 * <p>
 * For instance, {@link DataType} is a type from the perspective of data, and
 * most likely it is defined and driven by the lower level, data-oriented
 * developers. Contrast this with a type dealing with the representation of the
 * resources as cards, which is pretty high level and most likely defined and
 * driven by UI developers.
 * </p>
 *
 * <h3>Technical Requirements</h3>
 *
 * <h4>Type Owners</h4>
 * <p>
 * To create a new type, the owners need to satisfy the following requirements:
 * </p>
 * <ol>
 * <li>Create a type interface extending {@link Type}.</li>
 * <li>Provide an adaptation from the target {@link Adaptable} to the created
 * type interface.</li>
 * <li>Optionally create SPI interfaces extending {@link ExtensionProvider} to
 * allow the adaptable owners to participate in the type.</li>
 * </ol>
 *
 * <h4>Adaptable Owners</h4>
 * <p>
 * To use a new type, the adaptable owners need to satisfy the following
 * requirements:
 * </p>
 * <ol>
 * <li>Implement and associate a {@link TypeProvider}—returning the created type
 * interface—with hooking mechanic of choice. e.g. based on the resource
 * type.</li>
 * <li>Implement and associate the type's SPI interfaces to participate in the
 * type.</li>
 * </ol>
 *
 * @since 1.0
 */
@ConsumerType
public interface TypeSystem {

	/**
	 * Returns the available types of the current adaptable.
	 *
	 * <p>
	 * For each type returned, the current adaptable can be adapted using
	 * {@link Adaptable#adaptTo(Class)} against the type.
	 * </p>
	 *
	 * @return the collection of types
	 */
	@NotNull
	Collection<@NotNull Class<? extends Type>> getAvailableTypes();

	/**
	 * Returns the type instance as the result of the adaptation of the current
	 * adaptable against the given typeClass.
	 *
	 * <p>
	 * When passing the {@code typeClass} parameter taken from
	 * {@link #getAvailableTypes()}, this method SHOULD return non-empty optional.
	 * </p>
	 *
	 * @param <T> the type whose instance to get
	 * @param typeClass the type to be adapted against
	 * @return A non-empty optional when the adaptation is successful, empty
	 *         optional otherwise
	 */
	@NotNull
	<T extends Type> Optional<T> getType(@NotNull Class<T> typeClass);
}
