plugins {
    id("java")
}

group = "org.study"
version = "1.0-SNAPSHOT"

val log4jVersion = "2.25.0"
val jUnitVersion = "5.10.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:$jUnitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
}


tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.study.view.AppUI"
    }
}

tasks.test {
    useJUnitPlatform()
}