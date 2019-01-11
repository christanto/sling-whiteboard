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
import java.util.Collection;

import org.apache.sling.sample.types.entity.Entity;
import org.apache.sling.types.Type;
import org.apache.sling.types.data.DataType;
import org.apache.sling.types.spi.TypeProvider;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;

@Component(
    property = {
        TypeProvider.PROPERTY_RESOURCE_TYPE + "=/libs/slingshot/Userinfo"
    }
)
public class UserInfoTypeProvider implements TypeProvider {

    @SuppressWarnings("null")
    @Override
    @NotNull
    public Collection<@NotNull Class<? extends Type>> getAvailableTypes() {
        return Arrays.asList(Entity.class, DataType.class);
    }
}
