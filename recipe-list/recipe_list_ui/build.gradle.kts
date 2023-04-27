apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.recipeListDomain))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.core))
    "implementation"(Coil.coilCompose)
    "implementation"(Compose.material)
}