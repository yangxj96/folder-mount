package com.devops00.plugins.folder.mount.tree

import com.devops00.plugins.folder.mount.menu.MenuHelper
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.ui.tree.AsyncTreeModel
import com.intellij.ui.tree.StructureTreeModel
import com.intellij.ui.treeStructure.SimpleNode
import com.intellij.ui.treeStructure.SimpleTree
import com.intellij.ui.treeStructure.SimpleTreeStructure
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JComponent
import javax.swing.JScrollPane


/**
 * 默认的树节点
 *
 * @author Jack Young
 * @since 0.0.4
 *
 * Created on 2026/01/06.
 */
class TreeNode(project: Project) : SimpleTree() {

    companion object {
        // 全局单例引用
        var instance: TreeNode? = null
            private set
    }

    private val rootNode = RootNode(project)

    private val structure = SimpleTreeStructure.Impl(rootNode)

    private val structureModel = StructureTreeModel(structure, null, project)

    private val component: JScrollPane

    init {
        instance = this
        // 初始化自己
        this.isRootVisible = false
        this.showsRootHandles = true
        this.model = AsyncTreeModel(structureModel, project)
        this.component = JScrollPane(this)

        // 初始化监听
        this.addMouseListener(object : MouseAdapter() {
            // 文件被双击
            override fun mouseClicked(e: MouseEvent) {
                if (e.clickCount != 2) return
                val node = this@TreeNode.selectedNode as? FileNode ?: return
                val file = node.file
                if (file.isDirectory) return
                FileEditorManager
                    .getInstance(project)
                    .openFile(file, true)
            }

            // 右键菜单
            override fun mousePressed(e: MouseEvent) {
                MenuHelper.maybeShowPopup(e, this@TreeNode, project)
            }

            // 右键菜单
            override fun mouseReleased(e: MouseEvent) {
                MenuHelper.maybeShowPopup(e, this@TreeNode, project)
            }
        })

    }

    /**
     * 刷新树
     */
    fun refresh() {
        rootNode.initData()
        this.structureModel.invalidateAsync()
    }

    /**
     * 刷新,根据NODE
     */
    fun refresh(node: SimpleNode) {
        this.structureModel.invalidateAsync(node, true)
    }

    /**
     * 获取组件
     */
    fun getComponent(): JComponent {
        return component
    }
}