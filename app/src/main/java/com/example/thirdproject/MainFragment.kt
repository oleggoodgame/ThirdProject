package com.example.thirdproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.thirdproject.databinding.FragmentMainBinding

class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val dataModel: ViewModelFile by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener{
            val navController = findNavController()
            // Переходь на другий фрагмент
            navController.navigate(R.id.action_mainFragment2_to_blankFragment1)
        }
        binding.button2.setOnClickListener{
            val navController = findNavController()
            // Переходь на другий фрагмент
            navController.navigate(R.id.action_mainFragment2_to_blankFragment2)
        }
        binding.button3.setOnClickListener{
            val lists = ArrayList(dataModel.itemsDeleted)
            dataModel.itemsDeleted.clear()
            dataModel.itemsAll.addAll(lists)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
