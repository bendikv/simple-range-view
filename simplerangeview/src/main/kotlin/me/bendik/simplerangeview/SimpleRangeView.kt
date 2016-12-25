/*
 * Copyright (C) 2016 Vitaliy Bendik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.bendik.simplerangeview

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

open class SimpleRangeView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var labelColor = DEFAULT_LABEL_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var activeLabelColor = DEFAULT_ACTIVE_LABEL_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var activeThumbLabelColor = DEFAULT_ACTIVE_THUMB_LABEL_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var fixedLabelColor = DEFAULT_FIXED_LABEL_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var fixedThumbLabelColor = DEFAULT_FIXED_THUMB_LABEL_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }
    
    
    var lineColor = DEFAULT_LINE_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var activeLineColor = DEFAULT_ACTIVE_LINE_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var fixedLineColor = DEFAULT_FIXED_LINE_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var tickColor = DEFAULT_TICK_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var activeTickColor = DEFAULT_ACTIVE_TICK_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var fixedTickColor = DEFAULT_FIXED_TICK_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var activeThumbColor = DEFAULT_ACTIVE_THUMB_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var activeFocusThumbColor = DEFAULT_ACTIVE_FOCUS_THUMB_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var fixedThumbColor = DEFAULT_FIXED_THUMB_COLOR
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var activeFocusThumbAlpha: Float = DEFAULT_ACTIVE_FOCUS_THUMB_ALPHA
        set(value) {
            field = value
            initPaints()
            invalidate()
        }

    var lineThickness = 0f
        set(value) {
            field = value
            updateView()
        }

    var activeLineThickness = 0f
        set(value) {
            field = value
            updateView()
        }

    var fixedLineThickness = 0f
        set(value) {
            field = value
            updateView()
        }

    var tickRadius = 0f
        set(value) {
            field = value
            updateView()
        }

    var activeThumbFocusRadius = 0f
        set(value) {
            field = value
            updateView()
        }

    var activeThumbRadius = 0f
        set(value) {
            field = value
            updateView()
        }

    var activeTickRadius = 0f
        set(value) {
            field = value
            updateView()
        }

    var fixedThumbRadius = 0f
        set(value) {
            field = value
            updateView()
        }

    var fixedTickRadius = 0f
        set(value) {
            field = value
            updateView()
        }

    var labelFontSize = 0f
        set(value) {
            field = value
            updateView()
        }

    var count = DEFAULT_COUNT
        set(value) {
            field = value
            invalidate()
        }
    var start = DEFAULT_START
        set(value) {
            field = closestValidPosition(value)
            invalidate()
        }
    var end = DEFAULT_END
        set(value) {
            field = closestValidPosition(value)
            invalidate()
        }
    var minDistance = DEFAULT_MINIMAL_DISTANCE
        set(value) {
            field = value
            invalidate()
        }

    var movable = DEFAULT_MOVABLE

    var showFixedLine = DEFAULT_SHOW_FIXED_LINE
        set(value) {
            field = value
            invalidate()
        }

    var startFixed = DEFAULT_START_FIXED
        set(value) {
            field = value
            start = start
            invalidate()
        }

    var endFixed = DEFAULT_END_FIXED
        set(value) {
            field = value
            end = end
            invalidate()
        }

    var showTicks = DEFAULT_SHOW_TICKS
        set(value) {
            field = value
            invalidate()
        }

    var showActiveTicks = DEFAULT_SHOW_ACTIVE_TICKS
        set(value) {
            field = value
            invalidate()
        }

    var showFixedTicks = DEFAULT_SHOW_FIXED_TICKS
        set(value) {
            field = value
            invalidate()
        }

    var showLabels = DEFAULT_SHOW_LABELS
        set(value) {
            field = value
            updateView()
        }

    var labelMarginBottom = 0f
        set(value) {
            field = value
            updateView()
        }

    var minDistanceBetweenLabels = 0f
        set(value) {
            field = value
            updateView()
        }

    var innerRangePadding = 0f
        set(value) {
            field = value
            innerRangePaddingLeft = value
            innerRangePaddingRight = value
        }

    var innerRangePaddingLeft = 0f
        set(value) {
            field = value
            updateView()
        }
    var innerRangePaddingRight = 0f
        set(value) {
            field = value
            updateView()
        }


    // Callbacks

    var onChangeRangeListener: OnChangeRangeListener? = null
    var onTrackRangeListener: OnTrackRangeListener? = null
    var onRangeLabelsListener: OnRangeLabelsListener? = null


    // Internal

    lateinit private var paint: Paint
    lateinit private var paintFixed: Paint
    lateinit private var paintActive: Paint
    lateinit private var paintTick: Paint
    lateinit private var paintFixedTick: Paint
    lateinit private var paintActiveTick: Paint
    lateinit private var paintActiveThumb: Paint
    lateinit private var paintFixedThumb: Paint
    lateinit private var paintActiveFocusThumb: Paint
    lateinit private var paintText: Paint
    lateinit private var paintActiveText: Paint
    lateinit private var paintFixedText: Paint
    lateinit private var paintActiveThumbText: Paint
    lateinit private var paintFixedThumbText: Paint

    private var currentLeftFocusRadiusPx = ValueWrapper(0f)
    private var currentRightFocusRadiusPx = ValueWrapper(0f)

    private var posY = 0f
    private var lineY = 0f
    private var lineYActive = 0f
    private var lineYFixed = 0f
    private var stepPx = 0f

    private var isEndPressed: Boolean = false
    private var isStartPressed: Boolean = false
    private var isRangeMoving: Boolean = false

    private var linePosToStart = 0

    init {
        applyDefaultValues()

        if (attrs != null) {
            //get attributes passed in XML
            val styledAttrs = context.obtainStyledAttributes(attrs,
                    R.styleable.SimpleRangeView, 0, 0)
            labelColor = styledAttrs.getColor(R.styleable.SimpleRangeView_labelColor, labelColor)
            activeLabelColor = styledAttrs.getColor(R.styleable.SimpleRangeView_activeLabelColor, activeLabelColor)
            activeThumbLabelColor = styledAttrs.getColor(R.styleable.SimpleRangeView_activeThumbLabelColor, activeThumbLabelColor)
            fixedLabelColor = styledAttrs.getColor(R.styleable.SimpleRangeView_fixedLabelColor, fixedLabelColor)
            fixedThumbLabelColor = styledAttrs.getColor(R.styleable.SimpleRangeView_fixedThumbLabelColor, fixedThumbLabelColor)

            lineColor = styledAttrs.getColor(R.styleable.SimpleRangeView_lineColor, lineColor)
            activeLineColor = styledAttrs.getColor(R.styleable.SimpleRangeView_activeLineColor, activeLineColor)
            fixedLineColor = styledAttrs.getColor(R.styleable.SimpleRangeView_fixedLineColor, fixedLineColor)
            tickColor = styledAttrs.getColor(R.styleable.SimpleRangeView_tickColor, tickColor)
            activeTickColor = styledAttrs.getColor(R.styleable.SimpleRangeView_activeTickColor, activeTickColor)
            fixedTickColor = styledAttrs.getColor(R.styleable.SimpleRangeView_fixedTickColor, fixedTickColor)
            activeThumbColor = styledAttrs.getColor(R.styleable.SimpleRangeView_activeThumbColor, activeThumbColor)
            activeFocusThumbColor = styledAttrs.getColor(R.styleable.SimpleRangeView_activeFocusThumbColor, activeThumbColor)
            fixedThumbColor = styledAttrs.getColor(R.styleable.SimpleRangeView_fixedThumbColor, fixedThumbColor)

            activeFocusThumbAlpha = styledAttrs.getFloat(R.styleable.SimpleRangeView_activeFocusThumbAlpha, activeFocusThumbAlpha)

            lineThickness = styledAttrs.getDimension(R.styleable.SimpleRangeView_lineThickness, lineThickness)
            activeLineThickness = styledAttrs.getDimension(R.styleable.SimpleRangeView_activeLineThickness, activeLineThickness)
            fixedLineThickness = styledAttrs.getDimension(R.styleable.SimpleRangeView_fixedLineThickness, fixedLineThickness)
            activeThumbRadius = styledAttrs.getDimension(R.styleable.SimpleRangeView_activeThumbRadius, activeThumbRadius)
            activeThumbFocusRadius = styledAttrs.getDimension(R.styleable.SimpleRangeView_activeThumbFocusRadius, activeThumbFocusRadius)
            fixedThumbRadius = styledAttrs.getDimension(R.styleable.SimpleRangeView_fixedThumbRadius, fixedThumbRadius)
            tickRadius = styledAttrs.getDimension(R.styleable.SimpleRangeView_tickRadius, tickRadius)
            activeTickRadius = styledAttrs.getDimension(R.styleable.SimpleRangeView_activeTickRadius, activeTickRadius)
            fixedTickRadius = styledAttrs.getDimension(R.styleable.SimpleRangeView_fixedTickRadius, fixedTickRadius)
            labelMarginBottom = styledAttrs.getDimension(R.styleable.SimpleRangeView_labelMarginBottom, labelMarginBottom)
            labelFontSize = styledAttrs.getDimension(R.styleable.SimpleRangeView_labelFontSize, labelFontSize)
            minDistanceBetweenLabels = styledAttrs.getDimension(R.styleable.SimpleRangeView_minDistanceBetweenLabels, minDistanceBetweenLabels)
            innerRangePadding = Math.max(calcMaxRadius(), styledAttrs.getDimension(R.styleable.SimpleRangeView_innerRangePadding, innerRangePadding))
            innerRangePaddingLeft = Math.max(calcMaxRadius(), styledAttrs.getDimension(R.styleable.SimpleRangeView_innerRangePaddingLeft, innerRangePadding))
            innerRangePaddingRight = Math.max(calcMaxRadius(), styledAttrs.getDimension(R.styleable.SimpleRangeView_innerRangePaddingRight, innerRangePadding))

            count = styledAttrs.getInt(R.styleable.SimpleRangeView_count, count)
            startFixed = styledAttrs.getInt(R.styleable.SimpleRangeView_startFixed, startFixed)
            endFixed = styledAttrs.getInt(R.styleable.SimpleRangeView_endFixed, endFixed)
            start = styledAttrs.getInt(R.styleable.SimpleRangeView_start, start)
            end = styledAttrs.getInt(R.styleable.SimpleRangeView_end, end)
            minDistance = styledAttrs.getInt(R.styleable.SimpleRangeView_minDistance, minDistance)

            movable = styledAttrs.getBoolean(R.styleable.SimpleRangeView_movable, movable)
            showFixedLine = styledAttrs.getBoolean(R.styleable.SimpleRangeView_showFixedLine, showFixedLine)
            showTicks = styledAttrs.getBoolean(R.styleable.SimpleRangeView_showTicks, showTicks)
            showActiveTicks = styledAttrs.getBoolean(R.styleable.SimpleRangeView_showActiveTicks, showActiveTicks)
            showFixedTicks = styledAttrs.getBoolean(R.styleable.SimpleRangeView_showFixedTicks, showFixedTicks)
            showLabels = styledAttrs.getBoolean(R.styleable.SimpleRangeView_showLabels, showLabels)

            styledAttrs.recycle()
        }

        initPaints()
    }

    private fun applyDefaultValues() {
        val scale = context.resources.displayMetrics.density

        lineThickness = DEFAULT_LINE_THICKNESS * scale
        activeLineThickness = DEFAULT_ACTIVE_LINE_THICKNESS * scale
        fixedLineThickness = DEFAULT_FIXED_LINE_THICKNESS * scale
        activeThumbRadius = DEFAULT_ACTIVE_THUMB_RADIUS * scale
        activeThumbFocusRadius = DEFAULT_ACTIVE_THUMB_FOCUS_RADIUS * scale
        fixedThumbRadius = DEFAULT_FIXED_THUMB_RADIUS * scale
        tickRadius = DEFAULT_TICK_RADIUS * scale
        activeTickRadius = DEFAULT_ACTIVE_TICK_RADIUS * scale
        fixedTickRadius = DEFAULT_FIXED_TICK_RADIUS * scale
        labelMarginBottom = DEFAULT_LABEL_MARGIN_BOTTOM * scale
        labelFontSize = DEFAULT_LABEL_FONT_SIZE * scale
        minDistanceBetweenLabels = DEFAULT_MINIMAL_DISTANCE_BETWEEN_LABELS * scale
        innerRangePadding = DEFAULT_INNER_RANGE_PADDING * scale
        innerRangePaddingLeft = DEFAULT_INNER_RANGE_PADDING_LEFT * scale
        innerRangePaddingRight = DEFAULT_INNER_RANGE_PADDING_RIGHT * scale
    }

    private fun defaultValIfZero(value : Float, defValue: Float) = if (value == 0f) defValue else value

    private fun initPaints() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = lineColor

        paintFixed = Paint(Paint.ANTI_ALIAS_FLAG)
        paintFixed.style = Paint.Style.FILL
        paintFixed.color = fixedLineColor

        paintFixedTick = Paint(Paint.ANTI_ALIAS_FLAG)
        paintFixedTick.style = Paint.Style.FILL
        paintFixedTick.color = fixedTickColor

        paintActive = Paint(Paint.ANTI_ALIAS_FLAG)
        paintActive.style = Paint.Style.FILL
        paintActive.color = activeLineColor

        paintTick = Paint(Paint.ANTI_ALIAS_FLAG)
        paintTick.style = Paint.Style.FILL
        paintTick.color = tickColor

        paintActiveTick = Paint(Paint.ANTI_ALIAS_FLAG)
        paintActiveTick.style = Paint.Style.FILL
        paintActiveTick.color = activeTickColor

        paintActiveThumb = Paint(Paint.ANTI_ALIAS_FLAG)
        paintActiveThumb.style = Paint.Style.FILL
        paintActiveThumb.color = activeThumbColor

        paintFixedThumb = Paint(Paint.ANTI_ALIAS_FLAG)
        paintFixedThumb.style = Paint.Style.FILL
        paintFixedThumb.color = fixedThumbColor

        paintActiveFocusThumb = Paint(Paint.ANTI_ALIAS_FLAG)
        paintActiveFocusThumb.style = Paint.Style.FILL
        paintActiveFocusThumb.color = activeFocusThumbColor
        paintActiveFocusThumb.alpha = (activeFocusThumbAlpha * 255).toInt()

        paintText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintText.style = Paint.Style.FILL
        paintText.color = labelColor
        paintText.textSize = labelFontSize
        paintText.textAlign = Paint.Align.CENTER
        paintText.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)

        paintActiveText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintActiveText.style = Paint.Style.FILL
        paintActiveText.color = activeLabelColor
        paintActiveText.textSize = labelFontSize
        paintActiveText.textAlign = Paint.Align.CENTER
        paintActiveText.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)

        paintFixedText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintFixedText.style = Paint.Style.FILL
        paintFixedText.color = fixedLabelColor
        paintFixedText.textSize = labelFontSize
        paintFixedText.textAlign = Paint.Align.CENTER
        paintFixedText.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)

        paintActiveThumbText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintActiveThumbText.style = Paint.Style.FILL
        paintActiveThumbText.color = activeThumbLabelColor
        paintActiveThumbText.textSize = labelFontSize
        paintActiveThumbText.textAlign = Paint.Align.CENTER
        paintActiveThumbText.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)

        paintFixedThumbText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintFixedThumbText.style = Paint.Style.FILL
        paintFixedThumbText.color = fixedThumbLabelColor
        paintFixedThumbText.textSize = labelFontSize
        paintFixedThumbText.textAlign = Paint.Align.CENTER
        paintFixedThumbText.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {

            // Draw range lines
            drawLines(canvas)

            // Draw ticks
            drawTicks(canvas)

            // Draw fixed range thumbs
            drawFixedThumbs(canvas)

            // Draw active range thumbs
            drawActiveThumbs(canvas)

            // Draw labels
            drawLabels(canvas)
        }
    }

    /**
     * Draw range lines
     */
    protected open fun drawLines(canvas: Canvas) {
        val xActiveStart = getPositionX(start)
        val xActiveEnd = getPositionX(end) - xActiveStart
        val xFixedStart = getPositionX(startFixed)
        val xFixedEnd = getPositionX(endFixed) - xFixedStart

        drawLine(canvas, innerRangePaddingLeft, lineY, width.toFloat() - (innerRangePaddingRight + innerRangePaddingLeft), lineThickness, paint)
        drawFixedLine(canvas, xFixedStart, lineYFixed, xFixedEnd, fixedLineThickness)
        drawActiveLine(canvas, xActiveStart, lineYActive, xActiveEnd, activeLineThickness)
    }

    /**
     * Draw range ticks
     */
    protected open fun drawTicks(canvas: Canvas) {
        if (showTicks) {
            val left = if (showFixedLine) startFixed else start
            val right = if (showFixedLine) endFixed else Math.min(end+1, count)

            for (i in 0 until left) {
                val x = getPositionX(i)
                canvas.drawCircle(x, getPositionY(), tickRadius, paintTick)
            }
            for (i in right until count) {
                val x = getPositionX(i)
                canvas.drawCircle(x, getPositionY(), tickRadius, paintTick)
            }
        }

        if (showFixedLine && showFixedTicks) {
            for (i in startFixed until start) {
                val x = getPositionX(i)
                canvas.drawCircle(x, getPositionY(), fixedTickRadius, paintFixedTick)
            }
            for (i in end until endFixed) {
                val x = getPositionX(i)
                canvas.drawCircle(x, getPositionY(), fixedTickRadius, paintFixedTick)
            }
        }

        if (showActiveTicks) {
            for (i in start+1 until end) {
                val x = getPositionX(i)
                canvas.drawCircle(x, getPositionY(), tickRadius, paintActiveTick)
            }
        }
    }

    /**
     * Draw labels
     */
    protected open fun drawLabels(canvas: Canvas) {
        if (showLabels) {
            val left = if (showFixedLine) startFixed else start
            val right = if (showFixedLine) endFixed else Math.min(end+1, count)

            for (i in 0..count) {
                val x = getPositionX(i)
                when(i) {
                    start, end -> drawLabel(canvas, x, i, State.ACTIVE_THUMB, paintActiveThumbText)
                    in start+1 until end -> drawLabel(canvas, x, i, State.ACTIVE, paintActiveText)
                    startFixed, endFixed -> drawLabel(canvas, x, i, State.FIXED_THUMB, paintFixedThumbText)
                    in startFixed until start, in end until endFixed -> drawLabel(canvas, x, i, State.FIXED, paintFixedText)
                    in 0 until left, in right until count -> drawLabel(canvas, x, i, State.NORMAL, paintText)
                }
            }
        }
    }

    /**
     * Draw fixed thumbs
     */
    protected open fun drawFixedThumbs(canvas: Canvas) {
        if (showFixedLine) {
            for (i in listOf(startFixed, endFixed)) {
                val x = getPositionX(i)
                canvas.drawCircle(x, getPositionY(), fixedThumbRadius, paintFixedThumb)
                if (showFixedTicks) {
                    canvas.drawCircle(x, getPositionY(), fixedTickRadius, paintFixedTick)
                }
            }
        }
    }

    /**
     * Draw active thumbs
     */
    protected open fun drawActiveThumbs(canvas: Canvas) {
        drawActiveThumb(canvas, start, currentLeftFocusRadiusPx)
        drawActiveThumb(canvas, end, currentRightFocusRadiusPx)
    }

    protected open fun drawLine(canvas: Canvas, x: Float, y: Float, w: Float, h: Float, paint: Paint) {
        canvas.drawRect(x, y, x+w, y+h, paint)
    }

    protected open fun drawFixedLine(canvas: Canvas, x: Float, y: Float, w: Float, h: Float) {
        if (showFixedLine) {
            drawLine(canvas, x, y, w, h, paintFixed)
        }
    }

    protected open fun drawActiveLine(canvas: Canvas, x: Float, y: Float, w: Float, h: Float) {
        drawLine(canvas, x, y, w, h, paintActive)
    }

    protected open fun drawActiveThumb(canvas: Canvas, i: Int, size: ValueWrapper<Float>) {
        val x = getPositionX(i)

        // Draw focus
        if (size.value > 0f) {
            canvas.drawCircle(x, getPositionY(), size.value, paintActiveFocusThumb)
        }

        canvas.drawCircle(x, getPositionY(), activeThumbRadius, paintActiveThumb)
        if (showActiveTicks) {
            canvas.drawCircle(x, getPositionY(), activeTickRadius, paintActiveTick)
        }
    }

    protected open fun drawLabel(canvas: Canvas, x: Float, pos: Int, state: State, paint: Paint) {
        if (showLabels) {
            val text = onRangeLabelsListener?.getLabelTextForPosition(this, pos, state)
            if (text != null) {
                canvas.drawText(text, x, getPositionY() - labelMarginBottom, paint)
            }
        }
    }

    private fun getPositionX(i: Int): Float = (innerRangePaddingLeft + stepPx * i).toFloat()
    private fun getPositionY() = posY

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (!isEnabled) return false

        parent.requestDisallowInterceptTouchEvent(true)

        when (event.action) {
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_DOWN -> {
                val x = event.x
                val y = event.y

                if (isRangeMoving) {
                    val size = end - start
                    var _start = closestValidPosition(getPositionByXCoord(x) - linePosToStart)
                    val _end = closestValidPosition(_start + size)
                    _start = _end - size

                    if (validatePosition(_start) && validatePosition(_end)) {
                        start = _start
                        end = _end
                        invalidate()
                        onTrackRangeListener?.onStartRangeChanged(this, start)
                        onTrackRangeListener?.onEndRangeChanged(this, end)
                    }
                    return true
                }

                if (isStartPressed) {
                    val _start = closestValidPosition(getPositionByXCoord(x))
                    if (validatePositionForStart(_start)) {
                        if (start != _start) {
                            start = _start
                            invalidate()
                            onTrackRangeListener?.onStartRangeChanged(this, start)
                        }
                    }
                    return true
                }

                if (isEndPressed) {
                    val _end = closestValidPosition(getPositionByXCoord(x))
                    if (validatePositionForEnd(_end)) {
                        if (end != _end) {
                            end = _end
                            invalidate()
                            onTrackRangeListener?.onEndRangeChanged(this, end)
                        }
                    }
                    return true
                }

                if (isInTargetZone(start, x, y)) {
                    isStartPressed = true
                    fadeIn(currentLeftFocusRadiusPx, activeThumbFocusRadius)
                    return true
                }

                if (isInTargetZone(end, x, y)) {
                    isEndPressed = true
                    fadeIn(currentRightFocusRadiusPx, activeThumbFocusRadius)
                    return true
                }

                if (!isStartPressed && !isEndPressed) {
                    if ((getPositionByXCoord(x) in start until end) && movable) {
                        isRangeMoving = true
                        linePosToStart = getPositionByXCoord(x) - start
                    } else {
                        val tmpX = closestValidPosition(getPositionByXCoord(x))
                        val xS = Math.abs(tmpX - start)
                        val xE = Math.abs(tmpX - end)

                        if (xS < xE && ((end-tmpX) >= minDistance)) {
                            start = tmpX
                            isStartPressed = true
                            fadeIn(currentLeftFocusRadiusPx, activeThumbFocusRadius)
                            onTrackRangeListener?.onStartRangeChanged(this, start)
                        } else if (xS >= xE && ((tmpX-start) >= minDistance)) {
                            end = tmpX
                            isEndPressed = true
                            fadeIn(currentRightFocusRadiusPx, activeThumbFocusRadius)
                            onTrackRangeListener?.onEndRangeChanged(this, end)
                        }
                    }
                    return true
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isRangeMoving = false

                if (isStartPressed) {
                    isStartPressed = false
                    fadeOut(currentLeftFocusRadiusPx, activeThumbFocusRadius)
                }

                if (isEndPressed) {
                    isEndPressed = false
                    fadeOut(currentRightFocusRadiusPx, activeThumbFocusRadius)
                }

                onChangeRangeListener?.onRangeChanged(this, start, end)
            }
        }

        return true
    }

    private fun closestValidPosition(pos: Int): Int {
        if (showFixedLine) {
            if (pos < startFixed) {
                return startFixed
            }

            if (pos > endFixed) {
                return endFixed
            }
        } else {
            if (pos < 0) {
                return 0
            }

            if (pos >= count) {
                return count-1
            }
        }
        return pos
    }

    private fun isPressed(pos: Int) = (pos == start && isStartPressed) || (pos == end && isEndPressed)
    private fun validatePosition(pos: Int) = if (showFixedLine) pos >= startFixed && pos <= endFixed else pos >= 0 && pos < count
    private fun validatePositionForStart(pos: Int) = validatePosition(pos) && (pos <= end-minDistance)
    private fun validatePositionForEnd(pos: Int) = validatePosition(pos) && (pos >= start+minDistance)

    private fun getPositionByXCoord(x: Float) = ((x-innerRangePaddingLeft) / stepPx).toInt()

    private fun isInTargetZone(pos: Int, x: Float, y: Float): Boolean {
        val xDiff = Math.abs(x - getPositionX(pos))
        val yDiff = Math.abs(y - getPositionY())
        return  xDiff <= activeThumbRadius && yDiff <= activeThumbRadius
    }

    private fun updateView() {
        initPaints()
        requestLayout()
    }

    private fun getTextRect(text: String, paint: Paint): Rect {
        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        return rect
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val desiredHeight = calcDesiredHeight()
        var desiredWidth = calcDesiredWidth()

        var width: Int
        var height: Int

        if (widthMode === MeasureSpec.EXACTLY) {
            width = widthSize
        } else if (widthMode === MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize)
        } else {
            width = desiredWidth
        }

        if (heightMode === MeasureSpec.EXACTLY) {
            height = heightSize
        } else if (heightMode === MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize)
        } else {
            height = desiredHeight
        }

        if (width < 0) {
            width = 0
        }

        if (height < 0) {
            height = 0
        }

        setMeasuredDimension(width, height)
    }

    private fun calcDesiredWidth(): Int {
        return 0
    }

    private fun calcMaxRadius() = listOf(tickRadius, fixedTickRadius, activeTickRadius, activeThumbRadius, fixedThumbRadius, activeThumbFocusRadius).max()!!

    private fun calcMaxHeight(): Float {
        val maxRadius = calcMaxRadius() * 2
        val maxLineHeight = listOf(lineThickness, activeLineThickness, fixedLineThickness).max()!!
        return Math.max(maxRadius, maxLineHeight)
    }

    private fun calcDesiredHeight(): Int {
        val h = calcMaxHeight() / 2
        val labelHeight =  Math.max(if (showLabels && onRangeLabelsListener != null) labelMarginBottom+labelFontSize else 0f, h)
        return (h + labelHeight).toInt()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val yOffset = calcMaxHeight() / 2.0f

        posY = h - yOffset
        lineY = posY - lineThickness / 2.0f
        lineYActive = posY - activeLineThickness / 2.0f
        lineYFixed = posY - fixedLineThickness / 2.0f

        stepPx = (w - (innerRangePaddingLeft+innerRangePaddingRight)) / (count-1)
    }


    //
    // Animations
    //

    private val fadeInAnim: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
    private val fadeOutAnim: ValueAnimator = ValueAnimator.ofFloat(1f, 0f)

    private fun fadeIn(value: ValueWrapper<Float>, normalValue: Float) = fadeInAnim.apply {
        duration = 200
        addUpdateListener {
            value.value = (animatedValue as Float) * (normalValue)
            ViewCompat.postInvalidateOnAnimation(this@SimpleRangeView)
        }
        addListener(object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                value.value = normalValue
                removeAllListeners()
                removeAllUpdateListeners()
            }
        })
        start()
    }

    private fun fadeOut(value: ValueWrapper<Float>, normalValue: Float) = fadeOutAnim.apply {
        duration = 200

        addUpdateListener {
            value.value = (animatedValue as Float) * (normalValue)
            ViewCompat.postInvalidateOnAnimation(this@SimpleRangeView)
        }

        addListener(object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                value.value = 0f
                removeAllListeners()
                removeAllUpdateListeners()
            }
        })

        start()
    }

    //
    // Internal classes
    //

    class ValueWrapper<T>(var value: T) {
        // Empty body
    }

    open class SimpleAnimatorListener : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            // no-op
        }

        override fun onAnimationEnd(animation: Animator?) {
            // no-op
        }

        override fun onAnimationCancel(animation: Animator?) {
            // no-op
        }

        override fun onAnimationStart(animation: Animator?) {
            // no-op
        }

    }

    enum class State {
        ACTIVE, ACTIVE_THUMB, FIXED, FIXED_THUMB, NORMAL
    }

    //
    // Callbacks
    //

    interface OnChangeRangeListener {
        fun onRangeChanged(rangeView: SimpleRangeView, start: Int, end: Int)
    }

    interface OnTrackRangeListener {
        fun onStartRangeChanged(rangeView: SimpleRangeView, start: Int)
        fun onEndRangeChanged(rangeView: SimpleRangeView, end: Int)
    }

    interface OnRangeLabelsListener {
        fun getLabelTextForPosition(rangeView: SimpleRangeView, pos: Int, state: State): String?
    }

    //
    // Builder
    //

    class Builder(context: Context) {
        private var rangeView: SimpleRangeView

        init {
            rangeView = SimpleRangeView(context)
        }

        fun labelColor(color: Int): Builder {
            rangeView.labelColor = color
            return this
        }

        fun activeLabelColor(color: Int): Builder {
            rangeView.activeLabelColor = color
            return this
        }

        fun activeThumbLabelColor(color: Int): Builder {
            rangeView.activeThumbLabelColor = color
            return this
        }

        fun fixedLabelColor(color: Int): Builder {
            rangeView.fixedLabelColor = color
            return this
        }

        fun fixedThumbLabelColor(color: Int): Builder {
            rangeView.fixedThumbLabelColor = color
            return this
        }

        fun lineColor(color: Int): Builder {
            rangeView.lineColor = color
            return this
        }

        fun activeLineColor(color: Int): Builder {
            rangeView.activeLineColor = color
            return this
        }

        fun fixedLineColor(color: Int): Builder {
            rangeView.fixedLineColor = color
            return this
        }

        fun tickColor(color: Int): Builder {
            rangeView.tickColor = color
            return this
        }

        fun activeTickColor(color: Int): Builder {
            rangeView.activeTickColor = color
            return this
        }

        fun fixedTickColor(color: Int): Builder {
            rangeView.fixedTickColor = color
            return this
        }

        fun activeThumbColor(color: Int): Builder {
            rangeView.activeThumbColor = color
            return this
        }

        fun activeFocusThumbColor(color: Int): Builder {
            rangeView.activeFocusThumbColor = color
            return this
        }

        fun fixedThumbColor(color: Int): Builder {
            rangeView.fixedThumbColor = color
            return this
        }

        fun activeFocusThumbAlpha(alpha: Float): Builder {
            rangeView.activeFocusThumbAlpha = alpha
            return this
        }

        fun lineThickness(px: Float): Builder {
            rangeView.lineThickness = px
            return this
        }

        fun activeLineThickness(px: Float): Builder {
            rangeView.activeLineThickness = px
            return this
        }

        fun fixedLineThickness(px: Float): Builder {
            rangeView.fixedLineThickness = px
            return this
        }

        fun activeThumbFocusRadius(px: Float): Builder {
            rangeView.activeThumbFocusRadius = px
            return this
        }

        fun activeThumbRadius(px: Float): Builder {
            rangeView.activeThumbRadius = px
            return this
        }

        fun fixedThumbRadius(px: Float): Builder {
            rangeView.fixedThumbRadius = px
            return this
        }

        fun tickRadius(px: Float): Builder {
            rangeView.tickRadius = px
            return this
        }

        fun activeTickRadius(px: Float): Builder {
            rangeView.activeTickRadius = px
            return this
        }

        fun fixedTickRadius(px: Float): Builder {
            rangeView.fixedTickRadius = px
            return this
        }

        fun innerRangePadding(px: Float): Builder {
            rangeView.innerRangePadding = px
            return this
        }

        fun innerRangePaddingLeft(px: Float): Builder {
            rangeView.innerRangePaddingLeft = px
            return this
        }

        fun innerRangePaddingRight(px: Float): Builder {
            rangeView.innerRangePaddingRight = px
            return this
        }

        fun labelMarginBottom(px: Float): Builder {
            rangeView.labelMarginBottom = px
            return this
        }

        fun minDistanceBetweenLabels(px: Float): Builder {
            rangeView.minDistanceBetweenLabels = px
            return this
        }

        fun labelFontSize(px: Float): Builder {
            rangeView.labelFontSize = px
            return this
        }

        fun count(value: Int): Builder {
            rangeView.count = value
            return this
        }

        fun start(value: Int): Builder {
            rangeView.start = value
            return this
        }

        fun end(value: Int): Builder {
            rangeView.end = value
            return this
        }

        fun startFixed(value: Int): Builder {
            rangeView.startFixed = value
            return this
        }

        fun endFixed(value: Int): Builder {
            rangeView.endFixed = value
            return this
        }

        fun minDistance(value: Int): Builder {
            rangeView.minDistance = value
            return this
        }

        fun showFixedLine(value: Boolean): Builder {
            rangeView.showFixedLine = value
            return this
        }

        fun movable(value: Boolean): Builder {
            rangeView.movable = value
            return this
        }

        fun showTicks(value: Boolean): Builder {
            rangeView.showTicks = value
            return this
        }

        fun showActiveTicks(value: Boolean): Builder {
            rangeView.showActiveTicks = value
            return this
        }

        fun showFixedTicks(value: Boolean): Builder {
            rangeView.showFixedTicks = value
            return this
        }

        fun showLabels(value: Boolean): Builder {
            rangeView.showLabels = value
            return this
        }

        fun onChangeRangeListener(listener: OnChangeRangeListener): Builder {
            rangeView.onChangeRangeListener = listener
            return this
        }

        fun onRangeLabelsListener(listener: OnRangeLabelsListener): Builder {
            rangeView.onRangeLabelsListener = listener
            return this
        }

        fun onTrackRangeListener(listener: OnTrackRangeListener): Builder {
            rangeView.onTrackRangeListener = listener
            return this
        }

        fun build() = rangeView
    }

    //
    // Default values
    //

    private companion object DefaultValues {
        // Colors
        val DEFAULT_LABEL_COLOR = Color.parseColor("#C5C5C5")
        val DEFAULT_ACTIVE_LABEL_COLOR = Color.parseColor("#0C6CE1")
        val DEFAULT_ACTIVE_THUMB_LABEL_COLOR = Color.parseColor("#0F7BFF")
        val DEFAULT_FIXED_LABEL_COLOR = Color.parseColor("#C5C5C5")
        val DEFAULT_FIXED_THUMB_LABEL_COLOR = Color.parseColor("#C5C5C5")

        val DEFAULT_LINE_COLOR = Color.parseColor("#F7F7F7")
        val DEFAULT_ACTIVE_LINE_COLOR = Color.parseColor("#0C6CE1")
        val DEFAULT_FIXED_LINE_COLOR = Color.parseColor("#E3E3E3")
        val DEFAULT_TICK_COLOR = Color.parseColor("#C5C5C5")
        val DEFAULT_ACTIVE_TICK_COLOR = Color.parseColor("#FFFFFF")
        val DEFAULT_FIXED_TICK_COLOR = Color.parseColor("#C5C5C5")
        val DEFAULT_ACTIVE_THUMB_COLOR = Color.parseColor("#0F7BFF")
        val DEFAULT_ACTIVE_FOCUS_THUMB_COLOR = DEFAULT_ACTIVE_THUMB_COLOR
        val DEFAULT_FIXED_THUMB_COLOR = Color.parseColor("#E3E3E3")

        val DEFAULT_ACTIVE_FOCUS_THUMB_ALPHA = 1f

        val DEFAULT_LINE_THICKNESS = 4f
        val DEFAULT_ACTIVE_LINE_THICKNESS = 6f
        val DEFAULT_FIXED_LINE_THICKNESS = 6f

        val DEFAULT_ACTIVE_THUMB_FOCUS_RADIUS = 14f
        val DEFAULT_ACTIVE_THUMB_RADIUS = 10f
        val DEFAULT_FIXED_THUMB_RADIUS = 10f

        val DEFAULT_TICK_RADIUS = 1f
        val DEFAULT_ACTIVE_TICK_RADIUS = 1f
        val DEFAULT_FIXED_TICK_RADIUS = 1f

        val DEFAULT_INNER_RANGE_PADDING = 16f
        val DEFAULT_INNER_RANGE_PADDING_LEFT = DEFAULT_INNER_RANGE_PADDING
        val DEFAULT_INNER_RANGE_PADDING_RIGHT = DEFAULT_INNER_RANGE_PADDING

        val DEFAULT_COUNT = 10
        val DEFAULT_START = 0
        val DEFAULT_END = 9
        val DEFAULT_MINIMAL_DISTANCE = 1

        val DEFAULT_START_FIXED = 0
        val DEFAULT_END_FIXED = 0

        val DEFAULT_SHOW_FIXED_LINE = false
        val DEFAULT_MOVABLE = false
        val DEFAULT_SHOW_TICKS = true
        val DEFAULT_SHOW_ACTIVE_TICKS = true
        val DEFAULT_SHOW_FIXED_TICKS = true
        val DEFAULT_SHOW_LABELS = true

        val DEFAULT_LABEL_MARGIN_BOTTOM = 16f
        val DEFAULT_MINIMAL_DISTANCE_BETWEEN_LABELS = 20f // TODO

        val DEFAULT_LABEL_FONT_SIZE = 12f
    }
}

