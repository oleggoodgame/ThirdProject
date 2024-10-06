package com.example.thirdproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelFile :ViewModel() {
    val itemsDeleted: MutableList<File> = mutableListOf()

    val itemsAll: MutableList<File> = mutableListOf(
            File(1, R.drawable.first, "Fuflik", "Game suslik", "Very good game suslic"),
            File(2, R.drawable.second, "Pancake", "Game", "Bul bul blblbl pumpkin"),
            File(3, R.drawable.third, "VS", "Mini game", "<Aboba vs Pudge>"),
            File(4, R.drawable.fourth, "Education", "Program python", "Program for learning python "),
            File(5, R.drawable.fifth, "Randomathig", "Random photo", "Create random photo"),
            File(6, R.drawable.sixth, "Punguin", "Program", "Mini virus"),
            File(7, R.drawable.seventh, "Fight", "Boxing", "Bam bam bam"),
            File(8, R.drawable.eighth, "Aye", "Shrek", "SHREK")
        )

    fun addItemAll(item: File) {
        itemsAll.add(item)
    }
    fun deleteAll(item: File) {
        itemsAll.remove(item)
        itemsDeleted.add(item)
    }

    fun all(): Int {
        return itemsAll.size
    }
    fun addItemDeleted(item: File) {
        itemsDeleted.remove(item)
        itemsAll.add(item)
    }

    fun delete(): Int {
        return itemsAll.size
    }
}