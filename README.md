myrmecology
===========

The Myrmecology mod for Minecraft

Textures
=========

To find what you'll need to call the textures that you create, look in the Reference class under: "myrmecology / src / minecraft / vivadaylight3 / myrmecology / common /".
There, each block, hill and ant is listed with their sub names.

The file names of ants should look ike this: <ant's subname>_<ant type>.png
Example: antForest_Queen.png
If you look at the ANT_FOREST_NAME variable, its value is "antForest".

The same goes for hills, but they have no type name (e.g Queen), so they would be like this: <hill's subname>.png
Example: antHillForest.png
If you look at the HILL_FOREST variable, its value is "antHillForest".

The file names for GUIs should look like this: <item/block's subname>.png
Example: antFarm.png
If you look at the BLOCK_ANTFARM_NAME variable, its value is "antFarm".

All block textures should go in "myrmecology / resources / assets / myrmecology / textures / blocks /"
All GUI textures should go in "myrmecology / resources / assets / myrmecology / textures / gui /"
All item textures (except ants) should go in "myrmecology / resources / assets / myrmecology / textures / items /"
All ant textures should go in "myrmecology / resources / assets / myrmecology / textures / items / ant /"
