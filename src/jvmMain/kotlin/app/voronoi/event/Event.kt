package app.voronoi.event

import app.voronoi.beachline.BeachLine
import app.voronoi.graph.Graph
import app.voronoi.graph.Point

abstract class Event(
    val point: Point
) : Comparable<Event> {

    override fun compareTo(other: Event): Int {
        return other.point.y.compareTo(point.y)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Event
        return point == other.point
    }

    override fun hashCode(): Int {
        return point.hashCode()
    }

    abstract fun handle(eventQueue: MutableCollection<Event?>, beachLine: BeachLine, graph: Graph)
}
