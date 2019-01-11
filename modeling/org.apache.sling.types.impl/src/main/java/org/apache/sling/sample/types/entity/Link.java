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

import java.util.Optional;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.types.attributes.AttributesProvider;
import org.apache.sling.types.attributes.annotations.Attribute;
import org.apache.sling.types.attributes.annotations.Url;
import org.jetbrains.annotations.NotNull;

/**
 * A link of an {@link Entity}.
 */
public interface Link extends AttributesProvider {

	@Attribute
	@NotNull
	default String getRel() {
		String rel = getAttributes().get("rel", String.class);

        if (rel != null) {
            return rel;
        } else {
            throw new IllegalStateException("The required `rel` attribute is not found");
        }
	}

	@Attribute
	@Url
	@NotNull
	Href[] getHref(@NotNull Resource resource);

	boolean isMultipleHref();

	@Attribute
	default boolean isTemplated() {
		return getAttributes().get("templated", false);
	}

	@SuppressWarnings("null")
	@Attribute
	@NotNull
	default Optional<String> getType() {
		return Optional.<String>ofNullable(getAttributes().get("type", String.class));
	}

	@SuppressWarnings("null")
	@Attribute
	@Url
	@NotNull
	default Optional<String> getDeprecation() {
		return Optional.<String>ofNullable(getAttributes().get("deprecation", String.class));
	}

	@SuppressWarnings("null")
	@Attribute
	@NotNull
	default Optional<String> getName() {
		return Optional.<String>ofNullable(getAttributes().get("name", String.class));
	}

	@SuppressWarnings("null")
	@Attribute
	@Url
	@NotNull
	default Optional<String> getProfile() {
		return Optional.<String>ofNullable(getAttributes().get("profile", String.class));
	}

	@SuppressWarnings("null")
	@Attribute
	@NotNull
	default Optional<String> getTitle() {
		return Optional.<String>ofNullable(getAttributes().get("title", String.class));
	}

	@SuppressWarnings("null")
	@Attribute
	@NotNull
	default Optional<String> getHrefLang() {
		return Optional.<String>ofNullable(getAttributes().get("hrefLang", String.class));
	}

	interface Href {
		boolean isPath();

		String getValue();
	}
}
