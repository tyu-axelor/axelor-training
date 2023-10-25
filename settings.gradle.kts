pluginManagement {
    repositories {
        maven {
            url 'https://repository.axelor.com/nexus/repository/maven-public/'
        }
    }
    plugins {
        id 'com.axelor.app' version '6.0.+'
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral() {
            content {
                excludeGroup 'com.axelor'
            }
        }
        maven {
            url 'https://repository.axelor.com/nexus/repository/maven-public/'
        }
        ivy {
            name = "Node.js"
            setUrl("https://nodejs.org/dist/")
            patternLayout {
                artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]")
            }
            metadataSources {
                artifact()
            }
            content {
                includeModule("org.nodejs", "node")
            }
        }
    }
}

rootProject.name = 'axelor-training'

// Include modules
include 'modules:axelor-contact'

apply plugin: com.axelor.gradle.support.DependenciesSupport