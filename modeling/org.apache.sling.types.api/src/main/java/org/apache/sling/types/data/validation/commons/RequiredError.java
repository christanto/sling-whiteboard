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
package org.apache.sling.types.data.validation.commons;

import org.apache.sling.types.data.Property;
import org.apache.sling.types.data.validation.ValidationError;
import org.osgi.annotation.versioning.ProviderType;

/**
 * The error to indicate that the new value of the property is empty while
 * {@link Property#isRequired()} returns {@code true}.
 *
 * @param <T> the type of the property
 * @param <V> the type of the value of the property
 *
 * @since 1.0
 */
@ProviderType
public interface RequiredError<T extends Property<V>, V> extends ValidationError<T, V> {
}
