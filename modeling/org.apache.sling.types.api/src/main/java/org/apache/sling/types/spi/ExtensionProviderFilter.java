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
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;
import org.osgi.framework.ServiceReference;

/**
 * The SPI to be implemented by OSGi services to filter the list of
 * {@link ExtensionProvider} if they are applicable for a particular
 * {@link Adaptable}.
 *
 * @since 1.0
 */
@ConsumerType
public interface ExtensionProviderFilter {

	/**
	 * The OSGi service property name to indicate the list of fully qualified class
	 * names that this provider is applicable to filter.
	 */
	String PROPERTY_ADAPTABLE_CLASSES = "sling.adaptables";

	/**
	 * Filters the given providers if they are applicable for the given adaptable.
	 *
	 * The adaptable parameter will be one of the types declared at
	 * {@link #PROPERTY_ADAPTABLE_CLASSES}.
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
