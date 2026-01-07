package com.devops00.plugins.folder.mount.menu

import com.devops00.plugins.folder.mount.helper.NotifierHelper
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.tree.FolderNode
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project


/**
 * 新建文件夹菜单
 */
class FileNewDirAction(private val project: Project, private val node: FolderNode) : AnAction(
    I18nBundle.message("menus.file.newdir"),
    "",
    AllIcons.Nodes.Folder
) {

    override fun actionPerformed(p0: AnActionEvent) {
        NotifierHelper.info(project, "${node.name}未实现创建目录")
    }

}