package com.example.charactersheetempty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NewCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_character_sheet)

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener { view ->
            ReturnToHome()
        }
    }

    fun ReturnToHome(){
        //save changed data

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}