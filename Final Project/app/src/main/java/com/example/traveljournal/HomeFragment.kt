package com.example.traveljournal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveljournal.data.TravelMemory

class HomeFragment : Fragment(), AddTravelMemoryFragment.AddMemoryListener {

    private val travelMemories = mutableListOf(
        TravelMemory("Paris", "2022-01-01", true),
        TravelMemory("New York", "2022-02-15", false),
    )
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TravelMemoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeFragment", "Testing Log")

        recyclerView = view.findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = TravelMemoryAdapter(travelMemories)
        adapter = TravelMemoryAdapter(travelMemories)
        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        } else {
            adapter.notifyDataSetChanged()
        }

        val addButton: Button = view.findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val addTravelMemoryFragment = AddTravelMemoryFragment()

            if (activity is AddTravelMemoryFragment.AddMemoryListener) {
                addTravelMemoryFragment.setTargetFragment(this, 0)
            }

            requireFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, addTravelMemoryFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onMemoryAdded(memory: TravelMemory) {
        travelMemories.add(memory)
        adapter.updateTravelMemories(travelMemories)
        Log.d("HomeFragment", "Travel Memories after addition: $travelMemories")
    }

}
