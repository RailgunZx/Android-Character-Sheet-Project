package com.example.charactersheetempty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_item_sheet_updated)

        val backBtn = findViewById<Button>(R.id.backBtn2)
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