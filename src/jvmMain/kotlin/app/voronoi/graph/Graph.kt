package app.voronoi.graph

import java.util.Collections
import java.util.HashSet

class Graph {
    private val edges = BisectorMap()
    private val sites = HashSet<Point>()

    fun addEdge(e: Edge) {
        edges.put(e.site1, e.site2, e)
    }

    fun getEdgeBetweenSites(a: Point, b: Point): Edge? {
        return edges.get(a, b)
    }

    fun addSite(newSite: Point) {
        sites.add(newSite)
    }

    fun getSitePoints(): Set<Point> {
        return Collections.unmodifiableSet(sites)
    }

    fun edgeStream(): Sequence<Edge> {
        return edges.stream()
    }
}
