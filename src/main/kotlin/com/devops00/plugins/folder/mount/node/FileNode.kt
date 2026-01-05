package com.devops00.plugins.folder.mount.node

import andel.intervals.impl.Impl.Zipper.Companion.isRoot
import com.devops00.plugins.folder.mount.constant.Common
import com.intellij.ide.projectView.PresentationData
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.Iconable
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.treeStructure.SimpleNode
import com.intellij.util.IconUtil

/**
 * 文件节点
 *
 * @param project 上下文
 * @param file 虚拟文件信息
 * @param root 根节点信息
 * @param isRoot 是否显示root
 * @author Jack Young
 * @since 0.0.1
 *
 * Created on 2026/01/05.
 */
class FileNode(val file: VirtualFile, private val root: SimpleNode) : SimpleNode(root) {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

    override fun getChildren(): Array<SimpleNode> {
        logger.debug("${prefix}获取下级节点")
        if (!file.isDirectory) return NO_CHILDREN

        return file.children
            .filterNot { it.name == ".git" }
            .map { FileNode(it, this) }
            .toTypedArray()
    }

    override fun getName(): String = file.name

    override fun update(presentation: PresentationData) {
        presentation.presentableText = file.name
        presentation.tooltip = file.path
        // 让 IDEA 自己决定 icon
        presentation.setIcon(IconUtil.getIcon(file, Iconable.ICON_FLAG_READ_STATUS, project))
    }

}
