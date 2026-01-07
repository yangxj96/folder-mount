package com.devops00.plugins.folder.mount.actions

import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.tree.TreeNode
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * 刷新
 *
 * @author Jack Young
 * @since 0.0.4
 *
 * Created on 2026/01/06.
 */
class ActionRefresh : AnAction(I18nBundle.message("menus.refresh")) {

    override fun actionPerformed(e: AnActionEvent) {
        val tree = TreeNode.instance ?: return
        val node = tree.selectedNode
        if (node != null) {
            tree.refresh(node)
        } else {
            tree.refresh()
        }

    }
}