package com.example.charactersheetempty

import androidx.annotation.WorkerThread
import androidx.lifecycle.Transformations
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val toDoListDao: CharacterListDao) {


    val allCharacters: Flow<Map<Int,CharacterItem>> = toDoListDao.getToDoItems()


    @Suppress("RedudndantSuspendModifier")
    @WorkerThread
    suspend fun insert(toDoItem: CharacterItem){
        toDoListDao.insert(toDoItem)
    }

    @Suppress("RedudndantSuspendModifier")
    @WorkerThread
    suspend fun getCharacter(toDoId: Int): CharacterItem {
        return toDoListDao.getItem(toDoId)
    }


    @Suppress("RedudndantSuspendModifier")
    @WorkerThread
    suspend fun delete(id: Int) {
        toDoListDao.deleteItem(id)
    }

    @Suppress("RedudndantSuspendModifier")
    @WorkerThread
    suspend fun update(toDoItem: CharacterItem) {
        toDoListDao.updateItem(toDoItem)
    }

}