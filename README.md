# Kotlin Dotlan Maps
Data and wrappers for Dotlan maps

Data files are in the dotlanMaps directory, readable files are in the dotlanMaps/pretty directory.

The `allRegions.json` file contains the maps for every (non-WH and non-jove) region, and the other files contain a map of a single region.

## Maps

All maps used here are property of Wollari, Daniel Hoffend, who runs [evemaps.dotlan.net](http://evemaps.dotlan.net/).
This is only possible because he made the maps available in svg form.
All Eve Related Materials (the systems and regions themselves) are Property Of CCP Games.

## Usage

The DotlanMaps object will load maps as it is provided with files or directories to search (directories are searched recursively, with no limit).
By default, it tries to find `allRegions.json`, `dotlanMaps.json`, and `dotlanMaps/`.

Additional files can be provided by using the invoke operator, like `DotlanMaps("file1", "file2", "folder")`.  All provided files/directories are saved.

Maps are accessed using the get operator, like `DotlanMaps[10000060]`.  
DotlanMaps also supports get by name, so `DotlanMaps["Delve"]` will return the same map as the previous statement.

DiaplayRegions are just scaled maps.  The map itself is unchanged, but the display map has its own systems and jumps that are scaled depending on `scaleX` and `scaleY`.
A new DisplayRegion can be obtained via DotlanRegion.display with optional scale parameters.

A slightly longer example:

Lets say we have maps for Delve and Fountain in `static/data/dotlanMaps`.
```
DotlanMaps("static/data/dotlanMaps")      // loads all maps in static/data/dotlanMaps/ and subdirectories

val delve = DotlanMaps["Delve"]           // gets the map for Delve (via name)
val fountain = DotlanMaps[10000058]       // gets the map for Fountain (which as a regionID of 10000058)

val delveDisplay2x = delve.display(2.0)   // gets a display map for Delve with an initial scale of 2.0x
```
