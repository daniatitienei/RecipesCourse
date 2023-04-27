apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
}