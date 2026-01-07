package com.devops00.plugins.folder.mount.actions

import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.state.FolderMountState
import com.devops00.plugins.folder.mount.tree.TreeNode
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor

/**
 * 添加
 *
 * @author Jack Young
 * @since 0.0.4
 *
 * Created on 2026/01/06.
 */
class ActionFolderAttach : AnAction(I18nBundle.message("menus.add")) {


    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val descriptor = FileChooserDescriptor(
            false, true, false,
            false, false, false
        )
        val folder = FileChooser.chooseFile(descriptor, project, null) ?: return
        FolderMountState.getInstance(project).addFolder(folder.path)
        TreeNode.instance?.refresh()
    }
}