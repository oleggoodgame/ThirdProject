package com.example.thirdproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdproject.databinding.FileDownloadBinding

class ListAdapterDownload(
    private val viewModel: ViewModelFile,
    private val onItemClick: (Int, File) -> Unit
) : ListAdapter<File, ListAdapterDownload.FileHolder>(FileDiffUlti()) {

    private var items: MutableList<File> = mutableListOf()

    class FileHolder(item: View, val onItemClick: (Int, File) -> Unit) :
        RecyclerView.ViewHolder(item) {
        val binding = FileDownloadBinding.bind(item)

        fun bind(position: Int, file: File) = with(binding) {
            imageView.setImageResource(file.image)
            textView1.text = file.title
            textView2.text = file.description
            relative.setOnClickListener() {
                onItemClick(position, file)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.file_download, parent, false)
        return FileHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        holder.bind(position, items[position])
    }

    fun addItem(item: File) {
        items.add(item)
        submitList(ArrayList(items))
    }

    fun addAllItems(all_items: List<File>) {
        items.clear()
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
            items.removeAt(index)
            submitList(ArrayList(items))

        }

    }
}
class FileDiffUlti : DiffUtil.ItemCallback<File>() {
    override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem == newItem
    }
}
