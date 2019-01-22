Spring Boot TDD Example
=======================
The goal of this project to introduce TDD usage with Spring Boot with Mockito. 

- Writing unit tests with mocking dependencies
- Writing controller unit tests
- Configuring test context with Spring, configuring dependency beans

TOC
---
- [1 Our Example Web Service](#1-our-example-web-service) <br/>
- [2 The Structure Of Example Web Application](#2-the-structure-of-example-web-application) <br/>
- [3 How To Mock A Dependency](#3-how-to-mock-a-dependency) <br/>
- [4 How To Test Controller](#4-how-to-test-controller) <br/>

1 Our Example Web Service
-------------------------
Our sample web service is quite straightforward. It's a simple controller with basic CRUD operations.
The [PersonController](https://github.com/bzdgn/spring-boot-tdd-example/blob/master/src/main/java/com/levo/tdd/controller/PersonController.java) class is the main and the only controller class for our simple web application.

As it is obvious by naming, [EntryPoint](https://github.com/bzdgn/spring-boot-tdd-example/blob/master/src/main/java/com/levo/tdd/EntryPoint.java) is the entry point of our spring boot application.

There are two main dependencies. One for spring boot web applications and the other one for the testing
related dependencies. The only dependency needed for testing is **spring-boot-starter-test** dependency.
You should add this dependency wih the **test** scope as can be seen below. The dependencies section of our
[POM file](https://github.com/bzdgn/spring-boot-tdd-example/blob/master/pom.xml) is as below;


```
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

So, if you have an existing Spring Boot project and want to add a testing dependency, you should add this
depencency in your dependencies section of your **POM** file;

```
	<dependencies>
		...
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

[Go back to TOC](#toc)


2 The Structure Of Example Web Application
------------------------------------------
It is important to be aware of the structure of this example web application. It's a simple web service
with basic/trivial CRUD operations. It's components are;

- Controller
- Service
- Repository

To make a primitive graphical representation;

```
	Controller
	   ||
	   ||
           \/
   Service
	   ||
	   ||
           \/
   Repository
```

It is important to be aware of the dependency hierarchy of each components. We are using dependency injection.
The controller can be instantiated if Spring Context has an instance of service component. And the service can
be instantiated if the Spring context has an instance of a repository component as well. This is important because
we need to be aware of that while writing our tests, setting up our testing context and mocking our dependencies.

[Go back to TOC](#toc)


3 How To Mock A Dependency
--------------------------
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>

[Go back to TOC](#toc)


4 How To Test Controller
------------------------
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>
placeholder<br/>

[Go back to TOC](#toc)

