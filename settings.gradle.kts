pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("testLibs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "FTT"
include(":app")
include(":timetable")
include(":auth")
include(":common")
include(":editlesson")
