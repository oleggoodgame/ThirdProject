package com.example.thirdproject

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList

class CoroutineManager(private val dispatcher: CoroutineDispatcher, private val context: Context) : CoroutineScope {

    private val job = Job()  // Відповідає за життєвий цикл корутин
    private val taskQueue: LinkedList<() -> Unit> = LinkedList()  // Черга завдань
    private var isTaskRunning = false
    // Визначаємо контекст з переданим dispatcher + Job
    override val coroutineContext = dispatcher + job

        fun launchTask(time: Int, fifi: Boolean, onComplete: () -> Unit) {
        if (isTaskRunning) {
            Toast.makeText(
                context,
                "Вибачте, завдання вже виконується. Будь ласка, зачекайте і знов натисніть",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        isTaskRunning = true
        launch {
            // Викликаємо Toast на головному потоці
            if(fifi)
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Починає скачуватись на: ${Thread.currentThread().name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Починає видалятись на: ${Thread.currentThread().name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            withContext(dispatcher) {
                if(fifi){
                    delay(time.toLong()*500)
                }else{
                    delay(time.toLong()*300)

                }
                println("Running on ${Thread.currentThread().name}")
                onComplete()
            }

            // Показуємо Toast знову на головному потоці
//            withContext(Dispatchers.Main) {
//                Toast.makeText(
//                    context,
//                    "ДОСІ ПРАЦЮЄ",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
        }
            isTaskRunning = false
    }

    fun cancelTasks() {
        job.cancel()
    }
}
