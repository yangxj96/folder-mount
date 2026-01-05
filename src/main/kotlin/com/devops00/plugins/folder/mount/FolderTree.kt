package com.devops00.plugins.folder.mount

import com.devops00.plugins.folder.mount.node.FileNode
import com.devops00.plugins.folder.mount.node.RootNode
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.tree.AsyncTreeModel
import com.intellij.ui.tree.StructureTreeModel
import com.intellij.ui.treeStructure.SimpleTree
import com.intellij.ui.treeStructure.SimpleTreeStructure
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JScrollPane

class FolderTree(private val project: Project) {

    private val logger = Logger.getInstance("FolderMount")

    private val prefix = "[${this.javaClass.simpleName}]:"

    // 根节点
    private val root = RootNode(project)
    private val structure = SimpleTreeStructure.Impl(root)
    private val structureModel = StructureTreeModel(structure, null, project)
    private val tree = SimpleTree(AsyncTreeModel(structureModel, project))
    val component = JScrollPane(tree)

    init {
        logger.debug("${prefix}初始化双击监听和key监听")

        // 监听双击
        tree.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                logger.debug("${prefix}双击文件${tree.selectedNode?.name}")
                if (e.clickCount != 2) return
                openFileEditor()
            }
        })
        // 监听回车
        tree.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                logger.debug("${prefix}回车,激活目标文件:${tree.selectedNode?.name}")
                if (e.keyCode != KeyEvent.VK_ENTER) return
                openFileEditor()
            }
        })
    }

    fun mount(folder: VirtualFile) {
        logger.debug("${prefix}挂载文件")
        folder.refresh(false, true)
        root.addFolder(folder)
        structureModel.invalidateAsync()
    }

    /// 打开到编辑器
    fun openFileEditor() {
        logger.debug("${prefix}打开到编辑器")
        val node = tree.selectedNode as? FileNode
            ?: return

        val file = node.file
        if (file.isDirectory) return

        FileEditorManager
            .getInstance(project)
            .openFile(file, true)
    }

}