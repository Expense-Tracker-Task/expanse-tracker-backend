# TASK LINK
    https://gist.github.com/destan/36bb745c645440716a035c72f2ecf1ec

# POSTMAN
    https://www.postman.com/science-astronomer-97868904/workspace/public-workspace

# DATABASE
	PostgreSQL db kullanılmıştır.

 	ARAŞTIRILAN KONULAR:
		DATABASE TABLES NAME CONVENTION: (Plural or Singular):
			https://medium.com/@fbnlsr/the-table-naming-dilemma-singular-vs-plural-dc260d90aaff
	
		DATABASE CONFIG ISSUE:
			https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd
	
		USING SPRING-BOOT WITH DIFFERENT DATABASES:
			https://www.springboottutorial.com/spring-boot-with-mysql-and-oracle
	
	   	WHAT IS H2 DATABASE AND WHY IS IT USUALLY USED FOR:
	        	https://www.baeldung.com/h2-embedded-db-data-storage
	        	It is usually used as embedded database that means each user have their own database on their machine.

# API (SPRING BOOT)
	project-name: com.example.expensetrackerbackend
	type: Maven
	JDK: coretto-17 Amazon Coretto Version
	Java: 17
	Packing: Jar

	SELECTED DEPENDECIES ONCE CREATING PROJECT:
		Lombok
		Spring Web
		Spring Security
		Spring Data JPA
		PostgreSQL Driver

	ARAŞTIRILAN KONULAR:
		Hibernate(ORM):
			https://spring.io/projects/spring-data-jpa
			https://spring.io/guides/gs/accessing-data-jpa
	
		ARCHITECTURE:
			https://www.javatpoint.com/spring-mvc-tutorial
			https://www.geeksforgeeks.org/difference-between-spring-mvc-and-spring-boot/
			https://www.baeldung.com/hexagonal-architecture-ddd-spring
			https://www.interviewbit.com/blog/spring-boot-architecture/
			https://www.javatpoint.com/spring-boot-architecture
		
		DEPENDENCIES:
			https://www.educba.com/spring-boot-lombok/
			https://projectlombok.org/features/Data
		
		DEPENDENCY INJECTION:
			https://betterprogramming.pub/the-3-types-of-dependency-injection-141b40d2cebc
	
		SECURITY CONFIG ISSUE:
			https://stackoverflow.com/questions/74683225/updating-to-spring-security-6-0-replacing-removed-and-deprecated-functionality
		
		HOW TO PASS PARAMS IN REQUESTS:
			https://stackoverflow.com/questions/23977629/optional-long-parameter-is-present-but-cannot-be-translated-into-a-null-value
	
		PASS BASIC AUTH PARAMETERS IN POSTMAN:
			https://stackoverflow.com/questions/30502962/testing-spring-security-with-postman
	
		RETURNING ENTITY OR DTO IN SERVICES:
			https://stackoverflow.com/questions/68182984/returning-entity-from-service-method-is-a-bad-practice
	
		MODELMAPPER TO MAP ENTITIES TO DTO MODELS:
			https://modelmapper.org/getting-started/
	
		AUTHENTICATION:
			https://www.toptal.com/spring/spring-security-tutorial
			https://www.baeldung.com/spring-boot-api-key-secret
			https://www.youtube.com/watch?v=KxqlJblhzfI (auth yapısını kurmamda çoğunluk olarak bu video yardımcı oldu)
		
		VALIDATION:
			https://www.baeldung.com/spring-boot-bean-validation
		
		ALLOWING CORS WITH AUTHENTICATED REQUESTS:
			https://stackoverflow.com/questions/76682586/allow-cors-with-spring-security-6-1-1-with-authenticated-requests/77352249#77352249
		
	REVIEWED PROJECT:
		https://github.com/AliAnilKocak/FocusTodoSpringBoot
