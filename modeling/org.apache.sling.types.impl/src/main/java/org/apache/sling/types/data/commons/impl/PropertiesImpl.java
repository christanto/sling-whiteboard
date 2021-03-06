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
package org.apache.sling.types.data.commons.impl;

import org.apache.sling.types.attributes.commons.AttributesFactory;
import org.apache.sling.types.data.commons.BooleanProperty;
import org.apache.sling.types.data.commons.DateProperty;
import org.apache.sling.types.data.commons.Properties;
import org.apache.sling.types.data.commons.TextProperty;
import org.apache.sling.types.data.commons.TextareaProperty;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class PropertiesImpl implements Properties {

    @Reference
    private AttributesFactory factory;

    @SuppressWarnings("null")
    @Override
    public TextProperty.Builder text(@NotNull String id, @NotNull String name) {
        return new TextPropertyImpl(factory, id, name);
    }

    @SuppressWarnings("null")
    @Override
    public TextareaProperty.Builder textarea(@NotNull String id, @NotNull String name) {
        return new TextareaPropertyImpl(factory, id, name);
    }

    @SuppressWarnings("null")
    @Override
    public BooleanProperty.Builder bool(@NotNull String id, @NotNull String name) {
        return new BooleanPropertyImpl(factory, id, name);
    }

    @SuppressWarnings("null")
    @Override
    public DateProperty.Builder date(@NotNull String id, @NotNull String name) {
        return new DatePropertyImpl(factory, id, name);
    }
}
