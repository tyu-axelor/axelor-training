plugins {
    id 'com.axelor.app'
}

axelor {
    title = 'Axelor DEMO'
}

allprojects {

    group = 'com.axelor'
    version = '1.0.0'

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(11)
        }
    }

    afterEvaluate {
        test {
            useJUnitPlatform()
            beforeTest { descriptor ->
                logger.lifecycle('Running: ' + descriptor)
            }
        }
    }
}

dependencies {
    // add dependencies
    implementation project(':axelor-contact')
}