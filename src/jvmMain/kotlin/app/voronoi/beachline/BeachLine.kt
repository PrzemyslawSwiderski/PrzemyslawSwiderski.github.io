package app.voronoi.beachline

import app.voronoi.graph.Point


class BeachLine {

    private val rootContainer = InnerBeachNode()

    fun insertArc(newSite: Point): InsertionResult {
        val root = getRoot()
        return if (root != null) {
            root.insertArc(newSite)
        } else {
            val l = LeafBeachNode(newSite)
            setRoot(l)
            InsertionResult(null, l)
        }
    }

    fun getRoot(): BeachNode? {
        return rootContainer.leftChild
    }

    fun setRoot(n: BeachNode?) {
        rootContainer.insertLeftChild(n)
    }

}
