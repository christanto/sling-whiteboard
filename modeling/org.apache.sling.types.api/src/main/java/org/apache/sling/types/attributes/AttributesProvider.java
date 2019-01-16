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
package org.apache.sling.types.attributes;

import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * Provides the {@link Attributes}.
 *
 * <p>
 * This interface is usually implemented by the type's domain entity to hold its
 * attributes. For example, the Property of DataType, which may be represented
 * as a set of key-value pairs (the attributes) may implement this interface to
 * hold the pairs.
 * </p>
 *
 * @since 1.0
 */
@ConsumerType
public interface AttributesProvider {

	/**
	 * Returns the attributes of this domain entity.
	 *
	 * @return the attributes
	 */
	@NotNull
	Attributes getAttributes();
}
