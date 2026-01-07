package com.devops00.plugins.folder.mount.menu

import com.devops00.plugins.folder.mount.tree.FolderNode
import com.devops00.plugins.folder.mount.tree.RootNode
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.Project
import com.intellij.ui.treeStructure.SimpleNode
import com.intellij.ui.treeStructure.SimpleTree
import java.awt.event.MouseEvent


/**
 * 根节点右键菜单
 *
 * @author Jack Young
 * @since 0.0.2
 *
 * Created on 2026/01/05.
 */
object MenuHelper {

    /**
     * 判断是否要弹出菜单
     *
     * @param e 触发的事件
     * @param tree 树
     */
    fun maybeShowPopup(e: MouseEvent, tree: SimpleTree, project: Project) {
        if (!e.isPopupTrigger) {
            return
        }
        // 获取节点
        val node = tree.selectedNode ?: return
        // 创建节点且显示
        createNodeMenus(node, project)
            .component
            .show(tree, e.x, e.y)
    }

    /**
     * 创建RootNode节点的菜单
     *
     * @param node 根节点
     */
    fun createNodeMenus(node: SimpleNode, project: Project): ActionPopupMenu {
        val group = object : ActionGroup() {
            override fun getChildren(e: AnActionEvent?): Array<AnAction> {
                val actions = mutableListOf<AnAction>()
                // 是文件节点则多一个新增文件的节点
                if (node is FolderNode) {
                    actions += FileNewAction(project, node)
                }
                // 是文件节点,且父级是根节点,说明是第一层,添加一个删除菜单
                if (node is FolderNode && node.parent is RootNode) {
                    actions += FolderRemoveAction(project, node)
                }
                // 删除文件或目录
                actions += FileDelAction(project, node)
                // 以后新增菜单只加一行
                return actions.toTypedArray()
            }
        }

        return ActionManager
            .getInstance()
            .createActionPopupMenu(ActionPlaces.UNKNOWN, group)
    }

}