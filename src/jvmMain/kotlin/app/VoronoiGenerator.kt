package app

import app.voronoi.Voronoi
import app.voronoi.graph.Point
import org.jfree.svg.SVGGraphics2D
import java.awt.BasicStroke
import java.awt.BasicStroke.CAP_ROUND
import java.awt.BasicStroke.JOIN_ROUND
import java.awt.Color
import java.awt.geom.Path2D
import java.io.File
import java.util.function.DoubleFunction
import kotlin.math.roundToInt

private val OUTPUT_DIR by System.getenv()

private const val SVG_HEIGHT = 1500.0
private const val SVG_WIDTH = 200.0
private const val CANVAS_HEIGHT = 1500.0
private const val CANVAS_WIDTH = 200.0
private const val POINTS_COUNT = 500

private const val LIGHT_GREEN = "#0f5132"

fun main() {
    val outputDir: File = File(OUTPUT_DIR)
    val g2 = initG2Engine()

    val points = (0..POINTS_COUNT).map {
        val x = (0..CANVAS_WIDTH.toInt()).random()
        val y = (0..CANVAS_HEIGHT.toInt()).random()
        Point(x.toDouble(), y.toDouble())
    } + Point(0.0, 0.0) + Point(0.0, CANVAS_HEIGHT) + Point(CANVAS_WIDTH, 0.0) + Point(CANVAS_WIDTH, CANVAS_HEIGHT)

    val diagram = Voronoi(points)

    val path = Path2D.Double()
    diagram.graph.edgeStream().forEach {
        if (it.a != null && it.b != null) {
            val p1 = it.a!!.location
            val p2 = it.b!!.location
            path.moveTo(p1.x, p1.y)
            path.lineTo(p2.x, p2.y)
        }
    }
    g2.draw(path)

    outputDir.resolve("voronoi.svg").writeText(g2.svgElement)

}

private fun initG2Engine(): SVGGraphics2D {
    val g2 = SVGGraphics2D(SVG_WIDTH, SVG_HEIGHT)
    g2.paint = Color.decode(LIGHT_GREEN)
    g2.stroke = BasicStroke(0.2f, CAP_ROUND, JOIN_ROUND)
    g2.geomDoubleConverter = DoubleFunction<String> { value ->
        value.roundToInt().toString()
    }
    return g2
}
