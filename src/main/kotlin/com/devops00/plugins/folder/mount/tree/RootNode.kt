package com.devops00.plugins.folder.mount.tree

import com.devops00.plugins.folder.mount.state.FolderMountState
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsSafe
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.treeStructure.SimpleNode


/**
 * 根节点
 *
 * @author Jack Young
 * @since 0.0.4
 *
 * Created on 2026/01/06.
 */
class RootNode(project: Project) : SimpleNode() {

    /**
     * 文件夹列表
     */
    private val folders: MutableList<VirtualFile> = mutableListOf()

    /**
     * 状态
     */
    private val state = FolderMountState.getInstance(project)

    init {
        initData()
    }

    override fun getName(): @NlsSafe String? {
        return this.javaClass.simpleName
    }

    fun initData() {
        folders.clear()
        state.folders
            .mapNotNull {
                LocalFileSystem.getInstance().findFileByPath(it)
            }
            .forEach { folders += it }
    }

    override fun isAlwaysLeaf(): Boolean = false

    override fun getChildren(): Array<out SimpleNode?> =
        folders.map {
            FolderNode(it, this)
        }.toTypedArray()

}