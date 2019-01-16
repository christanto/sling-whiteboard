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
package org.apache.sling.types.attributes.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link TypeQualifier} for URL.
 *
 * <p>
 * The supported getter return type is {@link String}.
 * </p>
 *
 * <h2>Example</h2>
 * <pre>
 *public interface Property extends AttributesProvider {
 *    &#64;Attribute
 *    &#64;Url
 *    String getDocUrl();
 * </pre>
 *
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@TypeQualifier
public @interface Url {

	/**
	 * Checks the absolute-ness of this URL.
	 * <p>
	 * Absolute here is defined as one of the following criteria:
	 * </p>
	 * <ol>
	 * <li>The URL is absolute, such as
	 * {@code http://example.org/content/page1.html}.</li>
	 * <li>The URL is relative but its path is absolute, such as
	 * {@code /content/page1.html}.</li>
	 * </ol>
	 *
	 * @return {@code true} if it is absolute, {@code false} otherwise
	 */
	boolean absolute() default true;

	/**
	 * Checks if this URL is a URI Template.
	 *
	 * @return {@code true} if it is a URI Template, {@code false} otherwise
	 */
	boolean templated() default false;
}
