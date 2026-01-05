# Folder Mount

**Folder Mount** 是一个 IntelliJ IDEA 插件，可以让开发者在项目视图中挂载额外的文件夹，方便快速访问和管理。

## 功能

- 在 IDEA 项目视图中挂载一个或多个自定义文件夹
- 挂载的文件夹会显示在树形结构中，类似项目根目录
- 提供 **“挂载文件夹”** 按钮快速选择本地目录
- 支持动态刷新，添加/移除挂载目录

## 安装

### 本地安装

1. 使用 Gradle 打包插件：
   ```bash
   ./gradlew buildPlugin
2. 打开 IDEA → `Settings / Preferences` → `Plugins` → ⚙️ → `Install Plugin from Disk`
3. 选择 `build/distributions/folder.mount-<version>.zip` 文件
4. 重启 IDEA 即可使用

### Marketplace 安装

* 插件 ID：`folder.mount`
* 可以在 [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/29627-folder-mount) 搜索安装（发布后生效）

## 使用方法

1. 打开项目视图，左侧栏会显示 “Virtual Root Node”
2. 点击顶部的 **挂载文件夹** 按钮
3. 选择要挂载的文件夹，插件会将其添加到树形视图中
4. 可以右键目录执行卸载操作（UnMount）

## 贡献

欢迎提交 issue 或 PR，一起完善插件功能。

## 开发者

* 作者: `yangxj96`
* GitHub: [yangxj96/folder-mount](https://github.com/yangxj96/folder-mount)

## License

本项目使用 **Apache-2.0 License**。
