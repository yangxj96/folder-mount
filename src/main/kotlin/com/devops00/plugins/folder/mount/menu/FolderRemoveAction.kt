package com.devops00.plugins.folder.mount.menu

import com.devops00.plugins.folder.mount.helper.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.state.FolderMountState
import com.devops00.plugins.folder.mount.tree.FolderNode
import com.devops00.plugins.folder.mount.tree.TreeNode
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project


/**
 * 移除目录菜单
 */
class FolderRemoveAction(private val project: Project, private val node: FolderNode)
    : AnAction(I18nBundle.message("menus.remove")) {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"


    override fun actionPerformed(p0: AnActionEvent) {
        logger.debug("${prefix}卸载目录 ${node.name}")
        FolderMountState.getInstance(project).removeFolder(node.file.path)
        TreeNode.instance?.refreshTree()
    }
}