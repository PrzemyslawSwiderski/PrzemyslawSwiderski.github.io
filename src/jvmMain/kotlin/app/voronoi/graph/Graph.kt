package app.voronoi.graph

import java.util.HashSet

class Graph {
    private val sites = HashSet<Point>()
    private val edges = BisectorMap()

    fun addEdge(e: Edge) {
        edges.put(e.site1, e.site2, e)
    }

    fun getEdgeBetweenSites(a: Point, b: Point): Edge? {
        return edges.get(a, b)
    }

    fun addSite(newSite: Point) {
        sites.add(newSite)
    }

    fun sitePoints(): Set<Point> {
        return sites
    }

    fun edges(): Sequence<Edge> {
        return edges.edges()
    }
}
