package ysan.bainiugame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


/**
 * Created by YSAN on 2018/6/19
 */
class SBview : View {

    private lateinit var mL1: Sbline
    private lateinit var mL2: Sbline
    private lateinit var mL3: Sbline
    private lateinit var mL4: Sbline

    private val radius = 400
    private val time = 50L

    private var mPaint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPaint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPaint3 = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPaint4 = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path1 = Path()
    private var path2 = Path()
    private var path3 = Path()
    private var path4 = Path()
    private var b = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    private fun init() {
        mPaint1.color = Color.RED
        mPaint1.style = Paint.Style.STROKE
        mPaint1.strokeWidth = 5f

        mPaint2.color = Color.GREEN
        mPaint2.style = Paint.Style.STROKE
        mPaint2.strokeWidth = 5f

        mPaint3.color = Color.BLUE
        mPaint3.style = Paint.Style.STROKE
        mPaint3.strokeWidth = 5f

        mPaint4.color = Color.BLACK
        mPaint4.style = Paint.Style.STROKE
        mPaint4.strokeWidth = 5f

        mL1 = Sbline((-radius).toFloat(), (-radius).toFloat())
        mL2 = Sbline(radius.toFloat(), (-radius).toFloat())
        mL3 = Sbline(radius.toFloat(), radius.toFloat())
        mL4 = Sbline((-radius).toFloat(), radius.toFloat())

        path1.moveTo((-radius).toFloat(), (-radius).toFloat())
        path2.moveTo(radius.toFloat(), (-radius).toFloat())
        path3.moveTo(radius.toFloat(), radius.toFloat())
        path4.moveTo((-radius).toFloat(), radius.toFloat())
    }

    private fun start() {
        object : Thread() {
            override fun run() {
                while (b) {
                    try {
                        mL1.calculateXY(mL2.startX, mL2.startY)
                        mL2.calculateXY(mL3.startX, mL3.startY)
                        mL3.calculateXY(mL4.startX, mL4.startY)
                        mL4.calculateXY(mL1.startX, mL1.startY)

                        path1.lineTo(mL1.endX, mL1.endY)
                        path2.lineTo(mL2.endX, mL2.endY)
                        path3.lineTo(mL3.endX, mL3.endY)
                        path4.lineTo(mL4.endX, mL4.endY)

                        Thread.sleep(time)
                        postInvalidate()
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }
        }.start()
    }

    fun setStart(b: Boolean) {
        this.b = b
        start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(width * 0.5f, height * 0.5f)
        if (b) {
            canvas.drawPath(path1, mPaint1)
            canvas.drawPath(path2, mPaint2)
            canvas.drawPath(path3, mPaint3)
            canvas.drawPath(path4, mPaint4)

            mL1.updateStart()
            mL2.updateStart()
            mL3.updateStart()
            mL4.updateStart()
        }
    }
}