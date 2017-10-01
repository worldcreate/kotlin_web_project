@file:Suppress("UNCHECKED_CAST")

package jp.snowgirl.test_program.config

import kotlin.reflect.KClass

object DaoFactory {
    fun <T> create(daoInterface: KClass<T>): T where T : Any {
        val implClassName = daoInterface.qualifiedName + "Impl"
        try {
            val implClass = Class.forName(implClassName)
            return implClass.newInstance() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}