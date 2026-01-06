package com.devops00.plugins.folder.mount.menu

import com.devops00.plugins.folder.mount.helper.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.state.FolderMountState
import com.devops00.plugins.folder.mount.tree.FolderNode
import com.devops00.plugins.folder.mount.tree.RootNode
import com.devops00.plugins.folder.mount.tree.TreeNode
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
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

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

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
        // 当前是FolderNode且父级是RootNode
        if (node is FolderNode && node.parent is RootNode) {
            createRootNodeMenus(node, project)
                .component
                .show(tree, e.x, e.y)
        }
    }

    /**
     * 创建RootNode节点的菜单
     *
     * @param node 根节点
     */
    fun createRootNodeMenus(node: FolderNode, project: Project): ActionPopupMenu {
        val group = object : ActionGroup() {
            override fun getChildren(e: AnActionEvent?): Array<AnAction> {
                return arrayOf(object : AnAction(I18nBundle.message("menus.remove")) {
                    override fun actionPerformed(e: AnActionEvent) {
                        logger.debug("${prefix}卸载目录 ${node.name}")
                        FolderMountState.getInstance(project).removeFolder(node.file.path)
                        TreeNode.instance?.refreshTree()
                    }
                })
            }
        }

        return ActionManager
            .getInstance()
            .createActionPopupMenu(Common.MENU_ROOT_NODE, group)
    }

}