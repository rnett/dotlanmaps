package com.rnett.ligraph.eve.dotlanmaps

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import java.io.File

const val REGION_COUNT = 64

object DotlanMaps {

    private val _maps = mutableMapOf<Int, DotlanRegion>()

    val maps get() = _maps as Map<Int, DotlanRegion>

    init{
        DotlanMaps("allRegions.json", "dotlanMaps.json", "dotlanMaps")
    }

    operator fun invoke(vararg files: String){
        files.forEach { addMapFile(it) }
    }

    fun addMapFile(file: String){

        if(_maps.count() >= REGION_COUNT)
            return

        val mapFile = File(file)

        if(!mapFile.exists())
            return

        if(mapFile.isDirectory)
            mapFile.listFiles().forEach { addMapFile(it.absolutePath) }
        else{
            try{
                val dr = DotlanRegion(mapFile.absolutePath)

                if(dr.regionName == "" || dr.regionID == 0)
                    throw Exception()

                _maps[dr.regionID] = dr
            } catch(e: Exception){
            }
            try{
                val drs = Gson().fromJson<Map<Int, DotlanRegion>>(mapFile.readText())

                if(drs.values.all { it.regionName == "" || it.regionID == 0 } || drs.isEmpty())
                    throw Exception()

                _maps.putAll(drs)
            } catch(e: Exception){
            }
        }
    }

    fun getMap(regionID: Int): DotlanRegion = _maps[regionID]!!
    fun getMap(regionName: String): DotlanRegion = _maps.values.find { it.regionName == regionName }!!

    operator fun get(regionID: Int) = getMap(regionID)
    operator fun get(regionName: String) = getMap(regionName)
}