import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

plugins {
    kotlin("jvm") version "2.2.21"
    id("org.jetbrains.intellij.platform") version "2.10.5"
}

group = "com.devops00.plugins"
version = "0.0.5"

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
                    <h3>0.0.5</h3>
                
                    <h4>中文</h4>
                    <ul>
                        <li>优化右键菜单图标，操作语义更加清晰</li>
                        <li>新增“一键移除所有日志”功能</li>
                        <li>优化新建文件与文件夹流程，避免整棵树刷新，减少界面闪烁</li>
                        <li>改进目录刷新机制，提升性能与整体流畅度</li>
                        <li>右键菜单新增“创建文件夹”操作</li>
                    </ul>
                
                    <h4>English</h4>
                    <ul>
                        <li>Improved right-click menu icons for clearer and more intuitive actions</li>
                        <li>Added a one-click action to remove all logs</li>
                        <li>Optimized file and folder creation to avoid full tree refresh and reduce UI flicker</li>
                        <li>Enhanced directory refresh logic for better performance and smoother experience</li>
                        <li>Added support for creating folders directly from the context menu</li>
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
