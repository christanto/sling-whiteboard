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
package org.apache.sling.sample.slingshot.types.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.sling.types.data.Property;
import org.apache.sling.types.data.commons.Properties;
import org.apache.sling.types.data.spi.PropertyProvider;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The PropertyProvider for Stream.
 *
 * Unlike {@code /libs/slingshot/User}, where the DataType is exposed by its
 * {@code info} subresource, for Stream, we expose the DataType directly at the
 * Stream resource itself. This is to showcase that the DataType may or may not
 * need to strictly follow resource or persistence structure one-to-one.
 */
@Component(
    property = {
        PropertyProvider.PROPERTY_RESOURCE_TYPE + "=/libs/slingshot/Stream"
    }
)
public class StreamPropertyProvider implements PropertyProvider {

    @Reference
    private Properties properties;

    @SuppressWarnings("null")
    @Override
    @NotNull
    public List<@NotNull Property<?>> getProperties() {
        return Arrays.asList(
            properties.text("title", "info/title").withTitle("Title"),
            properties.text("description", "info/description").withTitle("Description")
        );
    }
}
