import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application").version("8.2.0-alpha09")
    id("org.jetbrains.kotlin.android").version("1.8.21")
    id("com.apollographql.apollo3").version("4.0.0-alpha.1")
}

apollo {
    service("api") {
        packageName.set("com.apollographql.demo.api")
    }
}

android {
    namespace = "com.apollographql.demo"

    compileSdk = 33

    defaultConfig {
        minSdk = 26
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<KotlinCompile> {
    this.compilerOptions {
        this.jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.apollographql.apollo3:apollo-compose-support")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc05")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
