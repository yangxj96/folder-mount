package com.devops00.plugins.folder.mount.helper

import com.devops00.plugins.folder.mount.i18n.I18nBundle
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.project.Project


/**
 * 通知管理
 * 
 * @author Jack Young
 * @since 0.0.4
 *
 * Created on 2026/01/06.
 */
object NotifierHelper {

    fun info(project: Project, message: String) {
        Notifications.Bus.notify(
            Notification(
                Common.GROUP_ID,
                I18nBundle.message("plugin.name"),
                message,
                NotificationType.INFORMATION
            ),
            project
        )
    }

    fun error(project: Project, message: String) {
        Notifications.Bus.notify(
            Notification(
                Common.GROUP_ID,
                "Title",
                message,
                NotificationType.ERROR
            ),
            project
        )
    }
}
