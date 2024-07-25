plugins {
    id 'java'
    id 'application'
    id 'checkstyle'
    id 'jacoco'
}

group = 'hexlet.code'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.9.1')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation('org.assertj:assertj-core:3.22.0')
    testImplementation('org.junit.jupiter:junit-jupiter-params:5.10.0-M1')

    implementation 'info.picocli:picocli:4.7.3'
    annotationProcessor 'info.picocli:picocli-codegen:4.7.3'

    implementation 'com.fasterxml.jackson.core:jackson-databind:2.14.2'
    implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2'
}

test {
    useJUnitPlatform()
    finalizedBy jacocoTestReport
}

application {
    mainClass = 'hexlet.code.App'
}

compileJava {
    options.release = 17
    options.compilerArgs += ["-Aproject=${project.group}/${project.name}"]
}

jacocoTestReport {
    reports {
        xml.required = true
    }
}