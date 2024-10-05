package app.voronoi.event

import app.voronoi.beachline.BeachLine
import app.voronoi.graph.Edge
import app.voronoi.graph.Graph
import app.voronoi.graph.Point


class SiteEvent(point: Point) : Event(point) {

    override fun handle(eventQueue: MutableCollection<Event?>, beachLine: BeachLine, graph: Graph) {
        val result = beachLine.insertArc(point)
        result.splitLeaf?.let { l -> graph.addEdge(Edge(l.site, point)) }
        result.splitLeaf?.let { l -> l.getSubscribers().forEach { eventQueue.remove(it) } }
        result.newLeaf.addCircleEvents { e -> eventQueue.add(e) }
    }
}
