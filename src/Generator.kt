import java.util.*

val random = Random(100)

fun rand() = (random.nextDouble() * 100).toInt()

fun generateSymmetric(size: Int): Matrix {
    while (true) {
        val matrix = generateRandom(size, size)
        (0 until matrix.columnDimension).forEach { i ->
            (i + 1 until matrix.columnDimension).forEach { j ->
                matrix[i, j] = matrix[j, i]
            }
        }
        assert(matrix == matrix.transpose())
        if (matrix.determinant() > 0) return matrix
    }
}

fun generateDiagonallyDominant(size: Int): Matrix {
    val matrix = generateRandom(size, size)
    (0 until matrix.rowDimension).forEach { i ->
        val j = (0 until matrix.columnDimension).sumByDouble { j -> matrix[i, j] }
        matrix[i, i] = j + rand()
    }
    return matrix
}

fun generateBad(size: Int): Matrix {
    val result = Matrix(size, size)
    for (i in 0 until size) {
        for (j in 0 until size) {
            result.set(i, j, (1.0 / (i + j + 1)))
        }
    }
    return result
}

fun generateRandom(n: Int, m: Int): Matrix {
    val result = Matrix(n, m)
    for (i in 0 until n) {
        for (j in 0 until m) {
            val a = rand()
            val b = rand()
            result.set(i, j, "$a.$b".toDouble())
        }
    }
    return result
}