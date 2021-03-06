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

import java.util.Arrays;
import java.util.Calendar;

import org.apache.jackrabbit.util.ISO8601;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.attributes.commons.AttributesFactory;
import org.apache.sling.types.data.commons.DateProperty;
import org.apache.sling.types.data.commons.DateProperty.Builder;
import org.apache.sling.types.data.commons.SimpleProperty;
import org.apache.sling.types.data.commons.SimplePropertyHandler;
import org.apache.sling.types.data.spi.PropertyHandler;
import org.apache.sling.types.data.validation.commons.Errors;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

class DatePropertyImpl extends SimpleProperty<Builder, DateProperty, Calendar> implements DateProperty, Builder {

    public DatePropertyImpl(@NotNull AttributesFactory attrsFactory, @NotNull String id, @NotNull String name) {
        super(attrsFactory, id, name, TYPE);
    }

    @Override
	@NotNull
	public DateProperty build() {
		return this;
	}

    @Override
	@NotNull
	protected Builder getSelf() {
		return this;
	}

    @Component(
        service = PropertyHandler.class,
        property = {
            PropertyHandler.PROPERTY_TYPE + "=" + DateProperty.TYPE
        }
    )
    public class DatePropertyHandler extends SimplePropertyHandler<DateProperty, Calendar> {
        @Reference
        private Errors errors;

        @Override
        protected Errors getErrors() {
            return errors;
        }

        @SuppressWarnings("null")
		@Override
        @NotNull
        protected Calendar[] getValue(@NotNull DateProperty property, @NotNull ValueMap vm) throws TypeException {
            return vm.get(property.getName(), new Calendar[0]);
        }

        @Override
        protected Calendar[] convertParams(@NotNull DateProperty property, RequestParameter... params)
                throws TypeException {
            return Arrays.stream(params)
                .map(RequestParameter::getString)
                .map(ISO8601::parse)
                .toArray(Calendar[]::new);
        }
    }
}
