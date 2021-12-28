
fun solveGaussian(a0: Matrix, b0: DoubleArray): Matrix {
    if (a0.determinant() == 0.0) throw NoSolveException("det = 0")
    val a = a0.copy()
    val b = b0.copyOf()
    val n = a.size
    val result = Matrix(n, 1)
    for (step in 0 until n - 1) {
        val t = a[step, step]
        for (i in step + 1 until n) {
            val tt = a[i, step] / t
            for (j in 0 until n) {
                a[i, j] = a[i, j] - tt * a[step, j]
            }
            b[i] = b[i] - tt * b[step]
        }
    }
    for (step in n - 1 downTo 0) {
        var sum = 0.0
        for (i in step + 1 until n) {
            sum -= a[step, i] * result[i, 0]
        }
        sum += b[step]
        result[step, 0] = sum / a[step, step]
    }
  //  println(result)
    return result
}


