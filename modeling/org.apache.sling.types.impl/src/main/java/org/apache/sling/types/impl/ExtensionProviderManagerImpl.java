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
package org.apache.sling.types.impl;

import static org.apache.sling.types.spi.ExtensionProviderFilter.PROPERTY_ADAPTABLE_CLASSES;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.spi.ExtensionProvider;
import org.apache.sling.types.spi.ExtensionProviderFilter;
import org.apache.sling.types.spi.ExtensionProviderManager;
import org.jetbrains.annotations.NotNull;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class ExtensionProviderManagerImpl implements ExtensionProviderManager {

	@Reference
	private volatile Collection<ServiceReference<ExtensionProviderFilter>> filters;

	private BundleContext ctx;

	@Activate
	protected void activate(BundleContext ctx) {
		this.ctx = ctx;
	}

	@SuppressWarnings("null")
	@Override
	@NotNull
	public <T extends ExtensionProvider> Stream<ServiceReference<T>> filter(
			@NotNull Collection<ServiceReference<T>> refs, @NotNull Adaptable adaptable) {
		Stream<ServiceReference<T>> all = Stream.empty();

		for (ServiceReference<ExtensionProviderFilter> ref : filters) {
			String[] classes = PropertiesUtil.toStringArray(ref.getProperty(PROPERTY_ADAPTABLE_CLASSES), new String[0]);
			boolean match = Arrays.stream(classes)
				.map(c -> {
					try {
						return ctx.getBundle().loadClass(c);
					} catch (ClassNotFoundException e) {
						throw new TypeException("", e);
					}
				})
				.anyMatch(c -> c.isAssignableFrom(adaptable.getClass()));

			if (match) {
				ExtensionProviderFilter filter = ctx.getService(ref);
				all = Stream.concat(all, filter.filter(refs, adaptable));
			}
		}

		return all;
	}
}
