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
package org.apache.sling.types.spi;

import java.util.Collection;
import java.util.stream.Stream;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.types.Type;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.ServiceReference;

/**
 * The service interface acting as the aggregator of
 * {@link ExtensionProviderFilter}.
 *
 * <p>
 * The {@link Type} implementations may use this service to filter the
 * {@link ExtensionProvider} if they are applicable to a particular
 * {@link Adaptable}.
 * </p>
 *
 * @since 1.0
 */
@ProviderType
public interface ExtensionProviderManager {

	/**
	 * Filters the given providers if they are applicable for the given resource.
	 *
	 * @param           <T> the type of {@link ExtensionProvider} to filter
	 * @param refs      the service references of the extension providers to filter
	 * @param adaptable the adaptable to filter against
	 * @return the stream of all the service references that are applicable to the
	 *         given adaptable
	 */
	@NotNull
	<T extends ExtensionProvider> Stream<ServiceReference<T>> filter(@NotNull Collection<ServiceReference<T>> refs,
			@NotNull Adaptable adaptable);
}
