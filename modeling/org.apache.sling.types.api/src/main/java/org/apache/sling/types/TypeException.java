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
package org.apache.sling.types;

/**
 * The main exception to be thrown to indicate that the operation in the type
 * system cannot be fulfilled.
 *
 * @since 1.0
 */
public class TypeException extends RuntimeException {
	private static final long serialVersionUID = -4119143566082644364L;

	public TypeException() {
		super();
	}

	public TypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypeException(String message) {
		super(message);
	}

	public TypeException(Throwable cause) {
		super(cause);
	}
}
