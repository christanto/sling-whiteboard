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
package org.apache.sling.types.data.validation.commons.impl;

import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.types.data.Property;
import org.apache.sling.types.data.validation.commons.RequiredError;
import org.jetbrains.annotations.NotNull;

class RequiredErrorImpl<T extends Property<V>, V> implements RequiredError<T, V> {
    @NotNull
    private T property;

    @NotNull
    private RequestParameter[] params;

    @SuppressWarnings("null")
    public RequiredErrorImpl(@NotNull T property, RequestParameter... params) {
        this.property = property;
        this.params = params;
    }

    @Override
    @NotNull
    public RequestParameter[] getRequestParameters() {
        return params;
    }

    @Override
    @NotNull
    public T getProperty() {
        return property;
    }

    @Override
    @NotNull
    public String getMessage() {
        return "The field is required";
    }
}
