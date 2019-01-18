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
package org.apache.sling.sample.types.entity.commons.impl;

import java.util.Optional;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.sample.types.entity.Prop;
import org.apache.sling.sample.types.entity.commons.EntityService;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.data.DataType;
import org.apache.sling.types.data.Property;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;

@Component
public class EntityServiceImpl implements EntityService {

	@SuppressWarnings("null")
	@Override
	@NotNull
	public Optional<Property<?>> getProperty(@NotNull Adaptable adaptable, @NotNull Prop prop) throws TypeException {
		return Optional.<DataType>ofNullable(adaptable.adaptTo(DataType.class))
				.flatMap(m -> m.getPropertyById(Optional.of(prop.getBinding())));
	}
}
