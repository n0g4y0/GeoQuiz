package io.github.n0g4y0.geoquiz

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.Exception

// agregando una variable constante, para trabajar con los LOGs:

private const val TAG = "MainActivity"

//
private const val KEY_INDEX = "index"

// codigo para obtener un valor, usado por la funcion startActivityForResult(intent,int)
private  const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex



        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        nextButton = findViewById(R.id.next_button)

        cheatButton = findViewById(R.id.cheat_button)

        questionTextView = findViewById(R.id.question_text_view)



        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            // con esta linea, hace que la lista siga vuelva a cero, cuando llegue al ultimo ITEM:
            quizViewModel.moveToNext()
            updateQuestion()
        }

        cheatButton.setOnClickListener {view ->
            // Start cheatActivity
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity,answerIsTrue)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // este condigo funciona para personalizar el como quieres que se inicie el ACTIVITY
                // en este caso, utiliza una animacion de revelacion.
                val options = ActivityOptions
                    .makeClipRevealAnimation(view,0,0,view.width,view.height)
                startActivityForResult(intent, REQUEST_CODE_CHEAT,options.toBundle())
            }
            else{
                startActivityForResult(intent, REQUEST_CODE_CHEAT )
            }


        }

        updateQuestion()

    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK){
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause() called")

    }

    /*
    * SOBRE-escribimos esta funcion, para enviar el valor de -> currenIndex, en el BUNDLE, la CONSTANTE es la
    * llave CLAVE para recuperar el valor.
    * */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }


    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer:Boolean){

        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = when{
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

}

/*
* hasta este punto, debemos ya podemos correr el programa, en el emulador o el dispositivo:
* mas documentacion sobre eso: fijarse en:
*
* https://developer.android.com/studio/run/device
*
* */

/*
* la distribucion de versiones de android, que cambian mes a mes:
* https://developer.android.com/about/dashboards/index.html
*
* las versiones de android, para hacer declaraciones condicionales:
*
* https://developer.android.com/reference/android/os/Build.VERSION_CODES.html
*
* documentacion, para ver los potenciales problemas que se pueden encontrar en cada version de android:
* https://developer.android.com/reference/android/os/Build.VERSION_CODES.html
*
* documentacion principal de android:
*
* https://developer.android.com/
*
* */
