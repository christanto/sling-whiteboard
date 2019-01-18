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
package org.apache.sling.types.data.spi;

import java.util.List;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.types.TypeException;
import org.apache.sling.types.data.DataType;
import org.apache.sling.types.data.Property;
import org.apache.sling.types.spi.ExtensionProvider;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * The SPI to be implemented by OSGi services to provide the available
 * {@link Property} of a particular {@link Adaptable}.
 *
 * @since 1.0
 */
@ConsumerType
public interface PropertyProvider extends ExtensionProvider {

	/**
	 * The OSGi service property name to indicate the resource type this provider
	 * binds to.
	 */
	String PROPERTY_RESOURCE_TYPE = "sling.resource.resourceType";

	/**
	 * Returns the properties to be part of the {@link DataType} of a particular
	 * {@link Adaptable}.
	 *
	 * @return the list of {@link Property}
	 * @throws TypeException when error occurs related to type
	 */
	@NotNull
	List<@NotNull Property<?>> getProperties() throws TypeException;
}
