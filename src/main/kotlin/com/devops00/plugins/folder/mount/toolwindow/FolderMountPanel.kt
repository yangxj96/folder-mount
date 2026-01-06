package com.devops00.plugins.folder.mount.toolwindow

import com.devops00.plugins.folder.mount.helper.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.state.FolderMountState
import com.devops00.plugins.folder.mount.tree.TreeNode
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionToolbar
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.CardLayout
import javax.swing.JComponent


/**
 * 面板
 * 
 * @author Jack Young
 * @since 0.0.4
 *
 * Created on 2026/01/06.
 */
class FolderMountPanel(private val project: Project) : JBPanel<FolderMountPanel>(BorderLayout()) {

    private val cardLayout = CardLayout()
    private val centerPanel = JBPanel<JBPanel<*>>(cardLayout)

    private var toolbar: ActionToolbar

    private val treeComponent: TreeNode

    private val emptyComponent: JComponent

    companion object {
        private const val CARD_EMPTY = "empty"
        private const val CARD_TREE = "tree"
    }


    init {
        /* ---------- Toolbar ---------- */
        val actionGroup = ActionManager
            .getInstance()
            // 这里的参数要和plugins.xml中的ID对应
            .getAction(Common.TOOLBAR_ID) as ActionGroup
        toolbar = ActionManager
            .getInstance()
            .createActionToolbar("FolderMountToolbar", actionGroup, true)

        add(toolbar.component, BorderLayout.NORTH)

        /* ---------- 主要内容 ---------- */
        emptyComponent = createEmptyPanel()
        treeComponent = TreeNode(project)
        // 初始化虚拟根节点
        centerPanel.add(emptyComponent, CARD_EMPTY)
        centerPanel.add(treeComponent.getComponent(), CARD_TREE)

        add(centerPanel, BorderLayout.CENTER)

        refreshView()
    }

    /** 根据是否有附加 folder 切换视图 */
    fun refreshView() {
        if (FolderMountState.getInstance(project).folders.isNotEmpty()) {
            cardLayout.show(centerPanel, CARD_TREE)
            toolbar.targetComponent = treeComponent
        } else {
            cardLayout.show(centerPanel, CARD_EMPTY)
            toolbar.targetComponent = emptyComponent
        }
    }

    /**
     * 空状态的UI
     */
    private fun createEmptyPanel() =
        JBPanel<JBPanel<*>>(BorderLayout()).apply {
            border = JBUI.Borders.empty(40)

            add(
                JBLabel(
                    "<html><center>" +
                            "<h3>" + I18nBundle.message("panel.empty.title") + "</h3>" +
                            "<p>" + I18nBundle.message("panel.empty.desc") + "</p>" +
                            "</center></html>"
                ).apply {
                    horizontalAlignment = JBLabel.CENTER
                },
                BorderLayout.CENTER
            )
        }
}