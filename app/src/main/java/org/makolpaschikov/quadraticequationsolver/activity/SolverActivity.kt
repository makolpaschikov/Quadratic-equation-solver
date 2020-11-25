package org.makolpaschikov.quadraticequationsolver.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_solver.*
import org.makolpaschikov.quadraticequationsolver.R
import org.makolpaschikov.quadraticequationsolver.solver.SolverManager

class SolverActivity : AppCompatActivity() {

    /**
     * Creation of activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solver)
        initActivityElements()
    }

    /**
     * Initialization of all elements in the activity
     */
    private fun initActivityElements() {
        supportActionBar?.hide()
        initButtons()
    }

    /**
     * Initializing all equation buttons
     */
    private fun initButtons() {
        sq_x_btn.setOnClickListener {
            equation.setText(equation.text.toString() + getString(R.string.sq_x_btn))
            equation.setSelection(equation.text.length)
        }

        x_btn.setOnClickListener {
            equation.setText(equation.text.toString() + getString(R.string.x_btn))
            equation.setSelection(equation.text.length)
        }

        plus_btn.setOnClickListener {
            equation.setText(equation.text.toString() + getString(R.string.plus_btn))
            equation.setSelection(equation.text.length)
        }

        minus_btn.setOnClickListener {
            equation.setText(equation.text.toString() + getString(R.string.minus_btn))
            equation.setSelection(equation.text.length)
        }

        equally_btn.setOnClickListener {
            equation.setText(equation.text.toString() + getString(R.string.equally_btn))
            equation.setSelection(equation.text.length)
        }

        calculate_btn.setOnClickListener {
            val solver = SolverManager(equation.text.toString())

            if (!solver.doAnalysis()) {
                Log.d("coeffs", solver.getCoefficients().toString())
                return@setOnClickListener
            }

            answer_view.text = solver.doAnswer().toString()
        }
    }

}