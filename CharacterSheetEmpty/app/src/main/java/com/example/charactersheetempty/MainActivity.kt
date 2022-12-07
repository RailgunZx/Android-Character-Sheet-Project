package com.example.charactersheetempty

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.charactersheetempty.ui.main.SectionsPagerAdapter
import com.example.charactersheetempty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val characterList: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
       /* val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        characterList.add("wowed");
        val testy = Character("testing", 2)
        val coolRing = Item(1, "Cool Ring", "Very cool ring.")
        val stats = AddStats(500, 500, 500, 500, 500)
        coolRing.AddPassiveComponent(stats)
        coolRing.AddEquippedComponent(stats)
        coolRing.AddEquippedComponent(AddWeight(13.37f))
        testy.AddItem(coolRing)
        Log.d("MainActivity", testy.toString())
        testy.EquipItem(0)
        Log.d("MainActivity", testy.toString())
        testy.RemoveItem(coolRing)
        Log.d("MainActivity", testy.toString())
    }
}