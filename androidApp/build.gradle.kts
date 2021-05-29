plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.core:core-ktx:${Versions.core}")
    implementation("com.google.android.material:material:${Versions.material}")

    // JetPack Compose
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:${Versions.compose}")
    // Material Design
    implementation("androidx.compose.material:material:${Versions.compose}")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:${Versions.compose}")
    //implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    // Integration with activities
    implementation("androidx.activity:activity-compose:${Versions.activity}")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.compose}")

    // ReduxKotlin
    implementation("org.reduxkotlin:redux-kotlin-threadsafe:${Versions.reduxKotlin}")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
}

android {
    compileSdk = Versions.targetSdkVersion

    defaultConfig {
        applicationId = "com.sfkit.androidApp"
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = 1
        versionName = "0.1"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xallow-jvm-ir-dependencies"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}