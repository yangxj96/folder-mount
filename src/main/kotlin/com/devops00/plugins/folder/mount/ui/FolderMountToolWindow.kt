package com.devops00.plugins.folder.mount.ui

import com.devops00.plugins.folder.mount.helper.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory


/**
 * 核心类
 *
 * @author Jack Young
 * @since 0.0.1
 *
 * Created on 2026/01/05.
 */
class FolderMountToolWindow : ToolWindowFactory {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        logger.debug("${prefix}插件在${project.name}上创建了")
        // 窗口设置
        // 图标
        toolWindow.setIcon(IconLoader.getIcon("/icons/folderMount.svg", FolderMountToolWindow::class.java))
        // 悬浮窗
        toolWindow.title = I18nBundle.message("plugin.name")
        // 侧边栏标签显示名称
        toolWindow.stripeTitle = I18nBundle.message("plugin.name")

        val panel = FolderMountPanel(project)
        val content = ContentFactory.getInstance().createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
        content.isCloseable = false
    }

}