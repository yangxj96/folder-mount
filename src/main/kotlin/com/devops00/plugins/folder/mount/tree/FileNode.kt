package com.devops00.plugins.folder.mount.tree

import com.intellij.ide.projectView.PresentationData
import com.intellij.openapi.util.Iconable
import com.intellij.openapi.util.NlsSafe
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.treeStructure.SimpleNode
import com.intellij.util.IconUtil


/**
 * 文件节点
 *
 * @author Jack Young
 * @since 0.0.4
 *
 * Created on 2026/01/06.
 */
class FileNode(val file: VirtualFile, root: SimpleNode) : SimpleNode(root) {

    override fun getName(): @NlsSafe String {
        return file.name
    }

    override fun isAlwaysLeaf(): Boolean = true

    override fun getChildren(): Array<out SimpleNode?> = NO_CHILDREN

    override fun update(presentation: PresentationData) {
        presentation.presentableText = file.name
        presentation.tooltip = file.path
        // 让 IDEA 自己决定 icon
        presentation.setIcon(IconUtil.getIcon(file, Iconable.ICON_FLAG_READ_STATUS, project))
    }
}