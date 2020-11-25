package org.makolpaschikov.quadraticequationsolver.solver

import kotlin.math.pow
import kotlin.math.sqrt

class EquationSolver {

    fun doAnswer(coefficients: Coefficients): Answer? {
        val d = coefficients.b.pow(2) - 4 * coefficients.a * coefficients.c

        if (d > 0.0) {
            val x1 = (-coefficients.b + sqrt(d)) / (2 * coefficients.a)
            val x2 = (-coefficients.b - sqrt(d)) / (2 * coefficients.a)
            return Answer(coefficients, d, x1, x2)
        }

        if (d == 0.0) {
            val x1 = -coefficients.b / (2 * coefficients.a)
            return Answer(coefficients, d, x1, null)
        }

        if (d < 0.0) {
            return Answer(coefficients, d, null, null)
        }

        return null
    }

}