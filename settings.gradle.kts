pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestMMDemo"
include(":app")
include(":core:network")
include(":core:model")
include(":core:data")
include(":core:common")
include(":features:dash")
include(":features:transaction")
include(":core:designsystem")
include(":features:creditcard")
