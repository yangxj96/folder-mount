package com.devops00.plugins.folder.mount.state

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * 状态存储,项目级的
 *
 * @author Jack Young
 * @since 0.0.1
 *
 * Created on 2026/01/05.
 */
@Service(Service.Level.PROJECT)
@State(name = "FolderMountState", storages = [Storage(StoragePathMacros.WORKSPACE_FILE)])
class FolderMountState : PersistentStateComponent<FolderMountState> {

    // 文件夹列表
    var folders: MutableList<String> = mutableListOf()

    override fun getState(): FolderMountState {
        return this
    }

    override fun loadState(state: FolderMountState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    fun addFolder(path: String) {
        // 不存在则添加
        if (!folders.contains(path)) {
            folders.add(path)
        }
    }

    fun removeFolder(path: String) {
        folders.remove(path)
    }


    companion object {
        fun getInstance(project: Project): FolderMountState =
            project.getService(FolderMountState::class.java)
    }
}