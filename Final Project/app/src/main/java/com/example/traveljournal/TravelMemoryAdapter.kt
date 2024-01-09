package com.example.traveljournal

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.traveljournal.data.TravelMemory

class TravelMemoryAdapter(private var travelMemories: MutableList<TravelMemory>) :
    RecyclerView.Adapter<TravelMemoryAdapter.TravelMemoryViewHolder>() {

    class TravelMemoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeNameTextView: TextView = itemView.findViewById(R.id.placeNameTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelMemoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_travel_memory, parent, false)
        return TravelMemoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TravelMemoryViewHolder, position: Int) {
        val currentMemory = travelMemories[position]

        holder.placeNameTextView.text = currentMemory.placeName
        holder.dateTextView.text = currentMemory.dateOfTravel
    }

    fun updateTravelMemories(newTravelMemories: MutableList<TravelMemory>) {
        travelMemories = newTravelMemories
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return travelMemories.size
    }

}
