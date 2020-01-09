package com.alan.cinq.ui.characters.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alan.cinq.R
import com.alan.cinq.model.Result
import com.alan.cinq.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(private val item : List<Result>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(item[position])

        holder.itemView.layoutItem.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("url", item[position].url)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val name = itemView.item_nameCharacter
        private val gender = itemView.item_gender
//        private val hair = itemView.item_hair_color
//        private val skin = itemView.item_skin_color
//        private val eye = itemView.item_eye_color


        fun bindView(item : Result){
            name.text = item.name
            gender.text = item.gender
//            hair.text = item.hair_color
//            skin.text = item.skin_color
//            eye.text = item.eye_color
        }
    }
}