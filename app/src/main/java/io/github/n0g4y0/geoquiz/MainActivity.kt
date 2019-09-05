package io.github.n0g4y0.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView



    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)



        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            // con esta linea, hace que la lista siga vuelva a cero, cuando llegue al ultimo ITEM:
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        backButton.setOnClickListener {
            if (currentIndex == 0){
                currentIndex = questionBank.size - 1
            }
            else{
                currentIndex = (currentIndex - 1) % questionBank.size
            }

            updateQuestion()
        }

        updateQuestion()

    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer:Boolean){

        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
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
