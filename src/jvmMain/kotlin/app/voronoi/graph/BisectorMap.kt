package app.voronoi.graph

import java.util.HashMap

class BisectorMap {

    private val data: MutableMap<Bisector, Edge> = HashMap()

    fun put(a: Point, b: Point, e: Edge) {
        data[Bisector(a, b)] = e
    }

    fun get(a: Point, b: Point): Edge? {
        return data[Bisector(a, b)]
    }

    fun edges(): Sequence<Edge> {
        return data.values.asSequence()
    }

    data class Bisector(val a: Point, val b: Point) {
        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o == null || javaClass != o.javaClass) return false
            val bisector = o as Bisector
            return (a == bisector.a && b == bisector.b) || (a == bisector.b && b == bisector.a)
        }

        override fun hashCode(): Int {
            return a.hashCode() + b.hashCode()
        }
    }
}

