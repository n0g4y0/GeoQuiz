package io.github.n0g4y0.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizModelView"

class QuizViewModel : ViewModel() {

    init {
        Log.d(TAG,"ViewModel instance created. ")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"ViewModel instance about to be destroyed")
    }
}