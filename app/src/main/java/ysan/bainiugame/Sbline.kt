package ysan.bainiugame


/**
 * Created by YSAN on 2018/6/19
 */
class Sbline {
    var startX: Float
    var startY: Float
    var endX: Float = 0.0f
    var endY: Float = 0.0f
    val v: Int = 10

    constructor(startX: Float, startY: Float) {
        this.startX = startX
        this.startY = startY
    }

    fun calculateXY(x: Float, y: Float) {
        val dx = x - startX
        val dy = y - startY
        val dzz = Math.pow(dx.toDouble(), 2.0) + Math.pow(dy.toDouble(), 2.0)
        val dz = Math.sqrt(dzz)
        val dt = dz / v
        this.endX = (startX + dx / dt).toFloat()
        this.endY = (startY + dy / dt).toFloat()
    }

    fun updateStart() {
        this.startX = endX
        this.startY = endY
    }
}