@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("org.jetbrains.kotlin.kapt")
    id("com.squareup.anvil")
}

android {
    namespace = "com.plusmobileapps.util.coroutines"
    compileSdk = project.properties["android-compilesdk"].toString().toInt()

    defaultConfig {
        minSdk = project.properties["android-minsdk"].toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
}

dependencies {
    api(libs.kotlin.coroutines.core)
    implementation(project(":di"))
    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)
}