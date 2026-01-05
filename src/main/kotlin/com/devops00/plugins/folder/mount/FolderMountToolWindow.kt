package com.devops00.plugins.folder.mount

import com.devops00.plugins.folder.mount.constant.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.node.FolderTree
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JPanel


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
        // 悬浮窗
        toolWindow.title = I18nBundle.message("plugin.name")
        // 侧边栏标签显示名称
        toolWindow.stripeTitle = I18nBundle.message("plugin.name")

        // 面板
        val panel = JPanel(BorderLayout())
        // 树节点
        val tree = FolderTree(project)
        panel.add(tree.component, BorderLayout.CENTER)
        // 添加按钮
        val addBtn = JButton(I18nBundle.message("node.add"))
        addBtn.addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
            FileChooser.chooseFile(descriptor, project, null) {
                tree.mount(it)
            }
        }
        panel.add(addBtn, BorderLayout.NORTH)
        // 添加到面板
        val content = ContentFactory.getInstance().createContent(
            panel, "", false
        )
        content.isCloseable = false
        toolWindow.contentManager.addContent(content)
    }

}