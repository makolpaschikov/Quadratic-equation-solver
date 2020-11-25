package org.makolpaschikov.quadraticequationsolver.solver

class SolverManager(equation: String) {

    private var equationAnalyzer: EquationAnalyzer = EquationAnalyzer(equation)

    private var equationSolver: EquationSolver = EquationSolver()

    fun doAnalysis(): Boolean {
        return equationAnalyzer.doAnalysis()
    }

    fun doAnswer(): Answer? {
        return equationSolver.doAnswer(equationAnalyzer.getCoefficients())
    }

    fun getCoefficients(): Coefficients {
        return equationAnalyzer.getCoefficients()
    }
}