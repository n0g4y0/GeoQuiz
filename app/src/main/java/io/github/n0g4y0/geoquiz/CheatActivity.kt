package io.github.n0g4y0.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN = "io.github.n0g4y0.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "io.github.n0g4y0.geoquiz.answer_is_true"
class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView : TextView
    private lateinit var showAnswerButton : Button

    private lateinit var showAPILevelTextView : TextView

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        showAPILevelTextView = findViewById(R.id.show_api_level)
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }

        /*
        * Para completar el desafio de mostrar el NIVEL del API del O.S del dispositivo:
        * */
        showAPILevelTextView.setText("API LEVEL ${Build.VERSION.SDK_INT}")


    }
    private fun setAnswerShownResult(isAnswerShown: Boolean){
            val data = Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
            }
            setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext,CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
