package com.example.charactersheetempty

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CharacterApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CharacterRoomDatabase.getDatabase(this,applicationScope)}
    val repository by lazy{ CharacterRepository(database.CharacterListDao())}
}