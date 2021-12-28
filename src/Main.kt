private fun solve(s: String, f: () -> Matrix) {
    try {
        print("$s: ")
        printResult(f())
    } catch (e: NoSolveException) {
        println(s + e.toString())
    }
}

val e = 1e-6

private fun solveAll(a: Matrix, b: Matrix, c: Matrix) {
    solve("G") { solveGaussian(a, b.getColumn(0)) }
    solve("J") { solveJacobi(a, b.getColumn(0), c, e) }
    solve("S") { solveSeidel(a, b.getColumn(0), c, e) }
    solve("C") { solveConjugateGradient(a, b.getColumn(0), c, e) }
}

//private fun getMatrix(file: String): Matrix = MatrixReader.getDoubleMatrix(File("res/double/$file.txt"))

private fun printResult(matrix: Matrix) {
    for (i in 0 until matrix.size) {
        print(matrix.get(i, 0).toString() + " ")
    }
    println()
}

fun main(args: Array<String>) {
    val matrix = Matrix(
            arrayOf(
                    doubleArrayOf(2.0, 1.0),
                    doubleArrayOf(1.0, 7.0)
            )
    )
    println(matrix.show())
  //  val ans = doubleArrayOf(1.0, 2.0, 3.0)
    println("matrix cond : ${matrix.cond}")
   // println(solveGaussian(matrix, ans).show())
    val n = 10
    double(n)
}

fun double(n: Int) {
    val b = generateRandom(n, 1)
    val c = generateRandom(n, 1)
    println("B matrix ${b.show()}")
    println("C matrix ${c.show()}")
    val sym = generateSymmetric(n)
    println("Symmetric matrix ${sym.show()}")
    solveAll(sym, b, c)
    (0 until n).forEach { sym.set(it, it, 1e-6) }
    println(sym.cond)
    println("Bad symmetric matrix ${sym.show()}")
    solveAll(sym, b, c)
    val diag = generateDiagonallyDominant(n)
    println("Diagonally Dominant matrix ${diag.show()}")
    solveAll(diag, b, c)
    val random = generateRandom(n, n)
    println("Random matrix ${random.show()}")
    solveAll(random, b, c)
    val bad = generateBad(n)
    println(bad.cond)
    println("Bad matrix ${bad.show()}")
    solveAll(bad, b, c)
}
