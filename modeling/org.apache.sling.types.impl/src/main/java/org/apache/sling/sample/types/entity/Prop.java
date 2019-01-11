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
package org.apache.sling.sample.types.entity;

import org.apache.sling.types.attributes.AttributesProvider;
import org.apache.sling.types.attributes.annotations.Attribute;
import org.apache.sling.types.data.Property;
import org.jetbrains.annotations.NotNull;

/**
 * A property of an {@link Entity}.
 */
public interface Prop extends AttributesProvider {

	/**
	 * Returns the ID of the property ({@link Property#getId()}).
	 *
	 * The {@link Property} is said to be binded to this {@link Prop}.
	 *
	 * @return
	 */
	@Attribute("sling:bind")
	@NotNull
	default String getBinding() {
		String bind = getAttributes().get("sling:bind", String.class);

		if (bind != null) {
			return bind;
		} else {
			throw new IllegalStateException("The required `sling:bind` attribute is not found");
		}
	}
}
