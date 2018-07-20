package com.rnett.ligraph.eve.dotlanmaps

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import java.io.File

const val REGION_COUNT = 64

object DotlanMaps {

    private val _maps = mutableMapOf<Int, DotlanRegion>()

    val maps get() = _maps as Map<Int, DotlanRegion>

    init{
        DotlanMaps("allRegions.json", "dotlanMaps.json", "dotlanMaps",
                "Geminate_10000029.json", "Aridia_10000054.json", "Genesis_10000067.json", "Providence_10000047.json",
                "Black Rise_10000069.json", "Great Wildlands_10000011.json", "Pure Blind_10000023.json", "Branch_10000055.json",
                "Heimatar_10000030.json", "Querious_10000050.json", "Cache_10000007.json", "Immensea_10000025.json",
                "Scalding Pass_10000008.json", "Catch_10000014.json", "Impass_10000031.json", "Sinq Laison_10000032.json",
                "Cloud Ring_10000051.json", "Insmother_10000009.json", "Solitude_10000044.json", "Cobalt Edge_10000053.json",
                "Kador_10000052.json", "Stain_10000022.json", "Curse_10000012.json", "Khanid_10000049.json",
                "Syndicate_10000041.json", "Deklein_10000035.json", "Kor-Azor_10000065.json", "Tash-Murkon_10000020.json",
                "Delve_10000060.json", "Lonetrek_10000016.json", "Tenal_10000045.json", "Derelik_10000001.json",
                "Malpais_10000013.json", "Tenerifis_10000061.json", "Detorid_10000005.json", "Metropolis_10000042.json",
                "The Bleak Lands_10000038.json", "Devoid_10000036.json", "Molden Heath_10000028.json", "The Citadel_10000033.json",
                "Domain_10000043.json", "Oasa_10000040.json", "The Forge_10000002.json", "Esoteria_10000039.json",
                "Omist_10000062.json", "The Kalevala Expanse_10000034.json", "Essence_10000064.json", "Outer Passage_10000021.json",
                "The Spire_10000018.json", "Etherium Reach_10000027.json", "Outer Ring_10000057.json", "Tribute_10000010.json",
                "Everyshore_10000037.json", "Paragon Soul_10000059.json", "Vale of the Silent_10000003.json", "Fade_10000046.json",
                "Period Basis_10000063.json", "Venal_10000015.json", "Feythabolis_10000056.json", "Perrigen Falls_10000066.json",
                "Verge Vendor_10000068.json", "Fountain_10000058.json", "Placid_10000048.json", "Wicked Creek_10000006.json"
        )
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