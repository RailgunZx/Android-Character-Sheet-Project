package com.example.charactersheetempty
import android.icu.text.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.charactersheetempty.CharacterItem
import com.example.charactersheetempty.R
import org.w3c.dom.Text
import java.util.*


class CharacterRecyclerAdapter(val itemClicked:(itemId:Int)->Unit)
    : ListAdapter<CharacterItem, CharacterRecyclerAdapter.CharacterViewHolder>(CharacterItemComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = getItem(position)
        current.id?.let {
            holder.bind(it,current.name,current.race,current._class,current.level)
        }
        holder.itemView.tag = current.id
        holder.itemView.setOnClickListener {
            val itemId = it.tag
            Log.d("com.example.charactersheetempty.CharacterRecyclerAdapter","Character Clicked: " + itemId)
            itemClicked(it.tag as Int)
        }
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val CharacterName: TextView = itemView.findViewById(R.id.itemName)
        private val CharacterRace: TextView = itemView.findViewById(R.id.characterRace)
        private val CharacterClass: TextView = itemView.findViewById(R.id.characterClass)
        private val CharacterLevel:TextView = itemView.findViewById(R.id.characterLevel)



        fun bind(id:Int, name: String?, race: String?, _class: String?, level: Int?) {
            CharacterRace.text = race
            CharacterClass.text = _class
            CharacterName.text = name
            CharacterLevel.text = level.toString()
        }

        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_item_character, parent, false)
                return CharacterViewHolder(view)
            }
        }
    }

    class CharacterItemComparator : DiffUtil.ItemCallback<CharacterItem>() {
        override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
