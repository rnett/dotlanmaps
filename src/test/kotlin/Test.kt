import com.rnett.ligraph.eve.dotlanmaps.DotlanMaps
import com.rnett.ligraph.eve.dotlanmaps.display

fun main(args: Array<String>) {
    val maps = DotlanMaps.maps

    val map = DotlanMaps["Cache"].display

    map.scale = 2.0

}