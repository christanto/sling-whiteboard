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
 * The service interface to instantiate properties.
 *
 * @since 1.0
 */
@ProviderType
public interface Properties {

	/**
	 * Returns a new builder of {@link TextProperty}.
	 *
	 * @param id   the {@link Property#getId()} of the property
	 * @param name the {@link Property#getName()} of the property
	 * @return the new instance
	 */
	TextProperty.Builder text(@NotNull String id, @NotNull String name);

	/**
	 * Returns a new builder of {@link TextareaProperty}.
	 *
	 * @param id   the {@link Property#getId()} of the property
	 * @param name the {@link Property#getName()} of the property
	 * @return the new instance
	 */
	TextareaProperty.Builder textarea(@NotNull String id, @NotNull String name);

	/**
	 * Returns a new builder of {@link BooleanProperty}.
	 *
	 * @param id   the {@link Property#getId()} of the property
	 * @param name the {@link Property#getName()} of the property
	 * @return the new instance
	 */
	BooleanProperty.Builder bool(@NotNull String id, @NotNull String name);

	/**
	 * Returns a new builder of {@link DateProperty}.
	 *
	 * @param id   the {@link Property#getId()} of the property
	 * @param name the {@link Property#getName()} of the property
	 * @return the new instance
	 */
	DateProperty.Builder date(@NotNull String id, @NotNull String name);
}
