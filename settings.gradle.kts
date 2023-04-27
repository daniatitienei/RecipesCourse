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
        google()
        mavenCentral()
    }
}

rootProject.name = "Recipes"
include(":app")
include(":recipe-list")
include(":core")
include(":core-ui")
include(":recipe-list:recipe_list_domain")
include(":recipe-list:recipe_list_data")
include(":recipe-list:recipe_list_ui")
include(":recipe-information")
include(":recipe-information:recipe_information_domain")
include(":recipe-information:recipe_information_data")
include(":recipe-information:recipe_information_ui")
