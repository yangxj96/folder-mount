import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

plugins {
    kotlin("jvm") version "2.2.21"
    id("org.jetbrains.intellij.platform") version "2.10.5"
}

group = "com.devops00.plugins"
version = "0.0.4"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}


dependencies {
    intellijPlatform {
        intellijIdea("2025.3")
        // intellijIdeaCommunity()
    }
}

intellijPlatform {

    pluginConfiguration {
        id = "folder.mount"
        name = "Folder Mount"
        description = "This plugin allows developers to mount additional directories into the project view for easier navigation and management."
        vendor {
            name = "yangxj96"
            email = "yangxj96@gmail.com"
            url = "https://www.devops00.com"
        }
        version = project.version.toString()

        ideaVersion {
            sinceBuild = "253"
        }

        changeNotes = """
            <h3>0.0.4</h3>
            <ul>
                <li>Completely refactored the folder tree implementation for better performance and expandability</li>
                <li>Added a toolbar with actions: attach folder and refresh</li>
                <li>Implemented an empty panel view when no folders are mounted, improving UX</li>
                <li>Enhanced tree behavior for multi-level folder display and incremental refresh</li>
                <li>Minor UI improvements and bug fixes</li>
            </ul>
        """.trimIndent()
    }

    pluginVerification {
        // 使用 recommended() 自动选择 IDE 版本验证
        ides {
            // 自动选择
            //recommended()
            // 指定版本
            create(IntelliJPlatformType.IntellijIdea, "2025.3")
        }

    }


    // 发布使用
    publishing {

    }
}


kotlin {
    jvmToolchain(21)
}
