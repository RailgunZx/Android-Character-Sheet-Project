package com.example.charactersheetempty

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewCharacterActivity : AppCompatActivity() {

    lateinit var nameText: EditText
    lateinit var classText: EditText
    lateinit var raceText: EditText
    lateinit var levelText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_character_sheet)

        nameText = findViewById(R.id.editTextTextPersonName)
        classText = findViewById(R.id.editTextTextPersonName3)
        raceText = findViewById(R.id.editTextTextPersonName2)
        levelText = findViewById(R.id.editTextNumber2)

        val backBtn = findViewById<Button>(R.id.backBtn)
        //backBtn.setOnClickListener { view ->
            //ReturnToHome()
        //}
        backBtn.setOnClickListener {
            ReturnToHome()
        }
    }

    fun ReturnToHome(){
        //save changed data
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", nameText.text.toString())
        intent.putExtra("race", raceText.text.toString())
        intent.putExtra("class", classText.text.toString())
        intent.putExtra("level", Integer.parseInt(levelText.text.toString()))
        intent.putExtra("edit", false)
        //startActivity(intent)
        setResult(1, intent)
        finish()
    }
}