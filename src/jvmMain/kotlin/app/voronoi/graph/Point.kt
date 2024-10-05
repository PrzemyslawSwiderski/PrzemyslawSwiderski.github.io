package app.voronoi.graph

import app.voronoi.Math.sq
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double) {

    fun dist(p: Point): Double {
        return sqrt(sq(x - p.x) + sq(y - p.y))
    }

}

