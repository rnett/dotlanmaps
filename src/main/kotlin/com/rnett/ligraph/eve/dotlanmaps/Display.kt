package com.rnett.ligraph.eve.dotlanmaps

import kotlin.math.roundToInt
import kotlin.properties.Delegates

val DotlanRegion.display get() = DisplayRegion(this)
fun DotlanRegion.display(scaleX: Double, scaleY: Double) = DisplayRegion(this, scaleX, scaleY)
fun DotlanRegion.display(scale: Double) = DisplayRegion(this, scale)

class DisplayRegion(val base: DotlanRegion, scaleX: Double, scaleY: Double){

    constructor(base: DotlanRegion, scale: Double = 1.0) : this(base, scale, scale)

    val jumps = base.jumps.map { DisplayJump(it) }
    val systems = base.systems.mapValues { DisplaySystem(it.value) }

    var width: Int
        get() = (base.width * scaleX).roundToInt()
        set(value){scaleX = value.toDouble() / base.width}
    var height: Int
        get() = (base.height * scaleY).roundToInt()
        set(value){scaleY = value.toDouble() / base.height}

    var scaleX: Double by Delegates.observable(1.0){ property, oldValue, newValue ->
        jumps.forEach { it.scaleX = newValue }
        systems.values.forEach { it.scaleX = newValue }
    }
    var scaleY: Double by Delegates.observable(1.0){ property, oldValue, newValue ->
        jumps.forEach { it.scaleY = newValue }
        systems.values.forEach { it.scaleY = newValue }
    }

    fun scale(scale: Double){
        scaleX = scale
        scaleY = scale
    }

    var scale: Double
        get() = -1.0
        set(value){scale(value)}

    init{
        this.scaleX = scaleX
        this.scaleY = scaleY
    }

}

class DisplayJump(val base: DotlanJump){
    val startX get() = base.startX * scaleX
    val startY get() = base.startY * scaleY

    val endX get() = base.endX * scaleX
    val endY get() = base.endY * scaleX

    var scaleX = 1.0
    var scaleY = 1.0
}

class DisplaySystem(val base: DotlanSystem){
    val xPos get() = base.xPos * scaleX
    val yPos get() = base.yPos * scaleY

    var scaleX = 1.0
    var scaleY = 1.0
}