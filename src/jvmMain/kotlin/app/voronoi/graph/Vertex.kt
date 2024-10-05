package app.voronoi.graph

data class Vertex(
    val location: Point
) {
    override fun toString() = location.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Vertex

        return location == other.location
    }

    override fun hashCode() = location.hashCode()
}
