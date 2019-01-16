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

import org.apache.sling.types.attributes.AttributeDefinition;
import org.apache.sling.types.attributes.AttributesProvider;

/**
 * The getter method annotated by this annotation represents an attribute.
 *
 * <p>
 * Given the following example:
 * </p>
 *
 * <pre>
 *public interface Property extends AttributesProvider {
 *    &#64;Attribute("sling:id")
 *    String getId();</pre>
 * <p>
 * The {@code getId()} above represents an attribute named {@code sling:id}.
 * </p>
 * <p>
 * Furthermore, when inspected via {@link AttributesProvider#getAttributes()},
 * there is an {@link AttributeDefinition} existed representing
 * {@code sling:id}.
 * </p>
 *
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Attribute {

	/**
	 * The name of the attribute.
	 *
	 * @return the name of attribute. When it is empty string, the name is inferred
	 *         from the name of the getter method.
	 */
	String value() default "";
}
