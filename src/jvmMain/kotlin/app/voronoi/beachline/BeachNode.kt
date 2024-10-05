package app.voronoi.beachline

import app.voronoi.graph.Point


abstract class BeachNode {
    private var parent: InnerBeachNode? = null

    abstract fun insertArc(newSite: Point): InsertionResult

    abstract fun getLeftmostLeaf(): LeafBeachNode?

    abstract fun getRightmostLeaf(): LeafBeachNode?

    fun replaceBy(n: BeachNode?) {
        if (getParent() != null) {
            val parent = getParent()!!
            if (parent.leftChild === this) {
                parent.insertLeftChild(n)
            } else {
                parent.insertRightChild(n)
            }
        }
    }

    fun getParent(): InnerBeachNode? {
        return parent
    }

    fun setParent(parent: InnerBeachNode?) {
        this.parent = parent
    }
}
