package com.todokanai.baseproject.tools

import com.todokanai.baseproject.abstracts.FileModuleLogics
import com.todokanai.baseproject.tools.independent.dirTree_td
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File

class FileModule(val coroutineDispatcher: CoroutineDispatcher):FileModuleLogics() {

    val dirTree = currentDirectory.map {
        dirTree_td(File(it)).map {
            it.absolutePath
        }
    }

    /** whether currentPath is Accessible **/
    val notAccessible = currentDirectory.map { !isDirectoryValid(it) }

    val listFiles = currentDirectory.map { directory ->
        File(directory).listFiles() ?: emptyArray()
    }
    override suspend fun isDirectoryValid(directory: String): Boolean =
        withContext(coroutineDispatcher) {
            return@withContext File(directory).listFiles() != null
        }
}