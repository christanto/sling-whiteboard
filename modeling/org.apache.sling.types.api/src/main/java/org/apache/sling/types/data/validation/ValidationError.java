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
package org.apache.sling.types.data.validation;

import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.types.data.Property;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * The error during validation process.
 *
 * @param <T> the type of the property
 * @param <V> the type of the value of the property
 */
@ConsumerType
public interface ValidationError<T extends Property<V>, V> {

	/**
	 * Returns the property associated with this error.
	 *
	 * @return the property associated with this error
	 */
	@NotNull
	T getProperty();

	/**
	 * Returns the new values of the property to set. These values represent the
	 * values that fail the validation process.
	 *
	 * @return the new values of the property to set
	 */
	@NotNull
	RequestParameter[] getRequestParameters();

	/**
	 * Returns the error message.
	 *
	 * @return the error message
	 */
	@NotNull
	String getMessage();
}
