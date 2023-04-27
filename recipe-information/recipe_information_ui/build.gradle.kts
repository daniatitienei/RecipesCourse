apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.recipeInformationDomain))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.core))
    "implementation"(Coil.coilCompose)
}