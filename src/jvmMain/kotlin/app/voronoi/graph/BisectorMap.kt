package app.voronoi.graph

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
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false
            val bisector = other as Bisector
            return (a == bisector.a && b == bisector.b) || (a == bisector.b && b == bisector.a)
        }

        override fun hashCode(): Int {
            return a.hashCode() + b.hashCode()
        }
    }
}

