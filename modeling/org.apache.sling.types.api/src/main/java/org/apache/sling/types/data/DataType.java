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

import java.util.List;
import java.util.Optional;

import org.apache.sling.types.Type;
import org.apache.sling.types.data.spi.PropertyProvider;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Type that describes a resource from the perspective of data.
 *
 * @since 1.0
 */
@ConsumerType
public interface DataType extends Type {

	/**
	 * Returns the properties of this DataType.
	 * <p>
	 * To participate in returning the properties, one needs to register
	 * {@link PropertyProvider}.
	 * </p>
	 *
	 * @return the list of properties of this DataType
	 */
	@NotNull
	List<@NotNull Property<?>> getProperties();

	/**
	 * Returns the property of the given ID in this DataType.
	 *
	 * @param id the {@link Property#getId()}
	 * @return the property with the given ID
	 */
	@SuppressWarnings("null")
	@NotNull
	default Optional<Property<?>> getPropertyById(@NotNull Optional<String> id) {
		return getProperties().stream().filter(p -> Optional.of(p.getId()).equals(id)).findFirst();
	}
}
