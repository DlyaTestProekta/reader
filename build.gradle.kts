val springBootStarterVer: String by project // 3.3.1-SNAPSHOT
val springAdminVer: String by project // 3.2.3
val postgreSQLVer: String by project // 42.7.3
val micrometerPrometheusVer: String by project // 1.12.4
val lombokVer: String by project // 1.18.32
val junitVer: String by project // 1.11.0-M2
val protoCommonVer: String by project // 0.0.1
val grpcVer: String by project // 3.1.0.RELEASE

plugins {
	java
	id("org.springframework.boot") version "3.3.1-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "ru.pachan"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	mavenLocal()
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootStarterVer")
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootStarterVer")
	implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootStarterVer")
	implementation("de.codecentric:spring-boot-admin-starter-client:$springAdminVer")
	implementation("io.micrometer:micrometer-registry-prometheus:$micrometerPrometheusVer")
	implementation("ru.pachan:proto-common:$protoCommonVer")
	implementation("net.devh:grpc-server-spring-boot-starter:$grpcVer")
	runtimeOnly("org.postgresql:postgresql:$postgreSQLVer")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootStarterVer")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitVer")
	compileOnly("org.projectlombok:lombok:$lombokVer")
	annotationProcessor("org.projectlombok:lombok:$lombokVer")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
