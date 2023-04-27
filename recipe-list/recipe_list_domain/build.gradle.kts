apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "testImplementation"(Testing.truth)
    "testImplementation"(Testing.junit4)
}