plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.squareup.anvil")
}

android {
    namespace = "com.plusmobileapps.sample.workflow.characters"
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":di"))
    implementation(project(":rickandmorty-api"))
    implementation(project(":util:coroutines"))

    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)
    implementation(libs.workflow.core.android)
    implementation(libs.workflow.compose)
    testImplementation(libs.workflow.testing)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling)

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += arrayOf(
            "-opt-in=com.squareup.workflow1.WorkflowExperimentalApi",
            "-opt-in=com.squareup.workflow1.ui.WorkflowUiExperimentalApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"        )
    }
}