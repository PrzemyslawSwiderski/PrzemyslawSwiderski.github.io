package app.voronoi.graph

import kotlin.math.abs

data class Point(val x: Double, val y: Double) {

    companion object {
        private const val EPSILON = 1e-10
        private const val PRECISION = 100.0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Point) return false

        return abs(x - other.x) <= EPSILON && abs(y - other.y) <= EPSILON
    }

    override fun hashCode(): Int {
        return (x * PRECISION * 31).toInt() + (y * PRECISION).toInt()
    }

}

