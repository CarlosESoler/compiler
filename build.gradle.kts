plugins {
    id("java")
}

group = "org.study"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.study.view.AppUI"
    }
}

tasks.test {
    useJUnitPlatform()
}