# Sling Type System

This repository is the source code for the development of the type system for Sling.

## Reading List

* [Explainer](docs/explainer.md)
* <https://cwiki.apache.org/confluence/display/SLING/Sling+Type+System%3A+motivation+and+requirements> - a related effort that might be merged with this
* [Sling dev list discussion, January 2019](https://lists.apache.org/thread.html/6e8e64007d6fc858b76d79aa688192073f986b9d5ecc268404213fb6@%3Cdev.sling.apache.org%3E)

## Modules

The repo is divided into two modules:

1. [org.apache.sling.types.api](org.apache.sling.types.api)

   This is the module providing the API.

2. [org.apache.sling.types.impl](org.apache.sling.types.impl)

   This is the module implementing the API.
   It also contain the example using SlingShot sample content.

## Example

There is an example that defines a new type named [Entity](org.apache.sling.types.impl/src/main/java/org/apache/sling/sample/types/entity)

Then the existing SlingShot sample content is used as the content [to model against the Entity](org.apache.sling.types.impl/src/main/java/org/apache/sling/sample/slingshot/types/impl).

Then finally, the Entity is used to render a simple [HAL response](org.apache.sling.types.impl/src/main/java/org/apache/sling/sample/types/hal/impl).

### Installation

1. Prepare a Sling instance as per <https://sling.apache.org/documentation/getting-started.html>
2. Install both modules
3. Browse to <http://localhost:8080/content/slingshot/users/slingshot1.hal.json>

   Notice that this HAL response has an `info` link to <http://localhost:8080/content/slingshot/users/slingshot1/info.hal.json>.
   It also has a `stream` link to <http://localhost:8080/content/slingshot/users/slingshot1/travel.hal.json>.
