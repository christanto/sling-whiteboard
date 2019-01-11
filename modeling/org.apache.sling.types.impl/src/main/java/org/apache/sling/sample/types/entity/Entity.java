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

import java.util.Collection;

import org.apache.sling.types.Type;
import org.apache.sling.types.data.DataType;
import org.jetbrains.annotations.NotNull;

/**
 * A type representing an entity.
 *
 * This type acts as an example to showcase to define a new type.
 *
 * The type is design to be higher level that leverages lower level type namely
 * {@link DataType}. This is done such that there is an intermediate layer to
 * prevent the properties defined by {@link DataType} exposed directly for other
 * purposes, which may not be desirable.
 */
public interface Entity extends Type {

	@NotNull
	Collection<@NotNull Prop> getProperties();

	@NotNull
	Collection<@NotNull Link> getLinks();
}
