package org.makolpaschikov.quadraticequationsolver.solver

import java.lang.StringBuilder
import kotlin.math.pow

class Answer(
        private val coefficients: Coefficients,
        private val d: Double,
        private val x1 : Double?,
        private val x2 : Double?
        ) {

    override fun toString(): String {
        val answer = StringBuilder()
        val a = getStringOfCoefficient(coefficients.a)
        val b = getStringOfCoefficient(coefficients.b)
        val c = getStringOfCoefficient(coefficients.c)

        answer.append("a = ${coefficients.a}, b = ${coefficients.b}, c = ${coefficients.c} \n\n")
                .append("D = b² - 4ac \n")
                .append("D = ${coefficients.b.pow(2.0)} - 4•$a•$c = $d \n\n")

        if (d > 0.0) {
            answer.append("x = (-b ± √D) / (2•a) \n")
                    .append("x = (-$b ± √$d) / (2•$a) \n\n")
                    .append("x₁ = $x1 \n")
                    .append("x₂ = $x2 \n")
        }

        if (d == 0.0) {
            answer.append("x = -b / 2 • a \n")
                    .append("x = -$b / (2•$a) \n\n")
                    .append("x = $x1 \n")
        }

        if (d < 0.0) {
            answer.append("Discriminant less than 0 means no roots")
        }

        return answer.toString()
    }

    private fun getStringOfCoefficient(value: Double): String {
        return if (value < 0)
            "($value)"
        else
            value.toString()
    }
}