package app.voronoi.beachline

import app.voronoi.Math.sq
import app.voronoi.graph.Point
import kotlin.math.sqrt

class InnerBeachNode(var leftChild: BeachNode? = null, var rightChild: BeachNode? = null) : BeachNode() {

    init {
        insertLeftChild(leftChild)
        insertRightChild(rightChild)
    }

    override fun insertArc(newSite: Point): InsertionResult {
        // Find leafs represented by this inner node
        var l = leftChild?.getRightmostLeaf()!!.site
        var r = rightChild?.getLeftmostLeaf()!!.site

        // Transform coordinate to local coords
        val lxOld = l.x
        r = Point(r.x - l.x, r.y - newSite.y)
        l = Point(0.0, l.y - newSite.y)

        // Compute intersection of parabolas
        var x: Double = when {
            l.y.compareTo(r.y) == 0 -> {
                r.x / 2.0
            }

            l.y == 0.0 -> {
                l.x
            }

            r.y == 0.0 -> {
                r.x
            }

            else -> {
                (l.y * r.x - sqrt(l.y * r.y * (sq(l.y - r.y) + sq(r.x)))) / (l.y - r.y)
            }
        }

        x += lxOld

        return if (newSite.x < x) leftChild!!.insertArc(newSite) else rightChild!!.insertArc(newSite)
    }

    override fun getLeftmostLeaf(): LeafBeachNode? {
        return leftChild?.getLeftmostLeaf()
    }

    override fun getRightmostLeaf(): LeafBeachNode? {
        return rightChild?.getRightmostLeaf()
    }

    fun insertLeftChild(leftChild: BeachNode?) {
        this.leftChild = leftChild
        leftChild?.parent = this
    }

    fun insertRightChild(rightChild: BeachNode?) {
        this.rightChild = rightChild
        rightChild?.parent = this
    }
}
