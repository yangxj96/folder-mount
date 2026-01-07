package com.devops00.plugins.folder.mount.menu

import com.devops00.plugins.folder.mount.helper.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.tree.FolderNode
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.ui.CreateFromTemplateDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.util.application


/**
 * 新建文件菜单
 */
class FileNewAction(private val project: Project, private val node: FolderNode) : AnAction(I18nBundle.message("menus.file.new")) {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

    override fun actionPerformed(e: AnActionEvent) {
        logger.info("${prefix}创建文件")

        val targetDir = PsiManager.getInstance(project)
            .findDirectory(node.file) ?: return

        val before = targetDir.virtualFile.children.map { it.name }.toSet()

        val template = FileTemplateManager.getInstance(project)
            .getTemplate("EmptyFile")


        val dialog = CreateFromTemplateDialog(project, targetDir, template, null, null)
        dialog.title = "创建文件"
        dialog.show()

        application.invokeLater {
            val after = targetDir.virtualFile.children

            val created = after.firstOrNull { it.name !in before }

            if (created != null) {
                PsiManager.getInstance(project).findFile(created)?.let {
                    // 这里刷新进行无感刷新
                }
            }
        }

    }

}