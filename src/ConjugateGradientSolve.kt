
fun scalarTimes(a : Matrix, matrix: Matrix): Double {
    return a.data
            .zip(matrix.data)
            .foldRight(0.0) { x, acc ->
                x.first.zip(x.second)
                        .foldRight(0.0) { y, acc2 ->
                            y.first * y.second + acc2
                        } + acc
            }
}

fun solveConjugateGradient(a0: Matrix, b1: DoubleArray, x0: Matrix, epsilon: Double): Matrix {
    val b0 = Matrix(Array(b1.size) {DoubleArray(1) {0.0}})
    b1.forEachIndexed {index, d -> b0[index, 0] = d }
    if (a0.determinant() < 0 || a0 != a0.transpose()) throw NoSolveException("Doesn't satisfy condition")

    var xkm = x0
    var rkm = b0 - a0 * x0
    var pkm = rkm

    var k = 0
    while (true) {
        k++
        val ak = scalarTimes(rkm, rkm) / scalarTimes(a0 * pkm, pkm)
        val xk = xkm + pkm * ak
        val rk = rkm - a0 * pkm * ak
        val bk = scalarTimes(rk, rk) / scalarTimes(rkm, rkm)
        val pk = rk + pkm * bk

        xkm = xk
        rkm = rk
        pkm = pk

        val d = rk.norm / b0.norm
        if (d < epsilon)
            break
    }
    //println(xkm)
    return xkm
}