package com.devops00.plugins.folder.mount.i18n

import com.intellij.AbstractBundle
import org.jetbrains.annotations.PropertyKey


/**
 * 国际化
 */
object I18nBundle: AbstractBundle("messages.I18nBundle") {

    fun message(@PropertyKey(resourceBundle = "messages.I18nBundle") key: String, vararg params: Any?): String {
        return getMessage(key, *params)
    }
}