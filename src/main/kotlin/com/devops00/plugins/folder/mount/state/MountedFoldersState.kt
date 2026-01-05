package com.devops00.plugins.folder.mount.state

import com.devops00.plugins.folder.mount.constant.Common
import com.intellij.openapi.components.*
import com.intellij.openapi.diagnostic.Logger
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
@State(
    name = "FolderMountState",
    storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
@Service(Service.Level.PROJECT)
class MountedFoldersState : PersistentStateComponent<MountedFoldersState> {

    private val logger = Logger.getInstance(Common.PLUGIN_ID)

    private val prefix = "[${this.javaClass.simpleName}]:"

    // 文件夹列表
    var folders: MutableList<String> = mutableListOf()

    override fun getState(): MountedFoldersState {
        logger.debug("${prefix}获取状态")
        return this
    }

    override fun loadState(state: MountedFoldersState) {
        logger.debug("${prefix}加载状态")
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(project: Project): MountedFoldersState =
            project.getService(MountedFoldersState::class.java)
    }
}