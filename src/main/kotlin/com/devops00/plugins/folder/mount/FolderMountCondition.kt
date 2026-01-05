package com.devops00.plugins.folder.mount

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Condition


/**
 * 条件控制
 *
 * @author Jack Young
 * @since 0.0.1
 *
 * Created on 2026/01/05.
 */
class FolderMountCondition : Condition<Project> {

    override fun value(project: Project): Boolean {
        // 控制 ToolWindow 是否显示
        // 例如：默认 Project 不显示
        return !project.isDefault
    }

}