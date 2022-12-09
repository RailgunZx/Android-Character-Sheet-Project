package com.example.charactersheetempty

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the CharacterItem class
@Database(entities = arrayOf(CharacterItem::class), version = 1, exportSchema = false)
public abstract class CharacterRoomDatabase : RoomDatabase() {

    abstract fun CharacterListDao(): CharacterListDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CharacterRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterRoomDatabase::class.java,
                    "character_database"
                )
                    .addCallback(CharacterListDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class CharacterListDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.CharacterListDao())
                }
            }
        }

        suspend fun populateDatabase(CharacterListDao: CharacterListDao) {
            // Delete all content here.
            CharacterListDao.deleteAll()

            // Add sample words.
            val CharacterItem = CharacterItem(null,"Bob", "Elf", "Ranger", 2)
            CharacterListDao.insert(CharacterItem)
        }
    }

}