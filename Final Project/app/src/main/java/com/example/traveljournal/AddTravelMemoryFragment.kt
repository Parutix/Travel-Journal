package com.example.traveljournal

// AddTravelMemoryFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.traveljournal.data.TravelMemory

class AddTravelMemoryFragment : Fragment() {

    private lateinit var editPlaceName: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var addMemoryButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_travel_memory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editPlaceName = view.findViewById(R.id.editPlaceName)
        datePicker = view.findViewById(R.id.datePicker)
        addMemoryButton = view.findViewById(R.id.addMemoryButton)

        addMemoryButton.setOnClickListener {
            val placeName = editPlaceName.text.toString()
            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year

            val newMemory = TravelMemory(placeName, "$year-${month + 1}-$day", false)

            if (activity is AddMemoryListener) {
                (activity as? AddMemoryListener)?.onMemoryAdded(newMemory)
            }

            requireFragmentManager().popBackStack()
        }

    }

    // Interface to communicate with the hosting activity or fragment
    interface AddMemoryListener {
        fun onMemoryAdded(memory: TravelMemory)
    }
}
