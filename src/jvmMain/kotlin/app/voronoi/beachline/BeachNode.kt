package app.voronoi.beachline

import app.voronoi.graph.Point


abstract class BeachNode {

    var parent: InnerBeachNode? = null

    abstract fun insertArc(newSite: Point): InsertionResult

    abstract fun getLeftmostLeaf(): LeafBeachNode?

    abstract fun getRightmostLeaf(): LeafBeachNode?

    fun replaceBy(n: BeachNode?) {
        parent?.also {
            if (it.leftChild === this) {
                it.insertLeftChild(n)
            } else {
                it.insertRightChild(n)
            }
        }
    }

}
