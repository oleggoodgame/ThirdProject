package com.example.thirdproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thirdproject.databinding.FragmentBlank1Binding
import com.example.thirdproject.databinding.FragmentBlank2Binding
import kotlinx.coroutines.Dispatchers

class BlankFragment2 : Fragment() {

    private var _binding: FragmentBlank2Binding? = null
    private val binding get() = _binding!!
    private lateinit var manager: CoroutineManager
    private lateinit var adapter: ListAdapterDownloaded
    private val dataModel: ViewModelFile by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlank2Binding.inflate(inflater, container, false)
        manager = CoroutineManager(Dispatchers.Default, requireContext())

        adapter = ListAdapterDownloaded(dataModel){file ->
            manager.launchTask(file.title.length, false) {
                adapter.deleteItem(file.id)
                dataModel.addItemDeleted(file)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter.addAllItems(dataModel.itemsDeleted)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


