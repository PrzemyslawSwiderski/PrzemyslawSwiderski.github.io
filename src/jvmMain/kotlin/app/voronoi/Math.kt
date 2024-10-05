package app.voronoi


object Math {
    const val PRECISION: Double = 100000.0
    const val EPSILON: Double = 1.0 / PRECISION

    fun sq(o: Double): Double {
        return o * o
    }
}
