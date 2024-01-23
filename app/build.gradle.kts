plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.squareup.anvil")
}

android {
    namespace = "com.plusmobileapps.sample.workflow"
    compileSdk = project.properties["android-compilesdk"].toString().toInt()

    defaultConfig {
        applicationId = "com.plusmobileapps.sample.workflow"
        minSdk = project.properties["android-minsdk"].toString().toInt()
        targetSdk = project.properties["android-targetsdk"].toString().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {

    implementation(libs.rick.and.morty.api.android)
    implementation(libs.core.ktx)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.fragment.ktx)

    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)

    implementation(libs.workflow.core.android)
    implementation(libs.workflow.compose)
    testImplementation(libs.workflow.testing)


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.tooling.debug)
    debugImplementation(libs.compose.ui.test.manifest)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += arrayOf(
            "-opt-in=com.squareup.workflow1.ui.WorkflowUiExperimentalApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"        )
    }
}