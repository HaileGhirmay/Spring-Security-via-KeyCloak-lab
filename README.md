# Spring-Security-via-KeyCloak-lab
This project is develped to get my hands dirty with the Spring security using the open-source KeyCloak Authentication and Authorization server being used in the moder microservices projects. I did this as a  fullfilment of my WAA professors recommendataion on the In the todays Security doamin. All the necessary configs and annotations can be seen as follows.

#EnableGlobalMethodSecurity vs EnableWebSecurity
================================================

EnableWebSecurity will provide configuration via HttpSecurity. It's the configuration you could find with <http></http> tag in xml configuration, it allows you to configure your access based on urls patterns, the authentication endpoints, handlers etc...

EnableGlobalMethodSecurity provides AOP security on methods. Some of the annotations that it provides are PreAuthorize, PostAuthorize. It also has support for JSR-250. There are more parameters in the configuration for you

For your needs, it's better to mix the two. With REST you can achieve everything you need only by using @EnableWebSecurity since HttpSecurity#antMatchers(HttpMethod,String...) accepts control over Http methods


#Security Annotations

All of @PreAuthorize, @RolesAllowed and @Secured are annotations which allow to configure method security. They can be applied both on
 individual methods or on class level, 
in the latter case the security constraints will be applied to all methods in the class.

Method-level security is accomplished using Spring AOP proxies.

@PreAuthorize
@PreAuthorize annotation allows to specify access constraints to a method using the Spring Expression Language (SpEL). These constraints are evaluated prior to the
 method being executed and may result in execution of the method being denied if the constraints are not fulfilled. The @PreAuthorize annotation is part of the Spring 
 Security framework.

In order to be able to use @PreAuthorize, the prePostEnabled attribute in the @EnableGlobalMethodSecurity annotation needs to be set to true:

@EnableGlobalMethodSecurity(prePostEnabled=true)
@RolesAllowed

@RolesAllowed annotation has its origin in the JSR-250 Java security standard. This annotation is more limited than the @PreAuthorize annotation because it only
 supports role-based security.

In order to use the @RolesAllowed annotation the library containing this annotation needs to be on the classpath, as it is not part of Spring Security. In addition, 
the jsr250Enabled attribute of the @EnableGlobalMethodSecurity annotation need to be set to true:

@EnableGlobalMethodSecurity(jsr250Enabled=true)
@Secured
@Secured annotation is a legacy Spring Security 2 annotation that can be used to configure method security. It supports more than only role-based security, but does
 not support using Spring Expression Language (SpEL) to specify security constraints. It is recommended to use the @PreAuthorize annotation in new applications over
 this annotation.

Support for the @Secured annotation needs to be explicitly enabled in the @EnableGlobalMethodSecurity annotation using the securedEnabled attribute:

@EnableGlobalMethodSecurity(securedEnabled=true)
Which security annotations allow to use SpEL
The following table shows the support for Spring Expression Language in the security annotations that can be used with Spring Security 5:

╔═════════════════════╦═══════════════════╗
║ Security Annotation ║ Has SpEL Support? ║
╠═════════════════════╬═══════════════════╣
║  @PreAuthorize      ║        yes        ║
╠═════════════════════╬═══════════════════╣
║  @PostAuthorize     ║        yes        ║
╠═════════════════════╬═══════════════════╣
║  @PreFilter         ║        yes        ║
╠═════════════════════╬═══════════════════╣
║  @PostFilter        ║        yes        ║
╠═════════════════════╬═══════════════════╣
║  @Secured           ║        no         ║
╠═════════════════════╬═══════════════════╣
║  @RolesAllowed      ║        no         ║
╚═════════════════════╩═══════════════════╝



In order to Apply keycloack-based-security in spring boot , I have used the following pom file dependencies.
===========================================================================================================
After you have downloaded and installed the KeyCloak distribtion server and set it to start in my desired port:8081 default is 8080(but i reserved this for tomcat)
, I added the following dependencies to my Spring-boot Application.

<dependency>
    <groupId>org.keycloak</groupId>
    <artifactId>keycloak-spring-boot-starter</artifactId>
</dependency>



<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.keycloak.bom</groupId>
      <artifactId>keycloak-adapter-bom</artifactId>
      <version>19.0.0</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
