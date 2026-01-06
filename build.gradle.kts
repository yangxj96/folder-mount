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
        description = """
                    Mount external folders into your project and work with them like local directories —
                    no file copying, no symbolic links.
                    
                    将项目外部目录挂载到当前项目中，像本地目录一样进行浏览和编辑，
                    无需复制文件或创建软链接。
                    """.trimIndent()
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
