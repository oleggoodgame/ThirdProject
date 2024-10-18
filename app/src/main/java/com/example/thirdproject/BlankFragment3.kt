package com.example.thirdproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thirdproject.databinding.FragmentBlank3Binding

class BlankFragment3 : Fragment() {

    private var _binding: FragmentBlank3Binding? = null
    private val binding get() = _binding!!

    var year1: Int = 0
    var month1: Int = 0
    var day1: Int = 0

    var year2: Int = 0
    var month2: Int = 0
    var day2: Int = 0

    var total1: Int = 0
    var total2: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlank3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            year1 = year
            month1 = month + 1
            day1 = dayOfMonth
        }

        binding.calendarView2.setOnDateChangeListener { _, year, month, dayOfMonth ->
            year2 = year
            month2 = month + 1
            day2 = dayOfMonth
        }

        binding.buttons.setOnClickListener {
            calculateDifference()
        }
    }

    private fun calculateDifference() {
        total1 = (year1 * 360) + (month1 * 30) + day1
        total2 = (year2 * 360) + (month2 * 30) + day2
        val result = total1 - total2
        binding.textViews.text = "$result днів"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
