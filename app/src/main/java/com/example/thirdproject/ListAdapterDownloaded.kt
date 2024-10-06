package com.example.thirdproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdproject.databinding.FileDownloadedBinding

class ListAdapterDownloaded(
    private val viewModel: ViewModelFile,
    private val onItemClick: (File) -> Unit
) : ListAdapter<File, ListAdapterDownloaded.FileHolder>(FileDiffUlti2()) {
    private val items: MutableList<File> = mutableListOf()

    class FileHolder(item: View, val onItemClick: (File) -> Unit) :
        RecyclerView.ViewHolder(item) {
        val binding = FileDownloadedBinding.bind(item)

        fun bind(file: File) = with(binding) {
            imageView2.setImageResource(file.image)
            textView12.text = file.title
            textView22.text = file.full_description

            relative2.setOnClickListener {
                onItemClick(file)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_downloaded, parent, false)
        return FileHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        val file = getItem(position)
        holder.bind(file)
    }
    fun addItem(item: File) {
        items.add(item)
        submitList(ArrayList(items))
    }

    fun addAllItems(all_items: List<File>) {
        items.addAll(all_items)
        submitList(ArrayList(items))
    }

    fun deleteItem(id: Int) {
        val index = items.indexOfFirst { it.id == id } // Знаходимо індекс елемента за id
        // Поясни тут краще як працює items.indexOfFirst { it.id == id }
        //indexOfFirst — це метод, який використовується для пошуку індексу першого елемента в списку,
        // що задовольняє умові, зазначеній у лямбда-виразі. У твоєму випадку умова перевіряє, чи id елемента дорівнює заданому id
//Працює O(n) -> трошки не добре треба O(1) -> ця функція зміг найкраще знайти, можливо краще було поставити Map, або щось інше поміняти щоб получити O(1)
        if (index >= 0) {
            val deletedFile = items[index] // Зберігаємо файл для подальшого використання
            items.removeAt(index)
            submitList(ArrayList(items))

        }

    }
}

// Клас DiffUtil для ефективного порівняння елементів списку
class FileDiffUlti2 : DiffUtil.ItemCallback<File>() {
    // Перевіряємо, чи є два елементи ідентичними
    override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem.id == newItem.id
    }

    // Перевіряємо, чи однаковий вміст елементів
    override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem == newItem
    }
}