plugins {
    kotlin("multiplatform")
//    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
    id("app.cash.sqldelight") version "2.0.0"
}

kotlin {
    androidTarget()

    jvm("desktop")

//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()
//
//    cocoapods {
//        version = "1.0.0"
//        summary = "Some description for the Shared Module"
//        homepage = "Link to the Shared Module homepage"
//        ios.deploymentTarget = "14.1"
//        podfile = project.file("../iosApp/Podfile")
//        framework {
//            baseName = "shared"
//            isStatic = true
//        }
//        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
//    }

    sourceSets {
        val coroutinesVersion = "1.7.3"
        val sqlDelightVersion = "2.0.0"
        val dateTimeVersion = "0.4.0"
        val mviKotlinVersion = "3.2.1"
        val koinVersion = "3.4.3"
        val koinAndroidxComposeVersion = "3.4.6"
        val ktorVersion = "2.3.4"
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
                implementation("com.arkivanov.mvikotlin:mvikotlin:$mviKotlinVersion")
                implementation("com.arkivanov.mvikotlin:mvikotlin-main:$mviKotlinVersion")
                implementation("app.cash.sqldelight:coroutines-extensions:$sqlDelightVersion")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$mviKotlinVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
                implementation("com.russhwolf:multiplatform-settings:1.0.0")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
                implementation("app.cash.sqldelight:android-driver:2.0.0")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
                implementation(compose.preview)
            }
        }
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            dependencies {
//                implementation("app.cash.sqldelight:native-driver:$sqlDelightVersion")
//            }
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation("app.cash.sqldelight:sqlite-driver:$sqlDelightVersion")
                implementation(compose.preview)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "kz.arctan.admissionadviser.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("kz.arctan")
        }
    }
}