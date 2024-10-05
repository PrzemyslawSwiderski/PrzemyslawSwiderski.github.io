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

    abstract fun handle(eventQueue: MutableCollection<Event?>, beachLine: BeachLine, graph: Graph)
}
