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

import org.apache.sling.sample.types.entity.Prop;
import org.apache.sling.sample.types.entity.commons.SimpleProp;
import org.apache.sling.sample.types.entity.spi.PropProvider;
import org.apache.sling.types.attributes.commons.AttributesFactory;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        PropProvider.PROPERTY_RESOURCE_TYPE + "=/libs/slingshot/Userinfo"
    }
)
public class UserInfoPropProvider implements PropProvider {

	@Reference
	private AttributesFactory attrsFactory;

    @SuppressWarnings("null")
    @Override
    @NotNull
    public Collection<@NotNull Prop> getProperties() {
        return Arrays.asList(
            new SimpleProp(attrsFactory, "name"),
            new SimpleProp(attrsFactory, "about")
        );
    }
}
