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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.sling.api.adapter.AdapterFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.types.Type;
import org.apache.sling.types.TypeSystem;
import org.apache.sling.types.spi.ExtensionProviderManager;
import org.apache.sling.types.spi.TypeProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        AdapterFactory.ADAPTABLE_CLASSES + "=org.apache.sling.api.resource.Resource",
        AdapterFactory.ADAPTER_CLASSES + "=org.apache.sling.types.TypeSystem"
    }
)
public class TypeSystemAdapterFactory implements AdapterFactory {

    @Reference
    private volatile Collection<ServiceReference<TypeProvider>> providers;

    @Reference
    private ExtensionProviderManager filters;

    private BundleContext bundleContext;

    @Activate
    protected void activate(BundleContext ctx) {
        this.bundleContext = ctx;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <AdapterType> AdapterType getAdapter(@NotNull Object adaptable, @NotNull Class<AdapterType> type) {
        Resource resource = (Resource) adaptable;

        @SuppressWarnings("null")
        Stream<ServiceReference<TypeProvider>> all = filters.filter(providers, resource);

        @SuppressWarnings("null")
        @NotNull
        List<@NotNull Class<? extends Type>> types = all
            .map(bundleContext::getService)
            .map(TypeProvider::getAvailableTypes)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        return (AdapterType) new TypeSystem() {
            @Override
            @NotNull
            public Collection<@NotNull Class<? extends Type>> getAvailableTypes() {
                return types;
            }
        };
    }
}
