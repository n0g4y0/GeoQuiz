package io.github.n0g4y0.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    /*
    * codigo traido desde el MAinActivity, para que pueda guardarse, al momento de cambiar
    * la configuracion de la APP (ROTACION), ya que el VIEWMODEL sobrevive a la destruccion de la APP,
    * cuando esta subre un cambio de configuracion.
    * */

    // quitandole la palabra reservada "Private", para poder accesar a su actual posicion.
    var currentIndex = 0
    // agregando una variable, que tedira si el usuario hizo trampa (CHEAT):
    var isCheater = false
    var apretoElBoton = false

    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true))

    /*
    * agregando funciones, para retornar valores al MainActivity:
    *
    * */

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }




}