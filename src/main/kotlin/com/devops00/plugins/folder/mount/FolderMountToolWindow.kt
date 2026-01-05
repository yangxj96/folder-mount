package com.devops00.plugins.folder.mount

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
 */
class FolderMountToolWindow : ToolWindowFactory {

    private val logger = Logger.getInstance("FolderMount")

    private val prefix = "[${this.javaClass.simpleName}]:"

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        logger.info("${prefix}插件在${project.name}上创建了")

        val panel = JPanel(BorderLayout())

        val tree = FolderTree(project)
        panel.add(tree.component, BorderLayout.CENTER)

        val addBtn = JButton("挂载文件夹")
        addBtn.addActionListener {
            val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
            FileChooser.chooseFile(descriptor, project, null) {
                tree.mount(it)
            }
        }
        panel.add(addBtn, BorderLayout.NORTH)

        val content = ContentFactory.getInstance().createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }

}