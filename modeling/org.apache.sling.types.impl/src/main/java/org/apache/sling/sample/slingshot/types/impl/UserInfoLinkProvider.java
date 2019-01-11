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

import org.apache.sling.sample.types.entity.Link;
import org.apache.sling.sample.types.entity.commons.RelPathLink;
import org.apache.sling.sample.types.entity.spi.LinkProvider;
import org.apache.sling.types.attributes.commons.AttributesFactory;
import org.apache.sling.types.spi.TypeProvider;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        TypeProvider.PROPERTY_RESOURCE_TYPE + "=/libs/slingshot/Userinfo"
    }
)
public class UserInfoLinkProvider implements LinkProvider {

	@Reference
	private AttributesFactory factory;

	@SuppressWarnings("null")
	@Override
	@NotNull
	public Collection<@NotNull Link> getLinks() {
		return Arrays.asList(
			new RelPathLink(factory, "up", "..")
		);
	}
}
