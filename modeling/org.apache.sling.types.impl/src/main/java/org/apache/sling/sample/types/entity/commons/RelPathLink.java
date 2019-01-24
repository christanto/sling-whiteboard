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
package org.apache.sling.sample.types.entity.commons;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.sample.types.entity.Link;
import org.apache.sling.types.attributes.Attributes;
import org.apache.sling.types.attributes.commons.AttributesBuilder;
import org.apache.sling.types.attributes.commons.AttributesFactory;
import org.jetbrains.annotations.NotNull;

public class RelPathLink implements Link {

	@NotNull
	protected AttributesBuilder<?, RelPathLink> attrs;

	@NotNull
	private Href href;

	@SuppressWarnings("unchecked")
	public RelPathLink(@NotNull AttributesFactory attrsFactory, @NotNull String rel, @NotNull String relPath) {
		attrs = (AttributesBuilder<?, RelPathLink>) attrsFactory.getWritable(getClass());
		attrs.put("rel", rel);

		href = new Href() {
			@Override
			public boolean isPath() {
				return true;
			}

			@Override
			public String getValue() {
				return relPath;
			}
		};
	}

	@Override
	@NotNull
	public Attributes getAttributes() {
		return attrs.build();
	}

	@SuppressWarnings("null")
	@Override
	@NotNull
	public Href[] getHref(@NotNull Resource resource) {
		return new Href[] { href };
	}

	@Override
	public boolean isMultipleHref() {
		return false;
	}
}
