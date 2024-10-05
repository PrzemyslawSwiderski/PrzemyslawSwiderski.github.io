package app.voronoi.beachline

import app.voronoi.event.Event
import app.voronoi.event.VertexEvent
import app.voronoi.graph.Point
import java.util.Collections
import java.util.LinkedList
import java.util.function.Consumer

class LeafBeachNode internal constructor(val site: Point) : BeachNode() {

    private val subscribedEvents: MutableList<VertexEvent?> = LinkedList<VertexEvent?>()

    private fun copy(): LeafBeachNode {
        return LeafBeachNode(site)
    }

    override fun insertArc(newSite: Point): InsertionResult {
        val newLeaf = LeafBeachNode(newSite)
        if (newSite.y == site.y) {
            if (newSite.x < site.x) {
                replaceBy(InnerBeachNode(newLeaf, copy()))
            } else {
                replaceBy(InnerBeachNode(copy(), newLeaf))
            }
        } else {
            if (newSite.x < site.x) {
                replaceBy(InnerBeachNode(InnerBeachNode(copy(), newLeaf), copy()))
            } else {
                replaceBy(InnerBeachNode(copy(), InnerBeachNode(newLeaf, copy())))
            }
        }
        setParent(null) // Disconnect this node from the tree
        return InsertionResult(this, newLeaf)
    }

    fun remove() {
        val parent = getParent()
        val sibling: BeachNode? = if (parent!!.leftChild === this) parent.rightChild else parent.leftChild
        parent.replaceBy(sibling)
        setParent(null) // Disconnect this node from the tree
    }

    override fun getLeftmostLeaf(): LeafBeachNode {
        return this
    }

    override fun getRightmostLeaf(): LeafBeachNode {
        return this
    }

    fun getLeftNeighbor(): LeafBeachNode? {
        var current = getParent()
        var child: BeachNode? = this
        if (current != null) {
            while (current!!.getParent() != null) {
                if (current.rightChild === child) {
                    return current.leftChild?.getRightmostLeaf()
                } else {
                    child = current
                    current = current.getParent()
                }
            }
        }
        return null
    }

    fun getRightNeighbor(): LeafBeachNode? {
        var current = getParent()
        var child: BeachNode? = this
        if (current != null) {
            while (current!!.getParent() != null) {
                if (current.leftChild === child) {
                    return current.rightChild?.getLeftmostLeaf()
                } else {
                    child = current
                    current = current.getParent()
                }
            }
        }
        return null
    }

    fun addCircleEvents(q: Consumer<Event?>) {
        // l2 -> l1 -> leaf <- r1 <- r2
        getLeftNeighbor()?.getLeftNeighbor()?.let { buildEvent(it) }?.let { q.accept(it) }

        getLeftNeighbor()?.let { buildEvent(it) }?.let { q.accept(it) }

        buildEvent(this)?.let { q.accept(it) }

        getRightNeighbor()?.getRightNeighbor()?.let { buildEvent(it) }?.let { q.accept(it) }

        getRightNeighbor()?.let { buildEvent(it) }?.let { q.accept(it) }
    }

    fun subscribe(e: VertexEvent?) {
        subscribedEvents.add(e)
    }

    fun getSubscribers(): MutableList<VertexEvent?> {
        return Collections.unmodifiableList<VertexEvent?>(subscribedEvents)
    }

    companion object {
        private fun buildEvent(center: LeafBeachNode): VertexEvent? {
            val r = center.getRightNeighbor()
            val l = center.getLeftNeighbor()

            return if (r != null && l != null) {
                VertexEvent.build(l, center, r)
            } else {
                null
            }
        }
    }
}
