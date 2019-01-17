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

import org.apache.sling.types.TypeException;
import org.apache.sling.types.attributes.AttributeContext;
import org.apache.sling.types.attributes.AttributeDefinition;
import org.apache.sling.types.attributes.spi.AttributeHandler;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;

/**
 * The service interface to perform attribute processing.
 * <p>
 * To participate of the processing, one needs to register
 * {@link AttributeHandler}.
 * </p>
 *
 * @since 1.0
 */
@ProviderType
public interface AttributeService {

	/**
	 * Performs the post-processing of the given attribute value for the purpose of
	 * exporting it outside the system.
	 * <p>
	 * For example, an attribute with URL type may have the value =
	 * {@code /content/page1.html} which refers relatively to the current servlet
	 * context. So when the attribute needs to be exported outside the system, the
	 * servlet context path may need to be applied first, giving the final value =
	 * {@code /ctx/content/page1.html}.
	 * </p>
	 * <p>
	 * Another example is an attribute with value that needs to be internationalized
	 * based on the current request locale.
	 * </p>
	 *
	 * @param       <T> the type of the attribute value
	 * @param ctx   the context of the processing
	 * @param def   the definition of the attribute whose value is processed
	 * @param value the value of the attribute to process
	 * @return the processed value
	 * @throws TypeException when error occurs related to type
	 */
	@NotNull
	<T> T process(@NotNull AttributeContext ctx, @NotNull AttributeDefinition<T> def, @NotNull T value)
			throws TypeException;
}
