package app.voronoi.graph

data class Edge(
    val site1: Point,
    val site2: Point,
    var a: Vertex? = null,
    var b: Vertex? = null
) {

    fun addVertex(v: Vertex) {
        when {
            a == null -> a = v
            b == null -> b = v
            else -> error("Trying to set a third vertex on an edge")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as Edge

        if (site1 != other.site1) return false
        if (site2 != other.site2) return false
        if (a != other.a) return false
        return b == other.b
    }

    override fun hashCode(): Int {
        var result = site1.hashCode()
        result = 31 * result + site2.hashCode()
        result = 31 * result + (a?.hashCode() ?: 0)
        result = 31 * result + (b?.hashCode() ?: 0)
        return result
    }
}

