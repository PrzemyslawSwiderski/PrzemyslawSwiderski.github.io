package app.voronoi.event

import app.voronoi.beachline.BeachLine
import app.voronoi.beachline.LeafBeachNode
import app.voronoi.graph.Edge
import app.voronoi.graph.Graph
import app.voronoi.graph.Point
import app.voronoi.graph.Vertex

class VertexEvent private constructor(
    private val l: LeafBeachNode,
    private val c: LeafBeachNode,
    private val r: LeafBeachNode,
    private val circle: Circle
) :
    Event(Point(circle.center.x, circle.center.y - circle.radius)) {

    init {
        l.subscribe(this)
        c.subscribe(this)
        r.subscribe(this)
    }

    override fun handle(eventQueue: MutableCollection<Event?>, beachLine: BeachLine, graph: Graph) {
        c.remove()
        eventQueue.removeAll(c.getSubscribers())

        val v = Vertex(circle.center)
        graph.getEdgeBetweenSites(l.site, c.site)!!.addVertex(v)
        graph.getEdgeBetweenSites(r.site, c.site)!!.addVertex(v)
        val e = Edge(l.site, r.site)
        graph.addEdge(e)
        e.addVertex(v)

        l.addCircleEvents { eventQueue.add(it) }
        r.addCircleEvents { eventQueue.add(it) }
    }

    private class Circle(l: Point, c: Point, r: Point) {
        val center: Point = when {
            l.x != c.x && c.x != r.x -> {
                computeCenter(l, c, r)
            }

            c.x != l.x && r.x != l.x -> {
                computeCenter(c, l, r)
            }

            c.x != r.x && l.x != r.x -> {
                computeCenter(c, r, l)
            }

            else -> {
                Point(Double.Companion.NaN, Double.Companion.NaN)
            }
        }
        val radius: Double = c.dist(center)

        companion object {
            private fun computeCenter(l: Point, c: Point, r: Point): Point {
                val ma = (c.y - l.y) / (c.x - l.x)
                val mb = (r.y - c.y) / (r.x - c.x)

                val x = (ma * mb * (l.y - r.y) + mb * (l.x + c.x) - ma * (c.x + r.x)) / (2.0 * (mb - ma))
                return if (ma != 0.0) {
                    val y = -(x - (l.x + c.x) / 2.0) / ma + (l.y + c.y) / 2.0
                    Point(x, y)
                } else {
                    val y = -(x - (c.x + r.x) / 2.0) / mb + (c.y + r.y) / 2.0
                    Point(x, y)
                }
            }
        }
    }

    companion object {
        fun build(l: LeafBeachNode, c: LeafBeachNode, r: LeafBeachNode): VertexEvent? {
            val ap = l.site
            val bp = c.site
            val cp = r.site
            val convergence = (ap.y - bp.y) * (bp.x - cp.x) - (bp.y - cp.y) * (ap.x - bp.x)
            if (convergence > 0) {
                val circle = Circle(ap, bp, cp)
                return VertexEvent(l, c, r, circle)
            }
            return null
        }
    }
}
