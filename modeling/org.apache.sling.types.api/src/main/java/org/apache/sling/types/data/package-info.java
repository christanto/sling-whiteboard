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
/**
 * {@link org.apache.sling.types.data.DataType} is the lowest level type that describes a resource from the perspective of data.
 * <p>For example, given the following resource:</p>
 * <pre>
 *+ mycontent
 *  - jcr:created = "2018-03-06T11:03:28.879+08:00"
 *  - jcr:createdBy = "admin"
 *  - jcr:language = "en_US"
 *  - jcr:title = "Example Page"
 *  - sling:resourceType = "example/components/page"</pre>
 * <p>A DataType can be created for <code>/libs/example/components/page</code> resource type to describe its data types:</p>
 * <p>(Note that the types are described using JSON format for the purpose of this article)</p>
 * <pre>
 *{
 *  "properties": [{
 *    "sling:id": "created",
 *    "sling:name": "jcr:created",
 *    "sling:type": "sling:date",
 *    "sling:title": "Created Date"
 *  }, {
 *    "sling:id": "createdby",
 *    "sling:name": "jcr:createdBy",
 *    "sling:type": "sling:user",
 *    "sling:title": "Created By"
 *  }, {
 *    "sling:id": "language",
 *    "sling:name": "jcr:language",
 *    "sling:type": "sling:locale",
 *    "sling:title": "Language"
 *  }, {
 *    "sling:id": "title",
 *    "sling:name": "jcr:title",
 *    "sling:type": "sling:text",
 *    "sling:title": "Title",
 *    "sling:required": true
 *  }, {
 *    "sling:id": "resourcetype",
 *    "sling:name": "sling:resourceType",
 *    "sling:type": "sling:resourcetype",
 *    "sling:title": "Resource Type"
 *  }]
 *}</pre>
 * <p>When describing the DataType of <code>/libs/example/components/page</code> this way,
 * we are essentially saying that we model the typing system of <code>/libs/example/components/page</code>.
 * The resources having <code>/libs/example/components/page</code> as their resource types are driven by its types,
 * where DataType is one of many possible types.
 * <p>To get the DataType of a resource, simply adapt the {@link org.apache.sling.api.resource.Resource} into {@link org.apache.sling.types.data.DataType}:</p>
 * <pre>
 *Resource resource = slingRequest.getResource();
 *DataType dataType = resource.adaptTo(DataType.class);
 *&#47;&#47; use dataType accordingly</pre>
 *
 * <h2>DataType's Properties</h2>
 * <p>A DataType consists of multiple {@link org.apache.sling.types.data.Property}.</p>
 * <p>A Property represents the definition of a resource's property.
 * The definition is typically just a set of attributes describing the characteristics of the said property.</p>
 *
 * <h2>Property Types</h2>
 * <p>The most important attribute is <code>sling:type</code> attribute, which represents the type of the property.</p>
 * <p>Just like resource types are often used as a hooking mechanic to model the resources,
 * the property types are also used as a hooking mechanic to model the properties.</p>
 * <p>For instance, Sling provides <code>sling:text</code> type to represent a simple text property. It defines the following attributes:</p>
 * <ul>
 * <li>sling:id</li>
 * <li>sling:name</li>
 * <li>sling:type</li>
 * <li>sling:title</li>
 * <li>sling:multiple</li>
 * <li>sling:readonly</li>
 * <li>sling:required</li>
 * <li>sling:validations</li>
 * </ul>
 * <p>The app developers can then use these attributes to describe their properties.</p>
 * <p>Using the example above, the <code>jcr:title</code> property has the following definition:</p>
 * <pre>
 *sling:id = "title"
 *sling:name = "jcr:title"
 *sling:type = "sling:text"
 *sling:title = "Title"
 *sling:required = true</pre>
 *
 * <h2>PropertyHandler</h2>
 * <p>Each property type needs to implement a {@link org.apache.sling.types.data.spi.PropertyHandler}.</p>
 * <p>The primary purpose of the handler is to be the implementation to retrieve/persist the property's values from/to the persistence layer.
 * It is implemented as an OSGi service.</p>
 *
 * <h2>Comparison with ValueMap</h2>
 * <p>Traditionally, when dealing with resource's properties, ValueMap is used:</p>
 * <pre>
 *ValueMap vm = resource.getValueMap();
 *String prop1 = vm.get("prop1", String.class);</pre>
 * <p>ValueMap offers conversion service to convert the properties to the desired Java classes.
 * However, it only covers the "primitive" types, such as String, Long, Calendar, and their collection counterparts.
 * It is not extensible to cover more complex property types, such as path, URI, and JSON.
 * ValueMap decision may be based on the types supported by the persistence layer, which is JCR in general.</p>
 * <p>DataType is simply ValueMap on steroid, where it allows arbitrary property types to be defined.</p>
 * <p>PropertyHandler is used as the converter between the DataType's Property and the persistence layer.
 * So, while the persistence layer only allows a certain set of primitive types,
 * we can simply define a new property type to represent a more complex type and implement its PropertyHandler
 * to convert between the Java class and the persistence layer's type, which would be a simple String most likely.</p>
 *
 * <h2>Establishing DataTypes</h2>
 * <p>To establish the DataTypes, app developers need to implement and register {@link org.apache.sling.types.data.spi.PropertyProvider},
 * where they need to associate the providers with the resource types using OSGi service property.</p>
 * <p>For example for <code>/libs/example/components/page</code> resource type:</p>
 * <pre>
 *&#64;Component(
 *    property = {
 *        PropertyProvider.PROPERTY_RESOURCE_TYPE + "=/libs/example/components/page"
 *    }
 *)
 *public class PagePropertyProvider implements PropertyProvider {
 *    &#64;Override
 *    &#64;NotNull
 *    public List&lt;&#64;NotNull Property&gt; getProperties() {
 *        // Return the list of properties
 *    }
 *}</pre>
 *
 * <h2>DataTypes Using JCR Node Types</h2>
 * <p>While the resource types are the most common ways to define the types of the resources,
 * the typing system of the underlying platform is richer than just resource types alone.
 * Since JCR is the primary persistence layer of Sling, JCR's node types can be considered as a typing system as well.</p>
 * <p>The following example shows a content structure using <code>cq:Page</code> and <code>cq:PageContent</code> node types of AEM:</p>
 * <pre>
 *+ /content/we-retail
 *  - jcr:created = "2018-11-27T15:12:53.516+08:00"
 *  - jcr:createdBy = "admin"
 *  - jcr:primaryType = "cq:Page"
 *  + jcr:content
 *    - cq:allowedTemplates = ["/conf/we-retail/settings/wcm/templates/.*"]
 *    - cq:lastModified = "2016-02-09T00:05:48.520+01:00"
 *    - cq:lastModifiedBy = "admin"
 *    - jcr:created = "2018-11-27T15:14:41.552+08:00"
 *    - jcr:createdBy = "admin"
 *    - jcr:primaryType = "cq:PageContent"
 *    - jcr:title = "We.Retail"
 *    - sling:resourceType = "weretail/components/structure/page"</pre>
 * <p>Unlike Sling, JCR has node type definitions. Here is the definition of <code>cq:PageContent</code>:</p>
 * <pre>
 *[cq:PageContent] &gt; cq:OwnerTaggable, cq:ReplicationStatus, mix:created, mix:title, nt:unstructured, sling:Resource, sling:VanityPath
 *  orderable
 *  - cq:lastModified (date)
 *  - cq:template (string)
 *  - pageTitle (string)
 *  - offTime (date)
 *  - hideInNav (boolean)
 *  - cq:lastModifiedBy (string)
 *  - onTime (date)
 *  - jcr:language (string)
 *  - cq:allowedTemplates (string) multiple
 *  - cq:designPath (string)
 *  - navTitle (string)</pre>
 * <p>In addition to Sling's resource type, Sling Type System allows to model the DataTypes based on JCR node types as well.
 * This is done by also implementing and registering PropertyProvider with the appropriate OSGi service property.</p>
 * <p>For example for <code>cq:PageContent</code> primary node type:</p>
 * <pre>
 *&#64;Component(
 *    property = {
 *        PropertyProvider.PROPERTY_NODE_TYPE + "=cq:PageContent"
 *    }
 *)
 *public class PageContentPropertyProvider implements PropertyProvider {
 *    &#64;Override
 *    &#64;NotNull
 *    public List&lt;&#64;NotNull Property&gt; getProperties() {
 *        // Return the list of properties for `cq:lastModified`, `cq:template`, `pageTitle`, etc
 *    }
 *}</pre>
 * <p>For example for <code>mix:created</code> mixin node type:</p>
 * <pre>
 *&#64;Component(
 *    property = {
 *        PropertyProvider.PROPERTY_NODE_TYPE + "=mix:created"
 *    }
 *)
 *public class CreatedPropertyProvider implements PropertyProvider {
 *    &#64;Override
 *    &#64;NotNull
 *    public List&lt;&#64;NotNull Property&gt; getProperties() {
 *        // Return the list of properties for `jcr:created`, `jcr:createdBy`
 *    }
 *}</pre>
 *
 * <h2>DataTypes Using Marker Resources and Properties</h2>
 * <p>Content modelers often use marker resources and properties to associate the resources with certain concepts.</p>
 * <p>For example, a module may use <code>example:cartPage</code> property to indicate integration with the existing page resource:</p>
 * <pre>
 *+ /content/we-retail/language-masters/en/jcr:content
 *  - example:cartPage = "/content/we-retail/language-masters/en/user/cart"
 *  - jcr:primaryType = "cq:PageContent"
 *  - jcr:title = "English"
 *  - sling:resourceType = "weretail/components/structure/page"</pre>
 * <p>So here <code>example:cartPage</code> acts as the marker property to establish typing for that module.</p>
 * <p>Likewise, the following uses <code>example:config</code> subresource as the marker:</p>
 * <pre>
 *+ mycontent
 *  - sling:resourceType = "example/components/page"
 *  + example:config
 *    - prop1 = "value1"</pre>
 * <p>PropertyProvider can then be implemented and registered with the appropriate OSGi service property:</p>
 * <pre>
 *&#64;Component(
 *    property = {
 *        PropertyProvider.PROPERTY_MARKER_PROPERTY + "=example:cartPage"
 *    }
 *)
 *public class CartPagePropertyProvider implements PropertyProvider {
 *    &#64;Override
 *    &#64;NotNull
 *    public List&lt;&#64;NotNull Property&gt; getProperties() {
 *        // Return the list of properties for `example:cartPage`
 *    }
 *}
 *
 *&#64;Component(
 *    property = {
 *        PropertyProvider.PROPERTY_MARKER_RESOURCE + "=example:config"
 *    }
 *)
 *public class ConfigPropertyProvider implements PropertyProvider {
 *    &#64;Override
 *    &#64;NotNull
 *    public List&lt;&#64;NotNull Property&gt; getProperties() {
 *        // Return the list of the relevant properties for `example:config`
 *    }
 *}</pre>
 *
 * <h2>Modeling the Underlying Platform's Typing System</h2>
 * <p>By establishing the DataTypes this way, we are essentially saying that we model the data type system of the underlying platform.
 * Then the types are used to drive the resources and other parts of the application.</p>
 * <p>For example, if we have the following DataTypes definitions:</p>
 * <ul>
 * <li><code>/libs/example/components/page</code> resource type having <code>prop1</code> and <code>prop2</code> properties</li>
 * <li><code>mix:created</code> node type having <code>jcr:created</code> and <code>jcr:createdBy</code> properties</li>
 * </ul>
 * <p>Then when we have the following resource:</p>
 * <pre>
 *+ myresource
 *  - sling:resourceType = "/libs/example/components/page"
 *  - jcr:mixinTypes = ["mix:created"]
 *  - prop1 = "value1"
 *  - prop2 = "value2"
 *  - jcr:created = "2018-03-06T11:03:28.879+08:00"
 *  - jcr:createdBy = "admin"</pre>
 * <p>The DataType of the resource will have <code>prop1</code>, <code>prop2</code>, <code>jcr:created</code>, and <code>jcr:createdBy</code> properties.
 * The resource itself is also allowed to have those properties as defined by the DataType.</p>
 */
@Version("0.1")
package org.apache.sling.types.data;

import org.osgi.annotation.versioning.Version;
