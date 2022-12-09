package com.example.charactersheetempty.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.charactersheetempty.*
import com.example.charactersheetempty.databinding.FragmentMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


/**
 * A placeholder fragment containing a simple view.
 */
class HomePageFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    private val characterList: MutableList<Character> = mutableListOf()

    //private val characterListViewModel: CharacterViewModel by viewModels {
        //CharacterViewModel.CharacterViewModelFactory((application as CharacterApplication).repository)
    //}

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        // Testing making a character using the code imported
        val testy = Character("Darth Vader", "Human", "Jedi", 2)
        val coolRing = Item(1, "Cool Ring", "Very cool ring.")
        val stats = AddStats(500, 500, 500, 500, 500)
        coolRing.AddPassiveComponent(stats)
        coolRing.AddEquippedComponent(stats)
        coolRing.AddEquippedComponent(AddWeight(13.37f))
        testy.AddItem(coolRing)
        //Log.d("MainActivity", testy.toString())
        testy.EquipItem(0)
        //Log.d("MainActivity", testy.toString())
        val secondTest = Character("Artoria Pendragon", "Human", "Warrior", 15)
        secondTest.AddItem(coolRing)
        characterList.add(testy)
        characterList.add(secondTest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        Log.d("Pages", textView.text.toString())
        val recyclerView: RecyclerView = binding.RecyclerView
        val fab: FloatingActionButton = binding.newBtn
        fab.setOnClickListener { view ->
            if (pageViewModel.getIndex() == 1){
                newCharacterSheet()
            }
            else if (pageViewModel.getIndex() == 2){
                newItemSheet()
            }
            else{
                Snackbar.make(view, "Coming Soon!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

        }
        return root
    }
    //calls the new character sheet function
    fun newCharacterSheet(){
        Log.d("Pages", "CharacterSheet")
        val intent = Intent(activity, NewCharacterActivity::class.java)
        startActivityForResult(intent, 1)
    }

    //calls the new item sheet function
    fun newItemSheet(){
        Log.d("Pages", "ItemSheet")
        val intent = Intent(activity, NewItemActivity::class.java)
        startActivityForResult(intent, 2)
    }

    fun openCharacterSheet(){
        //populate data from room

    }

    fun openItemSheet(){
        //populate data from room

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("HomePageFragment", "JUST GOT BACK HOME :D $resultCode")

        val edit = data?.getBooleanExtra("edit", false)
        val name = data?.getStringExtra("name")
        val race = data?.getStringExtra("race")
        val charClass = data?.getStringExtra("class")
        val level = data?.getIntExtra("level", 1)

        if (resultCode == -1)
            return;

        if (edit == true){
            // Change data to existing character if called from openCharacterSheet()
        }
        else{
            val newChar = level?.let { Character(name, race, charClass, it) }
            if (newChar != null) {
                characterList.add(newChar)
                Log.d("HomePageFragment", "NEW CHARACTER ADDED, LIST SIZE IS NOW: " + characterList.size)
                Log.d("HomePageFragment", newChar.toString())
            }
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): HomePageFragment {
            return HomePageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}