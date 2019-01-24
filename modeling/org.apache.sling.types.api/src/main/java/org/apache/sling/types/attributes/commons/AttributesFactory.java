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
package org.apache.sling.types.attributes.commons;

import org.apache.sling.types.attributes.AttributeDefinitions;
import org.apache.sling.types.attributes.Attributes;
import org.apache.sling.types.attributes.AttributesProvider;
import org.apache.sling.types.attributes.annotations.Attribute;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;

/**
 * The service interface for instantiate {@link Attributes}.
 *
 * @since 1.0
 */
@ProviderType
public interface AttributesFactory {

	/**
	 * Returns a new instance of {@link AttributesBuilder}. Its
	 * {@link AttributeDefinitions} is automatically generated based on the
	 * {@link Attribute} annotations defined in the given annotationSource.
	 *
	 * @param                  <P> the type of the attribute provider
	 * @param annotationSource the source of the annotations to generate the
	 *                         attribute definitions from
	 * @return a new {@link AttributesBuilder} instance
	 */
	@NotNull
	<P extends AttributesProvider> AttributesBuilder<?, P> getWritable(@NotNull Class<P> annotationSource);
}
