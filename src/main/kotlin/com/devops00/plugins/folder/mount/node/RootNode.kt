package com.devops00.plugins.folder.mount.node

import com.devops00.plugins.folder.mount.constant.Common
import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.devops00.plugins.folder.mount.state.MountedFoldersState
import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.treeStructure.SimpleNode

/**
 * 根节点
 *
 * @param project 项目上下文
 * @param refresh 刷新UI的方法
 * @author Jack Young
 * @since 0.0.1
 *
 * Created on 2026/01/05.
 */
class RootNode(project: Project, private val refresh: () -> Unit) : SimpleNode(project) {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

    private val state = MountedFoldersState.getInstance(project)

    private val mounts = mutableListOf<VirtualFile>()

    init {
        logger.debug("${prefix}初始化状态加载")
        state.folders
            .mapNotNull {
                LocalFileSystem.getInstance().findFileByPath(it)
            }
            .forEach { mounts += it }
    }

    fun addFolder(file: VirtualFile) {
        logger.debug("${prefix}添加挂载文件夹${file.path}")
        // 防止重复添加
        if (mounts.any { it.path == file.path }) return
        mounts += file
        // 添加时候加到存储
        state.folders += file.path
        refresh()
    }

    fun removeFolder(file: VirtualFile) {
        logger.debug("${prefix}移除挂载文件夹${file.path}")
        mounts.removeIf { it.path == file.path }
        state.folders.remove(file.path)
        refresh()
    }

    override fun getChildren(): Array<SimpleNode> =
        mounts.map { FileNode(it, this) }.toTypedArray()

    override fun update(presentation: PresentationData) {
        presentation.presentableText = I18nBundle.message("node.root")
        presentation.setIcon(AllIcons.Nodes.HomeFolder)
    }
}