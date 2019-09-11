package io.github.n0g4y0.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

const val EXTRA_ANSWER_SHOWN = "io.github.n0g4y0.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "io.github.n0g4y0.geoquiz.answer_is_true"

// clave para hallar el valor, si hizo trampa o no:

private const val KEY_IS_CHEAT = "isCheat"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView : TextView
    private lateinit var showAnswerButton : Button

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        val apretoElBoton = savedInstanceState?.getBoolean(KEY_IS_CHEAT, false) ?: false
        if (apretoElBoton){
            setAnswerShownResult(true)
            showAnswerButton.isEnabled = false

        }


        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            showAnswerButton.isEnabled = false
            quizViewModel.apretoElBoton = true
            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    /*
    * guardando el valor ante los futuros cambios de configuraciones (ROTACION DE PANTALLA)
    *
    * */

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_CHEAT,quizViewModel.apretoElBoton)
    }



    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext,CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
