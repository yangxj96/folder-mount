package com.devops00.plugins.folder.mount.node

import com.devops00.plugins.folder.mount.constant.Common
import com.devops00.plugins.folder.mount.menu.MenuHelper
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.tree.AsyncTreeModel
import com.intellij.ui.tree.StructureTreeModel
import com.intellij.ui.treeStructure.SimpleTree
import com.intellij.ui.treeStructure.SimpleTreeStructure
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JScrollPane

/**
 * 文件树
 *
 * @param project 项目上下文
 * @author Jack Young
 * @since 0.0.1
 *
 * Created on 2026/01/05.
 */
class FolderTree(private val project: Project) {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

    // 根节点
    private val root = RootNode(project) {
        refresh()
    }
    private val structure = SimpleTreeStructure.Impl(root)
    private val structureModel = StructureTreeModel(structure, null, project)
    private val tree = SimpleTree(AsyncTreeModel(structureModel, project))
    val component = JScrollPane(tree)

    init {
        logger.debug("${prefix}初始化双击监听和key监听")

        // 监听双击
        tree.addMouseListener(object : MouseAdapter() {
            // 双击打开到编辑器主窗口
            override fun mouseClicked(e: MouseEvent) {
                logger.debug("${prefix}双击文件${tree.selectedNode?.name}")
                if (e.clickCount != 2) return
                logger.debug("${prefix}打开到编辑器")
                val node = tree.selectedNode as? FileNode
                    ?: return

                val file = node.file
                if (file.isDirectory) return

                FileEditorManager
                    .getInstance(project)
                    .openFile(file, true)
            }

            // 右键菜单
            override fun mousePressed(e: MouseEvent) {
                MenuHelper.maybeShowPopup(e, tree)
            }

            // 右键菜单
            override fun mouseReleased(e: MouseEvent) {
                MenuHelper.maybeShowPopup(e, tree)
            }
        })
    }

    fun mount(folder: VirtualFile) {
        logger.debug("${prefix}挂载目录${folder.name}")
        folder.refresh(false, true)
        root.addFolder(folder)
    }

    // 刷新界面
    fun refresh() {
        structureModel.invalidateAsync()
    }
}