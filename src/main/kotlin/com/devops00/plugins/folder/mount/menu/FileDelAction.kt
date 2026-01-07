package com.devops00.plugins.folder.mount.menu

import com.devops00.plugins.folder.mount.helper.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.ui.treeStructure.SimpleNode


/**
 * 删除文件菜单
 */
class FileDelAction(private val project: Project, private val node: SimpleNode)
    : AnAction(I18nBundle.message("menus.file.delete")) {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

    override fun actionPerformed(p0: AnActionEvent) {
        logger.debug("${prefix}删除文件或者目录")
    }

}