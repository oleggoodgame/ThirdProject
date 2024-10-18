package com.example.thirdproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thirdproject.databinding.FragmentBlank1Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlankFragment1 : Fragment() {

    private var _binding: FragmentBlank1Binding? = null
    private val binding get() = _binding!!
    private lateinit var manager: CoroutineManager
    private val dataModel: ViewModelFile by activityViewModels()
    private lateinit var adapter: ListAdapterDownload

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlank1Binding.inflate(inflater, container, false)

        manager = CoroutineManager(Dispatchers.Default, requireContext())
        // можливо щоб не тратити пам'ять якусь перевірку додати
        val text = arguments?.getString("text") ?: null
        val description = arguments?.getString("desc") ?: "Default Description"
        val fullDescription = arguments?.getString("full_desc") ?: "Default Full Description"

        // Перевірка наявності файла перед додаванням
        if (text!=null) {
            val file = File(dataModel.itemsAll.size+1, R.drawable.ic_launcher_foreground, text, description, fullDescription)
            dataModel.itemsAll.add(file)
            Log.d("abe","Видалення елемента з індексом: ${file.id}" )
        }
        //------------
        binding.apply {
            adapter = ListAdapterDownload(dataModel) { _, file ->
                manager.launchTask(file.title.length, true) {
                    adapter.deleteItem(file.id)
                    dataModel.deleteAll(file)
                }
            }

            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = adapter
            adapter.addAllItems(dataModel.itemsAll)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
