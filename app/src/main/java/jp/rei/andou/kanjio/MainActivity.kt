package jp.rei.andou.kanjio

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.PathParser


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            KanjiView(
                this,
                parsePathsDatas(
                    "M32.5,16.39c0.13,1.11,0.22,2.5-0.11,3.86c-2.11,8.62-10.26,25.28-20.08,36.96|M27.5,38c0.64,0.66,0.81,2.12,0.81,3.48c0,14.02-0.31,43.23-0.31,52.65|M47.65,13.83c3.83,2.19,9.9,8.98,10.86,12.38|M82.25,11.14c0.03,0.35,0.06,0.9-0.05,1.4c-0.65,2.96-4.39,9.45-9.5,13.42|M44.57,33.74c1.14,0.61,3.2,0.71,4.36,0.61C59,33.5,76.5,31,86.13,30.06c1.89-0.19,3.03,0.29,3.98,0.59|M45.28,50.19c1.13,0.6,3.17,0.66,4.32,0.6c10.15-0.54,21.6-1.98,35.48-3.35c1.87-0.18,2.92-0.19,4.45,0.3|M38.5,68.7c1.42,0.54,4.01,0.67,5.44,0.54c11.16-0.99,34.32-3.49,49.96-4.23c2.36-0.11,2.2-0.01,4.35,0.24|M65.26,33.25c0.95,0.5,1.52,2.25,1.71,3.25c0.19,1,0,55.25-0.19,61.5"
                )
            )
        )
    }

    private fun parsePathsDatas(pathDatas: String): Path {
        val newPath = Path()
        pathDatas.split("|").map {PathParser.createPathFromPathData(it)}.forEach(newPath::addPath)
        return newPath
    }
}

//class AnimationKanjiView(context: Context, private val paths: List<Path>) : View(context) {



//}

class KanjiView(context: Context, private val path: Path) : View(context) {

    private val paint = Paint()

    init {
        paint.strokeWidth = 20F
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        val scaleMatrix = Matrix()
        scaleMatrix.setScale(10F, 10F, 0F, 0F)
        path.transform(scaleMatrix)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }
}
