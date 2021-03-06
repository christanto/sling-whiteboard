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
package org.apache.sling.types.data.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.sling.api.adapter.AdapterFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.types.data.DataType;
import org.apache.sling.types.data.Property;
import org.apache.sling.types.data.spi.PropertyProvider;
import org.apache.sling.types.spi.ExtensionProviderManager;
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
        AdapterFactory.ADAPTER_CLASSES + "=org.apache.sling.types.data.DataType"
    }
)
public class DataTypeAdapterFactory implements AdapterFactory {

    @Reference
    private volatile Collection<ServiceReference<PropertyProvider>> providers;

    @Reference
    private ExtensionProviderManager filters;

    private BundleContext bundleContext;

    @Activate
    protected void activate(BundleContext ctx) {
        this.bundleContext = ctx;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    @Nullable
    public <AdapterType> AdapterType getAdapter(@NotNull Object adaptable, @NotNull Class<AdapterType> type) {
        Resource resource = (Resource) adaptable;

        @SuppressWarnings("null")
        Stream<ServiceReference<PropertyProvider>> all = filters.filter(providers, resource);

        @SuppressWarnings("null")
        @NotNull
        List<@NotNull Property<?>> properties = all
            .map(bundleContext::getService)
            .map(PropertyProvider::getProperties)
            .flatMap(List::stream)
            .collect(Collectors.toList());

        return (AdapterType) new DataType() {
            @Override
            @NotNull
            public List<@NotNull Property<?>> getProperties() {
                return properties;
            }
        };
    }
}
