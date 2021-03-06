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
package org.apache.sling.types.attributes.commons.impl;

import org.apache.sling.types.attributes.AttributeDefinition;
import org.jetbrains.annotations.NotNull;

abstract class SimpleDefinition<T extends AttributeDefinition<V>, V> implements AttributeDefinition<V> {

    private @NotNull String name;
    private @NotNull String type;
    private @NotNull Class<V> typeClass;
    private boolean multiple;
    private boolean required;

    public SimpleDefinition(@NotNull String name, @NotNull String type, @NotNull Class<V> typeClass) {
        this.name = name;
        this.type = type;
        this.typeClass = typeClass;
    }

    @Override
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override
    @NotNull
    public String getType() {
        return type;
    }

    @Override
    @NotNull
    public Class<V> getTypeClass() {
        return typeClass;
    }

    @Override
    public boolean isMultiple() {
        return multiple;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @NotNull
    public T withMultiple() {
        return withMultiple(true);
    }

    @NotNull
    public T withMultiple(boolean multiple) {
        this.multiple = multiple;
        return getSelf();
    }

    @NotNull
    public T withRequired() {
        return withRequired(true);
    }

    @NotNull
    public T withRequired(boolean required) {
        this.required = required;
        return getSelf();
    }

    @NotNull
    protected abstract T getSelf();
}
