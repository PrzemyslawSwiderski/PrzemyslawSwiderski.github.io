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
        }
    }

}

