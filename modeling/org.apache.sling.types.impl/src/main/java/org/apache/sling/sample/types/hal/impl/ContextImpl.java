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
package org.apache.sling.sample.types.hal.impl;

import java.util.Locale;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.types.Context;
import org.apache.sling.types.TypeSystem;
import org.jetbrains.annotations.NotNull;

public class ContextImpl implements Context<Resource> {

    @NotNull
    private Resource resource;

    @NotNull
    private TypeSystem typeSystem;

    @NotNull
    private Locale locale;

    public ContextImpl(@NotNull Resource resource, @NotNull TypeSystem typeSystem, @NotNull Locale locale) {
        this.resource = resource;
        this.typeSystem = typeSystem;
        this.locale = locale;
    }

    @Override
    @NotNull
    public Resource getAdaptable() {
        return resource;
    }

    @Override
    @NotNull
    public TypeSystem getTypeSystem() {
        return typeSystem;
    }

    @Override
    @NotNull
    public Locale getLocale() {
        return locale;
    }
}
