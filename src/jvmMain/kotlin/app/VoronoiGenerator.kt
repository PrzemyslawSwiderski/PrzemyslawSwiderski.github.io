package app

import app.voronoi.Voronoi
import app.voronoi.graph.Point
import org.jfree.svg.SVGGraphics2D
import java.awt.BasicStroke
import java.awt.Color
import java.awt.geom.Ellipse2D
import java.awt.geom.Path2D
import java.io.File
import java.util.function.DoubleFunction
import kotlin.math.roundToInt
import kotlin.random.Random

private val OUTPUT_DIR by System.getenv()

private const val SEED = 77777
private const val SVG_HEIGHT = 1500.0
private const val SVG_WIDTH = 200.0
private const val CANVAS_HEIGHT = 1500.0
private const val CANVAS_WIDTH = 200.0
private const val POINTS_COUNT = 500
private const val LIGHT_GREEN = "#0f5132"
private const val DRAW_SITE_POINTS = false

fun main() {
    val outputDir: File = File(OUTPUT_DIR)
    val g2 = initG2Engine()
    val seed = Random(SEED)
    val points = (1..POINTS_COUNT).map {
        var x: Int
        var y: Int
        if (SEED > 0) {
            x = (1..CANVAS_WIDTH.toInt()).random(seed)
            y = (1..CANVAS_HEIGHT.toInt()).random(seed)
        } else {
            x = (1..CANVAS_WIDTH.toInt()).random()
            y = (1..CANVAS_HEIGHT.toInt()).random()
        }
        Point(x.toDouble(), y.toDouble())
    } + Point(1.0, 1.0) + Point(1.0, CANVAS_HEIGHT) + Point(CANVAS_WIDTH, 1.0) + Point(CANVAS_WIDTH, CANVAS_HEIGHT)

    val diagram = Voronoi(points)

    drawEdges(diagram, g2)

    if (DRAW_SITE_POINTS) {
        drawSitePoints(diagram, g2)
    }

    outputDir.resolve("voronoi.svg").writeText(g2.svgElement)

}

private fun initG2Engine(): SVGGraphics2D {
    val g2 = SVGGraphics2D(SVG_WIDTH, SVG_HEIGHT)
    g2.stroke = BasicStroke(0.2f)
    g2.geomDoubleConverter = DoubleFunction<String> { value ->
        value.roundToInt().toString()
    }
    return g2
}

private fun drawEdges(diagram: Voronoi, g2: SVGGraphics2D) {
    val path = Path2D.Double()
    diagram.graph.edges().forEach {
        if (it.a != null && it.b != null) {
            val p1 = it.a!!.location
            val p2 = it.b!!.location
            path.moveTo(p1.x, p1.y)
            path.lineTo(p2.x, p2.y)
        }
    }

    g2.paint = Color.decode(LIGHT_GREEN)
    g2.draw(path)
}

private fun drawSitePoints(diagram: Voronoi, g2: SVGGraphics2D) {
    diagram.graph.sitePoints().forEach {
        val point = Ellipse2D.Double()
        point.x = it.x
        point.y = it.y
        point.height = 2.0
        point.width = 2.0
        g2.paint = Color.RED
        g2.draw(point)
    }
}
