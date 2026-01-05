package com.devops00.plugins.folder.mount.i18n

import com.intellij.AbstractBundle
import org.jetbrains.annotations.PropertyKey


/**
 * 国际化
 *
 * @author Jack Young
 * @since 0.0.2
 *
 * Created on 2026/01/05.
 */
object I18nBundle : AbstractBundle("messages.I18nBundle") {

    /**
     * 把国际化key获取对应的值
     *
     * @param key 国际化key
     * @param params 参数
     */
    fun message(@PropertyKey(resourceBundle = "messages.I18nBundle") key: String, vararg params: Any?): String {
        return getMessage(key, *params)
    }
}