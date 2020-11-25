package org.makolpaschikov.quadraticequationsolver.solver

import androidx.appcompat.app.AppCompatActivity

class EquationAnalyzer(private var equation: String) : AppCompatActivity() {

    private val coefficients = Coefficients()

    /**
     * Starts parsing the [equation]
     */
    fun doAnalysis(): Boolean {
        return analysis()
    }

    /**
     * Analysis of the [equation] and search for [coefficients]
     */
    private fun analysis(): Boolean {
        // 'posRelEq' is position relatively equal (1 if left and -1 if right)
        var posRelEq = 1.0
        var checkStatus: Boolean?
        var coefficient = String()

        for (i in equation.indices) {
            if (equation[i] == '²') continue

            checkStatus = checkVariable(equation, i, coefficient, posRelEq) ?: return false
            if (checkStatus) {
                coefficient = ""
                continue
            }

            checkStatus = checkEqually(equation[i], coefficient, posRelEq) ?: return false
            if (checkStatus) {
                posRelEq = -1.0
                coefficient = ""
                continue
            }

            checkStatus = checkOperator(equation[i], coefficient, posRelEq) ?: return false
            if (checkStatus) {
                coefficient = ""
            }

            coefficient += equation[i]
        }

        if (coefficient.isNotEmpty())
            coefficients.c += posRelEq * (coefficient.toDoubleOrNull() ?: return false)

        return checkCoefficientA()
    }

    /**
     * Checking a [eqSymbols] with the index [i] for being a variable
     */
    private fun checkVariable(eqSymbols: String, i: Int, coefficient: String, posRelEq: Double): Boolean? {
        if (eqSymbols[i] == 'x') {
            val value: Double = if (coefficient.isEmpty() || coefficient.length == 1)
                (coefficient + "1").toDoubleOrNull() ?: return null
            else
                coefficient.toDoubleOrNull() ?: return null

            if (i == eqSymbols.length - 1 || eqSymbols[i + 1] != '²') {
                // Variable is 'x'
                coefficients.b += posRelEq * value
            } else {
                // Variable is 'x²'
                coefficients.a += posRelEq * value
            }
            return true
        }
        return false
    }

    /**
     * Checking the [symbol] to be an equal character
     */
    private fun checkEqually(symbol: Char, coefficient: String, posRelEq: Double): Boolean? {
        if (symbol == '=') {
            if (coefficient.isNotEmpty())
                coefficients.c += posRelEq * (coefficient.toDoubleOrNull() ?: return null)
            return true
        }
        return false
    }

    /**
     * Checking the operators
     */
    private fun checkOperator(symbol: Char, coefficient: String, posRelEq: Double): Boolean? {
        if (symbol == '+' || symbol == '-') {
            if (coefficient.isNotEmpty()) {
                if (coefficient.length == 1) return null
                coefficients.c += posRelEq * (coefficient.toDoubleOrNull() ?: return null)
            }
            return true
        }
        return false
    }

    /**
     * Checks that the coefficient 'a' is not zero (the equation is really square)
     */
    private fun checkCoefficientA(): Boolean {
        return coefficients.a != 0.0
    }

    /**
     * @return the [coefficients]
     */
    fun getCoefficients(): Coefficients {
        return coefficients
    }
}