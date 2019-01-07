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
package org.apache.sling.modeling.data.commons.impl;

import java.util.Arrays;

import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.modeling.ModelException;
import org.apache.sling.modeling.attributes.commons.AttributesFactory;
import org.apache.sling.modeling.data.commons.SimpleProperty;
import org.apache.sling.modeling.data.commons.SimplePropertyHandler;
import org.apache.sling.modeling.data.commons.TextareaProperty;
import org.apache.sling.modeling.data.spi.PropertyHandler;
import org.apache.sling.modeling.data.validation.commons.Errors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

class TextareaPropertyImpl extends SimpleProperty<TextareaProperty> implements TextareaProperty {
    @NotNull
    private static final String TYPE = "sling:textarea";

    public TextareaPropertyImpl(@NotNull AttributesFactory attrsFactory, @NotNull String id, @NotNull String name) {
        super(attrsFactory, id, name, TYPE);
    }

    @Component(
        service = PropertyHandler.class,
        property = {
            PropertyHandler.PROPERTY_TYPE + "=" + TYPE
        }
    )
    public class TextareaPropertyHandler extends SimplePropertyHandler<TextareaProperty> {
        @Reference
        private Errors errors;

        @Override
        protected Errors getErrors() {
            return errors;
        }

        @Override
        @Nullable
        protected Object getValue(@NotNull TextareaProperty property, @NotNull ValueMap vm) throws ModelException {
            return vm.get(property.getName(), String.class);
        }

        @Override
        protected Object[] convertParams(@NotNull TextareaProperty property, RequestParameter... params)
                throws ModelException {
            return Arrays.stream(params).map(p -> p.getString()).toArray(String[]::new);
        }
    }
}
