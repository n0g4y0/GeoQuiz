package io.github.n0g4y0.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        trueButton.setOnClickListener { view: View ->

            mostrarMsj(R.string.correct_toast)
        }

        falseButton.setOnClickListener { view: View ->
            mostrarMsj(R.string.incorrect_toast)
        }



    }

    private fun mostrarMsj(msjID: Int){
        val msj = Toast.makeText(this, msjID, Toast.LENGTH_SHORT)
        // con este metodo, cambio la direccion donde se mostrar el TOAST
        msj.setGravity(Gravity.TOP,0,150)
        msj.show()
    }
}
