package com.devops00.plugins.folder.mount.menu

import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.ui.treeStructure.SimpleNode


/**
 * 删除文件菜单
 */
class FileDelAction(private val project: Project, private val node: SimpleNode) : AnAction(I18nBundle.message("menus.file.delete")) {

    override fun actionPerformed(p0: AnActionEvent) {
        // 删除文件或者目录
    }

}