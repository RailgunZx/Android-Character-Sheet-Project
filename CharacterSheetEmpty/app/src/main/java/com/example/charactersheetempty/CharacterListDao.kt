package com.example.charactersheetempty

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterListDao {

    @MapInfo(keyColumn="id")
    @Query("SELECT * FROM character_table order by id ASC")
    fun getToDoItems(): Flow<Map<Int,CharacterItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoItem: CharacterItem)

    @Query("DELETE FROM character_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM character_table WHERE id = :id")
    suspend fun getItem(id:Int): CharacterItem

    @Query("DELETE FROM character_table WHERE id=:id")
    suspend fun deleteItem(id: Int)

    @Update
    suspend fun updateItem(characterItem: CharacterItem)

}