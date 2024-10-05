package app.voronoi

import app.voronoi.beachline.BeachLine
import app.voronoi.event.Event
import app.voronoi.event.SiteEvent
import app.voronoi.graph.Graph
import app.voronoi.graph.Point
import java.util.PriorityQueue

/**
 * The main class and entry point of voronoi-java.
 * Based on https://github.com/aschlosser/voronoi-java adn translated to Kotlin with some null-check improvements
 *
 */
class Voronoi(points: Collection<Point>) {

    val graph = Graph()

    init {
        val queue = PriorityQueue<Event?>()
        points.map { point: Point -> SiteEvent(point) }.forEach(queue::offer)
        points.forEach { newSite: Point -> graph.addSite(newSite) }

        val beachLine = BeachLine()
        var sweep = Double.Companion.MAX_VALUE
        while (queue.isNotEmpty()) {
            val e = queue.peek()
            assert(e!!.point.y <= sweep)
            e.handle(queue, beachLine, graph)
            queue.remove(e)
            sweep = e.point.y
        }
    }

}
