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
package org.apache.sling.types.spi;

import java.util.Collection;

import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.types.Type;
import org.apache.sling.types.TypeSystem;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * The SPI to be implemented by OSGi services to provide the available
 * {@link Type} of a particular {@link Adaptable}.
 *
 * <h2>Example</h2>
 * <pre>
 *&#64;Component(
 *    property = {
 *        TypeProvider.PROPERTY_RESOURCE_TYPE + "=/libs/slingshot/User"
 *    }
 *)
 *public class UserTypeProvider implements TypeProvider {
 *    &#64;Override
 *    &#64;NotNull
 *    public Collection&#60;&#64;NotNull Class&#60;? extends Type&#62;&#62; getAvailableTypes() {
 *        return Arrays.asList(DataType.class);
 *    }
 *}</pre>
 *
 * @since 1.0
 */
@ConsumerType
public interface TypeProvider extends ExtensionProvider {

	/**
	 * The OSGi service property name indicating this provider binds based on the
	 * resource type.
	 */
	String PROPERTY_RESOURCE_TYPE = "sling.resource.resourceType";

	/**
	 * Returns the available types of the current adaptable.
	 *
	 * <p>
	 * For each type returned, the current adaptable can be adapted using
	 * {@link Adaptable#adaptTo(Class)} against the type.
	 * </p>
	 *
	 * <p>
	 * The collection returned by this method is then aggregated to return the final
	 * collection of {@link TypeSystem#getAvailableTypes()}.</p>
	 *
	 * @return the collection of type
	 */
	@NotNull
	Collection<@NotNull Class<? extends Type>> getAvailableTypes();
}
