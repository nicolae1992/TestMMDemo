import java.util.Properties

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}
val baseUrl: String = localProperties.getProperty("baseUrl") ?: ""

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.library)
    id("kotlin-kapt")
}

android {
    namespace = "com.appenginex.com.network"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        minSdk = 34
        buildConfigField("String", "baseUrl", "\"$baseUrl\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.retrofit.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}

kapt {
    correctErrorTypes = true
}