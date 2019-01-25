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

import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.attributes.commons.AttributesFactory;
import org.apache.sling.types.data.commons.BooleanProperty;
import org.apache.sling.types.data.commons.BooleanProperty.Builder;
import org.apache.sling.types.data.commons.SimpleProperty;
import org.apache.sling.types.data.commons.SimplePropertyHandler;
import org.apache.sling.types.data.spi.PropertyHandler;
import org.apache.sling.types.data.validation.commons.Errors;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

class BooleanPropertyImpl extends SimpleProperty<Builder, BooleanProperty, Boolean> implements BooleanProperty, Builder {

    public BooleanPropertyImpl(@NotNull AttributesFactory attrsFactory, @NotNull String id, @NotNull String name) {
        super(attrsFactory, id, name, TYPE);
    }

    @Override
    @NotNull
    public Builder withCheckedValue(@NotNull String checkedValue) {
        attrs.put("checkedValue", checkedValue);
        return this;
    }

    @Override
	@NotNull
	public Builder withUncheckedValue(@NotNull String uncheckedValue) {
    	attrs.put("uncheckedValue", uncheckedValue);
    	return this;
	}

	@Override
	@NotNull
	public BooleanProperty build() {
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
            PropertyHandler.PROPERTY_TYPE + "=" + BooleanProperty.TYPE
        }
    )
    public static class BooleanPropertyHandler extends SimplePropertyHandler<BooleanProperty, Boolean> {
        @Reference
        private Errors errors;

        @Override
        protected Errors getErrors() {
            return errors;
        }

        @SuppressWarnings("null")
		@Override
        @NotNull
		protected Boolean[] getValue(@NotNull BooleanProperty property, @NotNull ValueMap vm) throws TypeException {
        	String checkedValue = property.getCheckedValue().orElse("true");
            String uncheckedValue = property.getUncheckedValue().orElse("false");

            String value = vm.get(property.getName(), String.class);

            if (value == null) {
            	return new Boolean[0];
            }
            if (checkedValue.equals(value)) {
                return new Boolean[] { true };
            }
            if (uncheckedValue.equals(value)) {
            	return new Boolean[] { false };
            }
            throw new TypeException("Mismatched value: " + value);
        }

        @Override
		protected Boolean[] convertParams(@NotNull BooleanProperty property, RequestParameter... params)
				throws TypeException {
            String checkedValue = property.getCheckedValue().orElse("true");
            String uncheckedValue = property.getUncheckedValue().orElse("false");

            return Arrays.stream(params)
                .map(RequestParameter::getString)
                .map(value -> {
                    if (checkedValue.equals(value)) {
                        return true;
                    } else if (uncheckedValue.equals(value)) {
                        return false;
                    }
                    throw new TypeException("Mismatched value: " + value);
                })
                .toArray(Boolean[]::new);
        }
    }
}
