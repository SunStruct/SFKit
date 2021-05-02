plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.core:core-ktx:${Versions.core}")
    //implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
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
    //implementation("androidx.compose.material:material-icons-core:${Versions.compose}")
    //implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    // Integration with activities
    implementation("androidx.activity:activity-compose:${Versions.activity}")
    // Integration with ViewModels
    //implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha02")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.compose}")

    // TODO remove after upgrade of androidx.compose.runtime:runtime:1.0.0-beta04
    // androidx.compose.runtime:runtime:1.0.0-beta04 depends on previous version but it won't be
    // republished on mavenCentral (it has been published only to jcenter which is deprecated)
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.4")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")

}

android {
    compileSdkVersion(Versions.targetSdkVersion)

    defaultConfig {
        applicationId = "com.sfkit.androidApp"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
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
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}