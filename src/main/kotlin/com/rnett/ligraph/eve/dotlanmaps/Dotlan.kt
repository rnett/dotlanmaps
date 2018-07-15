package com.rnett.ligraph.eve.dotlanmaps

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import java.io.File

class DotlanRegion(val width: Int = 1024,
                   val height: Int = 768,
                   val regionName: String = "",
                   val regionID: Int = 0,
                   val jumps: List<DotlanJump> = emptyList(),
                   val systems: Map<Int, DotlanSystem> = emptyMap()
) {

    val regionSystems get() = systems.filterValues { it.inRegion }
    val outOfRegionSystems get() = systems.filterValues { !it.inRegion }

    companion object {
        operator fun invoke(file: String) = Gson().fromJson<DotlanRegion>(File(file).readText())
    }
}

class DotlanJump(
        val startSystemID: Int = 0,
        val startSystemName: String = "",
        val endSystemID: Int = 0,
        val endSystemName: String = "",
        val startX: Int = 0,
        val endX: Int = 0,
        val startY: Int = 0,
        val endY: Int = 0,
        val type: Type = Type.System
)  {

    enum class Type{
        System, Constellation, Region
    }

    val deltaX get() = endX - startX
    val deltaY get() = endY - startY

    override fun toString() = "$startSystemName - $endSystemName : $type"

}

class DotlanSystem(
        val systemID: Int = 0,
        val systemName: String = "",
        val constellationID: Int = 0,
        val constellationName: String = "",
        val regionID: Int = 0,
        val regionName: String = "",
        val xPos: Int = 0,
        val yPos: Int = 0,
        val inRegion: Boolean = true
){
    override fun toString() = systemName
}