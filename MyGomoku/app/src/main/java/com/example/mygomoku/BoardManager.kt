package com.example.mygomoku

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

class BoardManager(context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener {
    /*
    game tracker
    0 = empty grid(green)
    1 = white disk
    2 = black disk

    background color of board(grid) will be green
    */
    var grid = arrayOf(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))


    //Color.argb(1, 202, 164, 114)

    var gridDisk = arrayOf(Color.argb(255, 225, 225, 82), Color.WHITE, Color.BLACK)

    // points used to indicate specific points in vertical side
    private var vertPoint1 : Float = 0.0f
    private var vertPoint2 : Float = 0.0f
    private var vertPoint3 : Float = 0.0f
    private var vertPoint4 : Float = 0.0f
    private var vertPoint5 : Float = 0.0f
    private var vertPoint6 : Float = 0.0f
    private var vertPoint7 : Float = 0.0f
    private var vertPoint8 : Float = 0.0f
    private var vertPoint9 : Float = 0.0f
    private var vertPoint10 : Float = 0.0f
    private var vertPoint11 : Float = 0.0f
    private var vertPoint12 : Float = 0.0f
    private var vertPoint13 : Float = 0.0f
    private var vertPoint14 : Float = 0.0f
    private var vertPoint15 : Float = 0.0f

    // points used to indicate specific points in horizontal side
    private var horiPoint1 : Float = 0.0f
    private var horiPoint2 : Float = 0.0f
    private var horiPoint3 : Float = 0.0f
    private var horiPoint4 : Float = 0.0f
    private var horiPoint5 : Float = 0.0f
    private var horiPoint6 : Float = 0.0f
    private var horiPoint7 : Float = 0.0f
    private var horiPoint8 : Float = 0.0f
    private var horiPoint9 : Float = 0.0f
    private var horiPoint10 : Float = 0.0f
    private var horiPoint11 : Float = 0.0f
    private var horiPoint12 : Float = 0.0f
    private var horiPoint13 : Float = 0.0f
    private var horiPoint14 : Float = 0.0f
    private var horiPoint15 : Float = 0.0f

    //true = White
    //false = Black
    var whosTurn : Boolean = false

    //used to check if valid move was made so that player's turn can be changed
    var validMoveMade : Boolean = false

    //check to make sure if game is commenced or not
    var gameStarted : Boolean = false

    //used to check if game's finished from playing. If yes, determine the winner
    var gameSet : Boolean = false

    //check to see if both white and black disk don't have anymore valid moves
    var whiteDone : Boolean = false
    var blackDone : Boolean = false

    //used to set who's the winner
    var whiteWin : Boolean = false
    var blackWin : Boolean = false
    var tie : Boolean = false

    private val paint = Paint()

    private var mDetector = GestureDetectorCompat(this.context, this)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (mDetector.onTouchEvent(event)) {
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        /* specific points of square used from demo
        mWidth = w.toFloat() //entire width
        mHeight = h.toFloat() //entire height
        mMidWidth = mWidth / 2 //half of the width
        mMidHeight = mHeight / 2 //half of the height
        */

        //these 4 variables are used to indicate specific point of the whole square
        // so that each color square can be shaped in specific size

        // now to setup 10 specific points for both horizontal and vertical sides
        //horiPoint9 = w.toFloat()
        //horiPoint8 = horiPoint9 - (horiPoint9 / 9)
        horiPoint15 = w.toFloat()
        horiPoint14 = horiPoint15 - (horiPoint15 / 15)
        horiPoint13 = horiPoint14 - (horiPoint15 / 15)
        horiPoint12 = horiPoint13 - (horiPoint15 / 15)
        horiPoint11 = horiPoint12 - (horiPoint15 / 15)
        horiPoint10 = horiPoint11 - (horiPoint15 / 15)
        horiPoint9 = horiPoint10 - (horiPoint15 / 15)
        horiPoint8 = horiPoint9 - (horiPoint15 / 15)
        horiPoint7 = horiPoint8 - (horiPoint15 / 15)
        horiPoint6 = horiPoint7 - (horiPoint15 / 15)
        horiPoint5 = horiPoint6 - (horiPoint15 / 15)
        horiPoint4 = horiPoint5 - (horiPoint15 / 15)
        horiPoint3 = horiPoint4 - (horiPoint15 / 15)
        horiPoint2 = horiPoint3 - (horiPoint15 / 15)
        horiPoint1 = horiPoint2 - (horiPoint15 / 15)

        //vertPoint9 = h.toFloat()
        //vertPoint8 = vertPoint9 - (vertPoint9 / 9)

        vertPoint15 = h.toFloat()
        vertPoint14 = vertPoint15 - (vertPoint15 / 15)
        vertPoint13 = vertPoint14 - (vertPoint15 / 15)
        vertPoint12 = vertPoint13 - (vertPoint15 / 15)
        vertPoint11 = vertPoint12 - (vertPoint15 / 15)
        vertPoint10 = vertPoint11 - (vertPoint15 / 15)
        vertPoint9 = vertPoint10 - (vertPoint15 / 15)
        vertPoint8 = vertPoint9 - (vertPoint15 / 15)
        vertPoint7 = vertPoint8 - (vertPoint15 / 15)
        vertPoint6 = vertPoint7 - (vertPoint15 / 15)
        vertPoint5 = vertPoint6 - (vertPoint15 / 15)
        vertPoint4 = vertPoint5 - (vertPoint15 / 15)
        vertPoint3 = vertPoint4 - (vertPoint15 / 15)
        vertPoint2 = vertPoint3 - (vertPoint15 / 15)
        vertPoint1 = vertPoint2 - (vertPoint15 / 15)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //canvas.drawRect(left, top, right, bottom, paint)

        /*
        starting from top left grid to bottom right grid
        from left to right, top to bottom
        */

        //Entire board itself
        paint.color = gridDisk[0]
        canvas.drawRect(0.0f, 0.0f, horiPoint15, vertPoint15, paint)

        //lines between grids (total of 10 lines for each sides)
        paint.color = Color.BLACK
        paint.strokeWidth = 2.5f
        //canvas.drawLine(startX, startY, stopX, stopY)

        //horizontal lines
        canvas.drawLine(0.0f, 0.0f, horiPoint15, 0.0f, paint)
        canvas.drawLine(0.0f, vertPoint1, horiPoint15, vertPoint1, paint)
        canvas.drawLine(0.0f, vertPoint2, horiPoint15, vertPoint2, paint)
        canvas.drawLine(0.0f, vertPoint3, horiPoint15, vertPoint3, paint)
        canvas.drawLine(0.0f, vertPoint4, horiPoint15, vertPoint4, paint)
        canvas.drawLine(0.0f, vertPoint5, horiPoint15, vertPoint5, paint)
        canvas.drawLine(0.0f, vertPoint6, horiPoint15, vertPoint6, paint)
        canvas.drawLine(0.0f, vertPoint7, horiPoint15, vertPoint7, paint)
        canvas.drawLine(0.0f, vertPoint8, horiPoint15, vertPoint8, paint)
        canvas.drawLine(0.0f, vertPoint9, horiPoint15, vertPoint9, paint)
        canvas.drawLine(0.0f, vertPoint10, horiPoint15, vertPoint10, paint)
        canvas.drawLine(0.0f, vertPoint11, horiPoint15, vertPoint11, paint)
        canvas.drawLine(0.0f, vertPoint12, horiPoint15, vertPoint12, paint)
        canvas.drawLine(0.0f, vertPoint13, horiPoint15, vertPoint13, paint)
        canvas.drawLine(0.0f, vertPoint14, horiPoint15, vertPoint14, paint)
        canvas.drawLine(0.0f, vertPoint15, horiPoint15, vertPoint15, paint)

        //vertical lines
        canvas.drawLine(0.0f, 0.0f, 0.0f, vertPoint15, paint)
        canvas.drawLine(horiPoint1, 0.0f, horiPoint1, vertPoint15, paint)
        canvas.drawLine(horiPoint2, 0.0f, horiPoint2, vertPoint15, paint)
        canvas.drawLine(horiPoint3, 0.0f, horiPoint3, vertPoint15, paint)
        canvas.drawLine(horiPoint4, 0.0f, horiPoint4, vertPoint15, paint)
        canvas.drawLine(horiPoint5, 0.0f, horiPoint5, vertPoint15, paint)
        canvas.drawLine(horiPoint6, 0.0f, horiPoint6, vertPoint15, paint)
        canvas.drawLine(horiPoint7, 0.0f, horiPoint7, vertPoint15, paint)
        canvas.drawLine(horiPoint8, 0.0f, horiPoint8, vertPoint15, paint)
        canvas.drawLine(horiPoint9, 0.0f, horiPoint9, vertPoint15, paint)
        canvas.drawLine(horiPoint10, 0.0f, horiPoint10, vertPoint15, paint)
        canvas.drawLine(horiPoint11, 0.0f, horiPoint11, vertPoint15, paint)
        canvas.drawLine(horiPoint12, 0.0f, horiPoint12, vertPoint15, paint)
        canvas.drawLine(horiPoint13, 0.0f, horiPoint13, vertPoint15, paint)
        canvas.drawLine(horiPoint14, 0.0f, horiPoint14, vertPoint15, paint)
        canvas.drawLine(horiPoint15, 0.0f, horiPoint15, vertPoint15, paint)

        //canvas.drawLine(0.0f, mMidHeight, mWidth, mMidHeight, paint) //horizontal line
        //canvas.drawLine(mMidWidth, 0.0f, mMidWidth, mHeight, paint) //vertical line


        //drawing disks
        //interesting resource I found but not used: https://www.geeksforgeeks.org/create-different-types-of-circle-in-canvas-in-android-using-jetpack-compose/
        //canvas.drawCircle(cx, cy, radius, paint)

        //SETUP disks for every grid so that when the grid value is changed, the correct disk will be displayed.
        //i = vertical (row)
        //j = horizontal (col)

        var diskSize = 27f

        for (i in grid.indices) {
            for (j in grid[i].indices) {

                //if the board value is not 0
                if (grid[i][j] != 0) {
                    paint.color = gridDisk[grid[i][j]]
                    paint.style = Paint.Style.FILL_AND_STROKE

                    if (i == 0) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint1,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 1) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint2,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 2) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint3,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 3) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint4,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 4) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint5,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 5) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint6,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 6) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint7,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 7) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint8,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 8) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint9,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 9) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint10,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 10) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint11,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 11) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint12,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 12) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint13,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 13) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint14,
                                diskSize,
                                paint
                            )
                        }
                    }

                    else if (i == 14) {
                        if (j == 0) {
                            canvas.drawCircle(
                                horiPoint1,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 1) {
                            canvas.drawCircle(
                                horiPoint2,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 2) {
                            canvas.drawCircle(
                                horiPoint3,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 3) {
                            canvas.drawCircle(
                                horiPoint4,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 4) {
                            canvas.drawCircle(
                                horiPoint5,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 5) {
                            canvas.drawCircle(
                                horiPoint6,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 6) {
                            canvas.drawCircle(
                                horiPoint7,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 7) {
                            canvas.drawCircle(
                                horiPoint8,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 8) {
                            canvas.drawCircle(
                                horiPoint9,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 9) {
                            canvas.drawCircle(
                                horiPoint10,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 10) {
                            canvas.drawCircle(
                                horiPoint11,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 11) {
                            canvas.drawCircle(
                                horiPoint12,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 12) {
                            canvas.drawCircle(
                                horiPoint13,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 13) {
                            canvas.drawCircle(
                                horiPoint14,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }

                        else if (j == 14) {
                            canvas.drawCircle(
                                horiPoint15,
                                vertPoint15,
                                diskSize,
                                paint
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent) {
        // not used
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        var col = 0
        var row = 0

        // Check which specific column the touch is detected at
        if (e.x > (horiPoint1 / 2) && e.x < (horiPoint2 - (horiPoint1 / 2))) {
            col = 0
        }

        else if (e.x > (horiPoint2 - (horiPoint1 / 2)) && e.x < (horiPoint3 - (horiPoint1 / 2))) {
            col = 1
        }

        else if (e.x > (horiPoint3 - (horiPoint1 / 2)) && e.x < (horiPoint4 - (horiPoint1 / 2))) {
            col = 2
        }

        else if (e.x > (horiPoint4 - (horiPoint1 / 2)) && e.x < (horiPoint5 - (horiPoint1 / 2))) {
            col = 3
        }

        else if (e.x > (horiPoint5 - (horiPoint1 / 2)) && e.x < (horiPoint6 - (horiPoint1 / 2))) {
            col = 4
        }

        else if (e.x > (horiPoint6 - (horiPoint1 / 2)) && e.x < (horiPoint7 - (horiPoint1 / 2))) {
            col = 5
        }

        else if (e.x > (horiPoint7 - (horiPoint1 / 2)) && e.x < (horiPoint8 - (horiPoint1 / 2))) {
            col = 6
        }

        else if (e.x > (horiPoint8 - (horiPoint1 / 2)) && e.x < (horiPoint9 - (horiPoint1 / 2))) {
            col = 7
        }

        else if (e.x > (horiPoint9 - (horiPoint1 / 2)) && e.x < (horiPoint10 - (horiPoint1 / 2))) {
            col = 8
        }

        else if (e.x > (horiPoint10 - (horiPoint1 / 2)) && e.x < (horiPoint11 - (horiPoint1 / 2))) {
            col = 9
        }

        else if (e.x > (horiPoint11 - (horiPoint1 / 2)) && e.x < (horiPoint12 - (horiPoint1 / 2))) {
            col = 10
        }

        else if (e.x > (horiPoint12 - (horiPoint1 / 2)) && e.x < (horiPoint13 - (horiPoint1 / 2))) {
            col = 11
        }

        else if (e.x > (horiPoint13 - (horiPoint1 / 2)) && e.x < (horiPoint14 - (horiPoint1 / 2))) {
            col = 12
        }

        else if (e.x > (horiPoint14 - (horiPoint1 / 2)) && e.x < (horiPoint15 - (horiPoint1 / 2))) {
            col = 13
        }


        // Now, check which specific row the touch is detected at
        if (e.y > (vertPoint1 / 2) && e.y < (vertPoint2 - (vertPoint1 / 2))) {
            row = 0
        }

        else if (e.y > (vertPoint2 - (vertPoint1 / 2)) && e.y < (vertPoint3 - (vertPoint1 / 2))) {
            row = 1
        }

        else if (e.y > (vertPoint3 - (vertPoint1 / 2)) && e.y < (vertPoint4 - (vertPoint1 / 2))) {
            row = 2
        }

        else if (e.y > (vertPoint4 - (vertPoint1 / 2)) && e.y < (vertPoint5 - (vertPoint1 / 2))) {
            row = 3
        }

        else if (e.y > (vertPoint5 - (vertPoint1 / 2)) && e.y < (vertPoint6 - (vertPoint1 / 2))) {
            row = 4
        }

        else if (e.y > (vertPoint6 - (vertPoint1 / 2)) && e.y < (vertPoint7 - (vertPoint1 / 2))) {
            row = 5
        }

        else if (e.y > (vertPoint7 - (vertPoint1 / 2)) && e.y < (vertPoint8 - (vertPoint1 / 2))) {
            row = 6
        }

        else if (e.y > (vertPoint8 - (vertPoint1 / 2)) && e.y < (vertPoint9 - (vertPoint1 / 2))) {
            row = 7
        }

        else if (e.y > (vertPoint9 - (vertPoint1 / 2)) && e.y < (vertPoint10 - (vertPoint1 / 2))) {
            row = 8
        }

        else if (e.y > (vertPoint10 - (vertPoint1 / 2)) && e.y < (vertPoint11 - (vertPoint1 / 2))) {
            row = 9
        }

        else if (e.y > (vertPoint11 - (vertPoint1 / 2)) && e.y < (vertPoint12 - (vertPoint1 / 2))) {
            row = 10
        }

        else if (e.y > (vertPoint12 - (vertPoint1 / 2)) && e.y < (vertPoint13 - (vertPoint1 / 2))) {
            row = 11
        }

        else if (e.y > (vertPoint13 - (vertPoint1 / 2)) && e.y < (vertPoint14 - (vertPoint1 / 2))) {
            row = 12
        }

        else if (e.y > (vertPoint14 - (vertPoint1 / 2)) && e.y < (vertPoint15 - (vertPoint1 / 2))) {
            row = 13
        }


        //Now, check if grid that user tapped is valid move or not

        //First, check if game is commenced or not. If not, there is no point of checking
        //since there is no game going on.
        if (gameStarted && !gameSet) {

            //check if it is white's turn
            if (whosTurn == true) {

                //if the spot that user tapped is empty, place white disk on that spot
                if (grid[row][col] == 0) {
                    grid[row][col] = 1
                    validMoveMade = true
                }

                //once the valid move was made, check if white disk meets the victory condition
                if (validMoveMade) {
                    validMoveMade = false

                    //4 variables used for tracking all 4 directions
                    var horiLine = 0 //left to right
                    var vertLine = 0 //top to bottom
                    var diagLine1 = 0 //top-left to bottom-right
                    var diagLine2 = 0 //bottom-left to top-right

                    horiLine = (countLeft(whosTurn, row, col, 1) + countRight(whosTurn, row, col, 1)) - 1
                    vertLine = (countTop(whosTurn, row, col, 1) + countBottom(whosTurn, row, col, 1)) - 1
                    diagLine1 = (countTopLeft(whosTurn, row, col, 1) + countBottomRight(whosTurn, row, col, 1)) - 1
                    diagLine2 = (countBottomLeft(whosTurn, row, col, 1) + countTopRight(whosTurn, row, col, 1)) - 1

                    //if there's 5 disks in any direction's line, then white disk wins
                    if (horiLine == 5 || vertLine == 5 || diagLine1 == 5 || diagLine2 == 5) {
                        gameStarted = false
                        gameSet = true
                        whiteWin = true
                    }

                    whosTurn = false

                }

            }

            //check if it is black's turn
            else {

                //if the spot that user tapped is empty, place black disk on that spot
                if (grid[row][col] == 0) {
                    grid[row][col] = 2
                    validMoveMade = true
                }

                //once the valid move was made, check if black disk meets the victory condition
                if (validMoveMade) {
                    validMoveMade = false

                    //4 variables used for tracking all 4 directions
                    var horiLine = 0 //left to right
                    var vertLine = 0 //top to bottom
                    var diagLine1 = 0 //top-left to bottom-right
                    var diagLine2 = 0 //bottom-left to top-right

                    horiLine = (countLeft(whosTurn, row, col, 1) + countRight(whosTurn, row, col, 1)) - 1
                    vertLine = (countTop(whosTurn, row, col, 1) + countBottom(whosTurn, row, col, 1)) - 1
                    diagLine1 = (countTopLeft(whosTurn, row, col, 1) + countBottomRight(whosTurn, row, col, 1)) - 1
                    diagLine2 = (countBottomLeft(whosTurn, row, col, 1) + countTopRight(whosTurn, row, col, 1)) - 1

                    //if there's 5 disks in any direction's line, then black disk wins
                    if (horiLine == 5 || vertLine == 5 || diagLine1 == 5 || diagLine2 == 5) {
                        gameStarted = false
                        gameSet = true
                        blackWin = true
                    }

                    whosTurn = true

                }

            }
        }

        //after new move has been made(by any player), check if board still has any empty grid.
        //If not, then neither players can make further move
        if (!checkEmptyGrid()) {
            gameStarted = false
            gameSet = true
            tie = true
        }

        /* Old code used for Othello game
        if (gameStarted && !gameSet) {
            //if it's white disk's turn
            if (whosTurn == true) {

                //first, check if white disk has any valid move available.
                //if no, set 'whiteDone' variable as true to indicate that white disk
                //don't have anymore valid moves to makes and give black disk a turn
                if (!checkRemainingValidMoves(whosTurn)) {
                    whiteDone = true
                    whosTurn = false
                    validMoveMade = false
                }

                //if there is valid move available for white disk, let the white disk play its turn
                else {
                    //Use a recursive function to check if it's valid move or not
                    //once the valid move has been made, use boolean variable to switch the player turn
                    //keep the player turn as it is until valid move has been made

                    //if statement to check if it's a empty grid that can place disk
                    if (grid[row][col] == 0) {
                        if (row > 0 && row < 7) {
                            if (col > 0 && col < 7) {

                                //check if top-left grid has black disk
                                if (grid[row - 1][col - 1] == 2) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has black disk
                                if (grid[row - 1][col] == 2) {

                                    //if valid move is detected at top direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top direction line
                                    //and place white disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has black disk
                                if (grid[row - 1][col + 1] == 2) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has black disk
                                if (grid[row][col - 1] == 2) {

                                    //if valid move is detected at left direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of left direction line
                                    //and place white disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has black disk
                                if (grid[row][col + 1] == 2) {

                                    //if valid move is detected at right direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of right direction line
                                    //and place white disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has black disk
                                if (grid[row + 1][col - 1] == 2) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has black disk
                                if (grid[row + 1][col] == 2) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has black disk
                                if (grid[row + 1][col + 1] == 2) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 0) {

                                //check if top grid has black disk
                                if (grid[row - 1][col] == 2) {

                                    //if valid move is detected at top direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top direction line
                                    //and place white disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has black disk
                                if (grid[row - 1][col + 1] == 2) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has black disk
                                if (grid[row][col + 1] == 2) {

                                    //if valid move is detected at right direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of right direction line
                                    //and place white disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has black disk
                                if (grid[row + 1][col] == 2) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has black disk
                                if (grid[row + 1][col + 1] == 2) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 7) {
                                //check if top-left grid has black disk
                                if (grid[row - 1][col - 1] == 2) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has black disk
                                if (grid[row - 1][col] == 2) {

                                    //if valid move is detected at top direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top direction line
                                    //and place white disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has black disk
                                if (grid[row][col - 1] == 2) {

                                    //if valid move is detected at left direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of left direction line
                                    //and place white disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has black disk
                                if (grid[row + 1][col - 1] == 2) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has black disk
                                if (grid[row + 1][col] == 2) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            }

                        } else if (row == 0) {
                            if (col > 0 && col < 7) {

                                //check if left grid has black disk
                                if (grid[row][col - 1] == 2) {

                                    //if valid move is detected at left direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of left direction line
                                    //and place white disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has black disk
                                if (grid[row][col + 1] == 2) {

                                    //if valid move is detected at right direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of right direction line
                                    //and place white disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has black disk
                                if (grid[row + 1][col - 1] == 2) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has black disk
                                if (grid[row + 1][col] == 2) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has black disk
                                if (grid[row + 1][col + 1] == 2) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 0) {

                                //check if right grid has black disk
                                if (grid[row][col + 1] == 2) {

                                    //if valid move is detected at right direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of right direction line
                                    //and place white disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has black disk
                                if (grid[row + 1][col] == 2) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has black disk
                                if (grid[row + 1][col + 1] == 2) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 7) {

                                //check if left grid has black disk
                                if (grid[row][col - 1] == 2) {

                                    //if valid move is detected at left direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of left direction line
                                    //and place white disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has black disk
                                if (grid[row + 1][col - 1] == 2) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has black disk
                                if (grid[row + 1][col] == 2) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                    //and place white disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            }
                        } else if (row == 7) {
                            if (col > 0 && col < 7) {

                                //check if top-left grid has black disk
                                if (grid[row - 1][col - 1] == 2) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has black disk
                                if (grid[row - 1][col] == 2) {

                                    //if valid move is detected at top direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top direction line
                                    //and place white disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has black disk
                                if (grid[row - 1][col + 1] == 2) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has black disk
                                if (grid[row][col - 1] == 2) {

                                    //if valid move is detected at left direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of left direction line
                                    //and place white disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has black disk
                                if (grid[row][col + 1] == 2) {

                                    //if valid move is detected at right direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of right direction line
                                    //and place white disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 0) {

                                //check if top grid has black disk
                                if (grid[row - 1][col] == 2) {

                                    //if valid move is detected at top direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top direction line
                                    //and place white disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has black disk
                                if (grid[row - 1][col + 1] == 2) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has black disk
                                if (grid[row][col + 1] == 2) {

                                    //if valid move is detected at right direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of right direction line
                                    //and place white disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 7) {
                                //check if top-left grid has black disk
                                if (grid[row - 1][col - 1] == 2) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                    //and place white disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has black disk
                                if (grid[row - 1][col] == 2) {

                                    //if valid move is detected at top direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of top direction line
                                    //and place white disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has black disk
                                if (grid[row][col - 1] == 2) {

                                    //if valid move is detected at left direction line,
                                    //flip all the black disks between selected grid to the white disk at the end of left direction line
                                    //and place white disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            }
                        }
                    }

                    //check to see if both the valid move and the disk changes were made using the boolean variable 'validMoveMade'
                    //if yes, reset this boolean variable back to false
                    //this is used to check if player has made valid move and the disk changes has completed.
                    //once the disk change is finished, this boolean variable will be used to check if it's ready
                    //to change player turn.
                    if (validMoveMade) {
                        grid[row][col] = 1
                        whosTurn = false
                        validMoveMade = false
                    }
                }
            }

            //if it's black disk's turn
            else {
                //first, check if black disk has any valid move available.
                //if no, set 'blackDone' variable as true to indicate that black disk
                //don't have anymore valid moves to makes and give white disk a turn
                if (!checkRemainingValidMoves(whosTurn)) {
                    blackDone = true
                    whosTurn = true
                    validMoveMade = false
                }

                else {
                    //Use a recursive function to check if it's valid move or not
                    //once the valid move has been made, use boolean variable to switch the player turn
                    //keep the player turn as it is until valid move has been made

                    //if statement to check if it's a empty grid that can place disk
                    if (grid[row][col] == 0) {
                        if (row > 0 && row < 7) {
                            if (col > 0 && col < 7) {

                                //check if top-left grid has white disk
                                if (grid[row - 1][col - 1] == 1) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has white disk
                                if (grid[row - 1][col] == 1) {

                                    //if valid move is detected at top direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top direction line
                                    //and place black disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has white disk
                                if (grid[row - 1][col + 1] == 1) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has white disk
                                if (grid[row][col - 1] == 1) {

                                    //if valid move is detected at left direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of left direction line
                                    //and place black disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has white disk
                                if (grid[row][col + 1] == 1) {

                                    //if valid move is detected at right direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of right direction line
                                    //and place black disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has white disk
                                if (grid[row + 1][col - 1] == 1) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has white disk
                                if (grid[row + 1][col] == 1) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has white disk
                                if (grid[row + 1][col + 1] == 1) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 0) {

                                //check if top grid has white disk
                                if (grid[row - 1][col] == 1) {

                                    //if valid move is detected at top direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top direction line
                                    //and place black disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has white disk
                                if (grid[row - 1][col + 1] == 1) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has white disk
                                if (grid[row][col + 1] == 1) {

                                    //if valid move is detected at right direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of right direction line
                                    //and place black disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has white disk
                                if (grid[row + 1][col] == 1) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has white disk
                                if (grid[row + 1][col + 1] == 1) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 7) {
                                //check if top-left grid has white disk
                                if (grid[row - 1][col - 1] == 1) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has white disk
                                if (grid[row - 1][col] == 1) {

                                    //if valid move is detected at top direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top direction line
                                    //and place black disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has white disk
                                if (grid[row][col - 1] == 1) {

                                    //if valid move is detected at left direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of left direction line
                                    //and place black disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has white disk
                                if (grid[row + 1][col - 1] == 1) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has white disk
                                if (grid[row + 1][col] == 1) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            }

                        } else if (row == 0) {
                            if (col > 0 && col < 7) {

                                //check if left grid has white disk
                                if (grid[row][col - 1] == 1) {

                                    //if valid move is detected at left direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of left direction line
                                    //and place black disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has white disk
                                if (grid[row][col + 1] == 1) {

                                    //if valid move is detected at right direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of right direction line
                                    //and place black disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has white disk
                                if (grid[row + 1][col - 1] == 1) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has white disk
                                if (grid[row + 1][col] == 1) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has white disk
                                if (grid[row + 1][col + 1] == 1) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 0) {

                                //check if right grid has white disk
                                if (grid[row][col + 1] == 1) {

                                    //if valid move is detected at right direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of right direction line
                                    //and place black disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has white disk
                                if (grid[row + 1][col] == 1) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-right grid has white disk
                                if (grid[row + 1][col + 1] == 1) {

                                    //if valid move is detected at bottom-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomRight(whosTurn, row, col)) {
                                        changeBottomRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 7) {

                                //check if left grid has white disk
                                if (grid[row][col - 1] == 1) {

                                    //if valid move is detected at left direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of left direction line
                                    //and place black disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom-left grid has white disk
                                if (grid[row + 1][col - 1] == 1) {

                                    //if valid move is detected at bottom-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottomLeft(whosTurn, row, col)) {
                                        changeBottomLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if bottom grid has white disk
                                if (grid[row + 1][col] == 1) {

                                    //if valid move is detected at bottom direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                    //and place black disk at selected grid
                                    if (checkValidBottom(whosTurn, row, col)) {
                                        changeBottom(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            }
                        } else if (row == 7) {
                            if (col > 0 && col < 7) {

                                //check if top-left grid has white disk
                                if (grid[row - 1][col - 1] == 1) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has white disk
                                if (grid[row - 1][col] == 1) {

                                    //if valid move is detected at top direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top direction line
                                    //and place black disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has white disk
                                if (grid[row - 1][col + 1] == 1) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has white disk
                                if (grid[row][col - 1] == 1) {

                                    //if valid move is detected at left direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of left direction line
                                    //and place black disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has white disk
                                if (grid[row][col + 1] == 1) {

                                    //if valid move is detected at right direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of right direction line
                                    //and place black disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 0) {

                                //check if top grid has white disk
                                if (grid[row - 1][col] == 1) {

                                    //if valid move is detected at top direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top direction line
                                    //and place black disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top-right grid has white disk
                                if (grid[row - 1][col + 1] == 1) {

                                    //if valid move is detected at top-right diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopRight(whosTurn, row, col)) {
                                        changeTopRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if right grid has white disk
                                if (grid[row][col + 1] == 1) {

                                    //if valid move is detected at right direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of right direction line
                                    //and place black disk at selected grid
                                    if (checkValidRight(whosTurn, row, col)) {
                                        changeRight(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            } else if (col == 7) {
                                //check if top-left grid has white disk
                                if (grid[row - 1][col - 1] == 1) {

                                    //if valid move is detected at top-left diagonal direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                    //and place black disk at selected grid
                                    if (checkValidTopLeft(whosTurn, row, col)) {
                                        changeTopLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if top grid has white disk
                                if (grid[row - 1][col] == 1) {

                                    //if valid move is detected at top direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of top direction line
                                    //and place black disk at selected grid
                                    if (checkValidTop(whosTurn, row, col)) {
                                        changeTop(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }

                                //check if left grid has white disk
                                if (grid[row][col - 1] == 1) {

                                    //if valid move is detected at left direction line,
                                    //flip all the white disks between selected grid to the black disk at the end of left direction line
                                    //and place black disk at selected grid
                                    if (checkValidLeft(whosTurn, row, col)) {
                                        changeLeft(whosTurn, row, col)
                                        validMoveMade = true
                                    }
                                }
                            }
                        }
                    }

                    //check to see if both the valid move and the disk changes were made using the boolean variable 'validMoveMade'
                    //if yes, reset this boolean variable back to false
                    //this is used to check if player has made valid move and the disk changes has completed.
                    //once the disk change is finished, this boolean variable will be used to check if it's ready
                    //to change player turn.
                    if (validMoveMade) {
                        grid[row][col] = 2
                        whosTurn = true
                        validMoveMade = false
                    }
                }

            }

            //once the white and black disk runs out of valid moves, the game will be finished
            if ((whiteDone && blackDone) || !checkEmptyGrid()) {
                gameStarted = false
                gameSet = true
            }
        }
        */

        //MainActivity().updateDisplay()
        //*I need to call updateDisplay to update the game data but calling it here causes the app to crash*
        //triggerUpdate()

        invalidate()

        // gives signal to main so that data display are updated
        triggerUpdate()

        return true
    }



    //recursive functions used to count disks on specific direction of line

    fun countTop(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's below the top-most grid
            if (r > 0) {
                //if there's another white disk at next grid
                if (grid[r-1][c] == 1) {
                    diskTotal = countTop(user, r - 1, c, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's below the top-most grid
            if (r > 0) {
                //if there's another black disk at next grid
                if (grid[r-1][c] == 2) {
                    diskTotal = countTop(user, r - 1, c, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }

    fun countTopLeft(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's below the top-most and away from eft-most grid
            if (r > 0 && c > 0) {
                //if there's another white disk at next grid
                if (grid[r-1][c-1] == 1) {
                    diskTotal = countTopLeft(user, r - 1, c - 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's below the top-most and away from left-most grid
            if (r > 0 && c > 0) {
                //if there's another black disk at next grid
                if (grid[r-1][c-1] == 2) {
                    diskTotal = countTopLeft(user, r - 1, c - 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }

    fun countTopRight(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's below the top-most and away from right-most grid
            if (r > 0 && c < 13) {
                //if there's another white disk at next grid
                if (grid[r-1][c+1] == 1) {
                    diskTotal = countTopRight(user, r - 1, c + 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's below the top-most and away from right-most grid
            if (r > 0 && c < 13) {
                //if there's another black disk at next grid
                if (grid[r-1][c+1] == 2) {
                    diskTotal = countTopRight(user, r - 1, c + 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }

    fun countLeft(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's away from left-most grid
            if (c > 0) {
                //if there's another white disk at next grid
                if (grid[r][c-1] == 1) {
                    diskTotal = countLeft(user, r, c - 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's away from left-most grid
            if (c > 0) {
                //if there's another black disk at next grid
                if (grid[r][c-1] == 2) {
                    diskTotal = countLeft(user, r, c - 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }

    fun countRight(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's away from right-most grid
            if (c < 13) {
                //if there's another white disk at next grid
                if (grid[r][c+1] == 1) {
                    diskTotal = countRight(user, r, c + 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's away from right-most grid
            if (c < 13) {
                //if there's another black disk at next grid
                if (grid[r][c+1] == 2) {
                    diskTotal = countRight(user, r, c + 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }

    fun countBottom(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's above the bottom-most grid
            if (r < 13) {
                //if there's another white disk at next grid
                if (grid[r+1][c] == 1) {
                    diskTotal = countBottom(user, r + 1, c, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's above the bottom-most grid
            if (r < 13) {
                //if there's another black disk at next grid
                if (grid[r+1][c] == 2) {
                    diskTotal = countBottom(user, r + 1, c, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }

    fun countBottomLeft(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's below the top-most and away from eft-most grid
            if (r < 13 && c > 0) {
                //if there's another white disk at next grid
                if (grid[r+1][c-1] == 1) {
                    diskTotal = countBottomLeft(user, r + 1, c - 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's below the top-most and away from left-most grid
            if (r < 13 && c > 0) {
                //if there's another black disk at next grid
                if (grid[r+1][c-1] == 2) {
                    diskTotal = countBottomLeft(user, r + 1, c - 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }

    fun countBottomRight(user: Boolean, r: Int, c: Int, disk: Int): Int {
        var diskTotal = 0

        //count white disk
        if (user == true) {
            //if it's below the top-most and away from eft-most grid
            if (r < 13 && c < 13) {
                //if there's another white disk at next grid
                if (grid[r+1][c+1] == 1) {
                    diskTotal = countBottomRight(user, r + 1, c + 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        //count black disk
        else {
            //if it's below the top-most and away from left-most grid
            if (r < 13 && c < 13) {
                //if there's another black disk at next grid
                if (grid[r+1][c+1] == 2) {
                    diskTotal = countBottomRight(user, r + 1, c + 1, disk + 1)
                }

                //if not, then just set 'diskTotal' as 'disk'
                else {
                    diskTotal = disk
                }
            }

            //if it reaches the top-most grid, just set 'diskTotal' as 'disk'
            else {
                diskTotal = disk
            }
        }

        return diskTotal
    }




    /* ONLY USED FOR OTHELLO GAME
    //recursive functions used to check if user's move is valid or not

    //recursive function for checking on top direction
    fun checkValidTop(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0) {
                //check if top grid has black disk. If yes, keep recursively call the function
                if (grid[r-1][c] == 2) {
                    isitValid = checkValidTop(user, r - 1, c)
                }

                //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c] == 1) {
                    return true
                }

                //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then top direction is invalid
            else if (r <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0) {
                //check if top grid has white disk. If yes, keep recursively call the function
                if (grid[r-1][c] == 1) {
                    isitValid = checkValidTop(user, r - 1, c)
                }

                //check if top grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c] == 2) {
                    return true
                }

                //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then top direction is invalid
            else if (r <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on top-left direction
    fun checkValidTopLeft(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r > 0 && c > 0) {
                //check if top-left grid has black disk. If yes, keep recursively call the function
                if (grid[r-1][c-1] == 2) {
                    isitValid = checkValidTopLeft(user, r - 1, c - 1)
                }

                //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c-1] == 1) {
                    return true
                }

                //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then top-left direction is invalid
            else if (r <= 0 || c <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r > 0 && c > 0) {
                //check if top-left grid has white disk. If yes, keep recursively call the function
                if (grid[r-1][c-1] == 1) {
                    isitValid = checkValidTopLeft(user, r - 1, c - 1)
                }

                //check if top-left grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c-1] == 2) {
                    return true
                }

                //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then top-left direction is invalid
            else if (r <= 0 || c <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on top-right direction
    fun checkValidTopRight(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r > 0 && c < 7) {
                //check if top-right grid has black disk. If yes, keep recursively call the function
                if (grid[r-1][c+1] == 2) {
                    isitValid = checkValidTopRight(user, r - 1, c + 1)
                }

                //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c+1] == 1) {
                    return true
                }

                //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then top-right direction is invalid
            else if (r <= 0 || c >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r > 0 && c < 7) {
                //check if top-right grid has white disk. If yes, keep recursively call the function
                if (grid[r-1][c+1] == 1) {
                    isitValid = checkValidTopRight(user, r - 1, c + 1)
                }

                //check if top-right grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c+1] == 2) {
                    return true
                }

                //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then top-right direction is invalid
            else if (r <= 0 || c >= 7) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on left direction
    fun checkValidLeft(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (c > 0) {
                //check if left grid has black disk. If yes, keep recursively call the function
                if (grid[r][c-1] == 2) {
                    isitValid = checkValidLeft(user, r, c - 1)
                }

                //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c-1] == 1) {
                    return true
                }

                //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then left direction is invalid
            else if (c <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (c > 0) {
                //check if left grid has white disk. If yes, keep recursively call the function
                if (grid[r][c-1] == 1) {
                    isitValid = checkValidLeft(user, r, c - 1)
                }

                //check if left grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c-1] == 2) {
                    return true
                }

                //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then left direction is invalid
            else if (c <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on right direction
    fun checkValidRight(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (c < 7) {
                //check if right grid has black disk. If yes, keep recursively call the function
                if (grid[r][c+1] == 2) {
                    isitValid = checkValidRight(user, r, c + 1)
                }

                //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c+1] == 1) {
                    return true
                }

                //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then right direction is invalid
            else if (c >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (c < 7) {
                //check if right grid has white disk. If yes, keep recursively call the function
                if (grid[r][c+1] == 1) {
                    isitValid = checkValidRight(user, r, c + 1)
                }

                //check if right grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c+1] == 2) {
                    return true
                }


                //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then right direction is invalid
            else if (c >= 7) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on bottom direction
    fun checkValidBottom(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r < 7) {
                //check if bottom grid has black disk. If yes, keep recursively call the function
                if (grid[r+1][c] == 2) {
                    isitValid = checkValidBottom(user, r + 1, c)
                }

                //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c] == 1) {
                    return true
                }

                //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then bottom direction is invalid
            else if (r >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r < 7) {
                //check if bottom grid has white disk. If yes, keep recursively call the function
                if (grid[r+1][c] == 1) {
                    isitValid = checkValidBottom(user, r + 1, c)
                }

                //check if bottom grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c] == 2) {
                    return true
                }

                //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then bottom direction is invalid
            else if (r >= 7) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on bottom-left direction
    fun checkValidBottomLeft(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r < 7 && c > 0) {
                //check if bottom-left grid has black disk. If yes, keep recursively call the function
                if (grid[r+1][c-1] == 2) {
                    isitValid = checkValidBottomLeft(user, r + 1, c - 1)
                }

                //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c-1] == 1) {
                    return true
                }

                //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then bottom-left direction is invalid
            else if (r >= 7 || c <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r < 7 && c > 0) {
                //check if bottom-left grid has white disk. If yes, keep recursively call the function
                if (grid[r+1][c-1] == 1) {
                    isitValid = checkValidBottomLeft(user, r + 1, c - 1)
                }

                //check if bottom-left grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c-1] == 2) {
                    return true
                }

                //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then bottom-left direction is invalid
            else if (r >= 7 || c <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on bottom-right direction
    fun checkValidBottomRight(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r < 7 && c < 7) {
                //check if bottom-right grid has black disk. If yes, keep recursively call the function
                if (grid[r+1][c+1] == 2) {
                    isitValid = checkValidBottomRight(user, r + 1, c + 1)
                }

                //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c+1] == 1) {
                    return true
                }

                //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then bottom-right direction is invalid
            else if (r >= 7 || c >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r < 7 && c < 7) {
                //check if bottom-right grid has white disk. If yes, keep recursively call the function
                if (grid[r+1][c+1] == 1) {
                    isitValid = checkValidBottomRight(user, r + 1, c + 1)
                }

                //check if bottom-right grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c+1] == 2) {
                    return true
                }

                //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither blacke disk or empty grid was still found,
            //then bottom-right direction is invalid
            else if (r >= 7 || c >= 7) {
                return false
            }
        }

        return isitValid
    }




    //recursive functions used to replace certain disks after valid move is detected

    //recursive function for making changes on top direction
    fun changeTop(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0) {
                //check if top grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on top direction
                //if not, recursion will be stopped, indicating that top direction is done for change
                if (grid[r-1][c] == 2) {
                    grid[r-1][c] = 1
                    changeTop(user, r - 1, c)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0) {
                //check if top grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top direction
                //if not, recursion will be stopped, indicating that top direction is done for change
                if (grid[r-1][c] == 1) {
                    grid[r-1][c] = 2
                    changeTop(user, r - 1, c)
                }
            }
        }
    }

    //recursive function for making changes on top-left direction
    fun changeTopLeft(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0 && c > 0) {
                //check if top-left grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on top-left direction
                //if not, recursion will be stopped, indicating that top-left direction is done for change
                if (grid[r-1][c-1] == 2) {
                    grid[r-1][c-1] = 1
                    changeTopLeft(user, r - 1, c - 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0 && c > 0) {
                //check if top-left grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top-left direction
                //if not, recursion will be stopped, indicating that top-left direction is done for change
                if (grid[r-1][c-1] == 1) {
                    grid[r-1][c-1] = 2
                    changeTopLeft(user, r - 1, c - 1)
                }
            }
        }
    }

    //recursive function for making changes on top-right direction
    fun changeTopRight(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0 && c < 7) {
                //check if top-right grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on top-right direction
                //if not, recursion will be stopped, indicating that top-right direction is done for change
                if (grid[r-1][c+1] == 2) {
                    grid[r-1][c+1] = 1
                    changeTopRight(user, r - 1, c + 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0 && c < 7) {
                //check if top-right grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top-right direction
                //if not, recursion will be stopped, indicating that top-right direction is done for change
                if (grid[r-1][c+1] == 1) {
                    grid[r-1][c+1] = 2
                    changeTopRight(user, r - 1, c + 1)
                }
            }
        }
    }

    //recursive function for making changes on left direction
    fun changeLeft(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (c > 0) {
                //check if left grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on left direction
                //if not, recursion will be stopped, indicating that left direction is done for change
                if (grid[r][c-1] == 2) {
                    grid[r][c-1] = 1
                    changeLeft(user, r, c - 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (c > 0) {
                //check if top-left grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top-left direction
                //if not, recursion will be stopped, indicating that top-left direction is done for change
                if (grid[r][c-1] == 1) {
                    grid[r][c-1] = 2
                    changeLeft(user, r, c - 1)
                }
            }
        }
    }

    //recursive function for making changes on right direction
    fun changeRight(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (c < 7) {
                //check if right grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on right direction
                //if not, recursion will be stopped, indicating that right direction is done for change
                if (grid[r][c+1] == 2) {
                    grid[r][c+1] = 1
                    changeRight(user, r, c + 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (c < 7) {
                //check if right grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on right direction
                //if not, recursion will be stopped, indicating that right direction is done for change
                if (grid[r][c+1] == 1) {
                    grid[r][c+1] = 2
                    changeRight(user, r, c + 1)
                }
            }
        }
    }

    //recursive function for making changes on bottom direction
    fun changeBottom(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r < 7) {
                //check if bottom grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on bottom direction
                //if not, recursion will be stopped, indicating that bottom direction is done for change
                if (grid[r+1][c] == 2) {
                    grid[r+1][c] = 1
                    changeBottom(user, r + 1, c)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r < 7) {
                //check if bottom grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on bottom direction
                //if not, recursion will be stopped, indicating that bottom direction is done for change
                if (grid[r+1][c] == 1) {
                    grid[r+1][c] = 2
                    changeBottom(user, r + 1, c)
                }
            }
        }
    }

    //recursive function for making changes on bottom-left direction
    fun changeBottomLeft(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r < 7 && c > 0) {
                //check if bottom-left grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on bottom-left direction
                //if not, recursion will be stopped, indicating that bottom-left direction is done for change
                if (grid[r+1][c-1] == 2) {
                    grid[r+1][c-1] = 1
                    changeBottomLeft(user, r + 1, c - 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r < 7 && c > 0) {
                //check if bottom-left grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on bottom-left direction
                //if not, recursion will be stopped, indicating that bottom-left direction is done for change
                if (grid[r+1][c-1] == 1) {
                    grid[r+1][c-1] = 2
                    changeBottomLeft(user, r + 1, c - 1)
                }
            }
        }
    }

    //recursive function for making changes on bottom-right direction
    fun changeBottomRight(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r < 7 && c < 7) {
                //check if bottom-right grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on bottom-right direction
                //if not, recursion will be stopped, indicating that bottom-right direction is done for change
                if (grid[r+1][c+1] == 2) {
                    grid[r+1][c+1] = 1
                    changeBottomRight(user, r + 1, c + 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r < 7 && c < 7) {
                //check if bottom-right grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on bottom-right direction
                //if not, recursion will be stopped, indicating that bottom-right direction is done for change
                if (grid[r+1][c+1] == 1) {
                    grid[r+1][c+1] = 2
                    changeBottomRight(user, r + 1, c + 1)
                }
            }
        }
    }



    // function used to check if there are valid moves remaining
    fun checkRemainingValidMoves(user: Boolean): Boolean {
        var areThereValidMoves : Boolean = false

        //check if there are any valid moves remained for white disk
        if (user) {
            //go through the entire board and find at least one possible valid move available for white disk
            for (i in grid.indices) {
                if (i > 0 && i < 7) {
                    for (j in grid[i].indices) {
                        if (grid[i][j] == 0) {
                            if (j > 0 && j < 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 2) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 2) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 2) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 2) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 2) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 2) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 2) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 2) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                            } else if (j == 0) {
                                //top
                                if (grid[i - 1][j] == 2) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 2) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 2) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 2) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 2) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 2) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 2) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 2) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 2) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 2) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                            }
                        }
                    }
                }

                else if (i == 0) {
                    for (j in grid[i].indices) {
                        if (grid[i][j] == 0) {
                            if (j > 0 && j < 7) {
                                //left
                                if (grid[i][j - 1] == 2) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 2) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 2) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 2) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 2) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 0) {
                                //right
                                if (grid[i][j + 1] == 2) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 2) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 2) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 7) {
                                //left
                                if (grid[i][j - 1] == 2) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 2) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 2) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            }
                        }
                    }
                }

                else if (i == 7) {
                    for (j in grid[i].indices) {
                        if (grid[i][j] == 0) {
                            if (j > 0 && j < 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 2) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 2) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 2) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 2) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 2) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 0) {
                                //top
                                if (grid[i - 1][j] == 2) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 2) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 2) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 2) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 2) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 2) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        //check if there are any valid moves remained for black disk
        else {
            //go through the entire board and find at least one possible valid move available for black disk
            for (i in grid.indices) {
                if (i > 0 && i < 7) {
                    for (j in grid[i].indices) {
                        if (grid[i][j] == 0) {
                            if (j > 0 && j < 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 1) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 1) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 1) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 1) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 1) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 1) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 1) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 1) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                            } else if (j == 0) {
                                //top
                                if (grid[i - 1][j] == 1) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 1) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 1) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 1) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 1) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 1) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 1) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 1) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 1) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 1) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                            }
                        }
                    }
                }

                else if (i == 0) {
                    for (j in grid[i].indices) {
                        if (grid[i][j] == 0) {
                            if (j > 0 && j < 7) {
                                //left
                                if (grid[i][j - 1] == 1) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 1) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 1) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 1) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 1) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 0) {
                                //right
                                if (grid[i][j + 1] == 1) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 1) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-right
                                if (grid[i + 1][j + 1] == 1) {
                                    if (checkValidBottomRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 7) {
                                //left
                                if (grid[i][j - 1] == 1) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom-left
                                if (grid[i + 1][j - 1] == 1) {
                                    if (checkValidBottomLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //bottom
                                if (grid[i + 1][j] == 1) {
                                    if (checkValidBottom(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            }
                        }
                    }
                }

                else if (i == 7) {
                    for (j in grid[i].indices) {
                        if (grid[i][j] == 0) {
                            if (j > 0 && j < 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 1) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 1) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 1) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 1) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 1) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 0) {
                                //top
                                if (grid[i - 1][j] == 1) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top-right
                                if (grid[i - 1][j + 1] == 1) {
                                    if (checkValidTopRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //right
                                if (grid[i][j + 1] == 1) {
                                    if (checkValidRight(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            } else if (j == 7) {
                                //top-left
                                if (grid[i - 1][j - 1] == 1) {
                                    if (checkValidTopLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //top
                                if (grid[i - 1][j] == 1) {
                                    if (checkValidTop(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }

                                //left
                                if (grid[i][j - 1] == 1) {
                                    if (checkValidLeft(user, i, j)) {
                                        areThereValidMoves = true
                                        break
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        return areThereValidMoves
    }
    */


    //check if board any empty grid remaining
    fun checkEmptyGrid(): Boolean {
        var areThereEmptyGrid : Boolean = false

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 0) {
                    areThereEmptyGrid = true
                    break
                }
            }
        }

        return areThereEmptyGrid
    }


    // function used to give signal to main so that data display should be updated
    // after valid moves were made by players
    fun triggerUpdate() {
        val main = context as MainActivity
        main.updateDisplay()
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        // not used
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }

}