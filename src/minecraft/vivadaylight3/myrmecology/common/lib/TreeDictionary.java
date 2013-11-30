/**
 * 
 */
package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;

import vivadaylight3.myrmecology.common.Log;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author samueltebbs
 */
public class TreeDictionary {

    public static final int treeWidth = 5;
    public static final int treeHeight = 10;

    private static ArrayList<TreeEntry> treeEntries = new ArrayList<TreeEntry>();

    public static void registerVanillaTrees() {

	addTreeEntry(new TreeEntry(new BlockIDEntry(Block.wood.blockID),
		new BlockIDEntry(Block.leaves.blockID)));
	addTreeEntry(new TreeEntry(new BlockIDEntry(Block.wood.blockID, 1),
		new BlockIDEntry(Block.leaves.blockID, 1)));
	addTreeEntry(new TreeEntry(new BlockIDEntry(Block.wood.blockID, 2),
		new BlockIDEntry(Block.leaves.blockID, 2)));
	addTreeEntry(new TreeEntry(new BlockIDEntry(Block.wood.blockID, 3),
		new BlockIDEntry(Block.leaves.blockID, 3)));

    }

    public static boolean contains(BlockIDEntry entry) {

	for (TreeEntry log : treeEntries) {

	    for (BlockIDEntry block : log.getLogIDs()) {

		if (block.equals(entry)) {

		    return true;

		}

	    }

	}

	return false;

    }

    @Override
    public String toString() {

	String result = "";

	for (TreeEntry entry : treeEntries) {

	    result = result + entry.toString();

	}

	return result;

    }

    public static void registerTreesFromOreDict(boolean existsCheck) {

	ArrayList<BlockIDEntry> leafList = new ArrayList<BlockIDEntry>();
	ArrayList<BlockIDEntry> logList = new ArrayList<BlockIDEntry>();

	int leafID = OreDictionary.getOreID("treeLeaves");

	for (ItemStack leaf : OreDictionary.getOres(leafID)) {

	    leafList.add(new BlockIDEntry(leaf.itemID, leaf.getItemDamage()));

	}

	int logID = OreDictionary.getOreID("logWood");

	for (ItemStack log : OreDictionary.getOres(logID)) {

	    logList.add(new BlockIDEntry(log.itemID, log.getItemDamage()));

	}

	for (int k = 0; k < leafList.size(); k++) {

	    if (existsCheck) {

		if (getTreeEntryFromLog(logList.get(k)) != null
			&& getTreeEntryFromLeaf(leafList.get(k)) != null) {

		    addTreeEntry(new TreeEntry(logList.get(k), leafList.get(k)));

		}

	    } else {

		addTreeEntry(new TreeEntry(logList.get(k), leafList.get(k)));

	    }

	}

    }

    public static void updateTreesFromOreDict() {

	registerTreesFromOreDict(true);

    }

    public static void addTreeEntry(TreeEntry entry) {

	treeEntries.add(entry);

    }

    public static TreeEntry getTreeEntryFromLog(BlockIDEntry entry) {

	for (TreeEntry check : treeEntries) {

	    for (BlockIDEntry block : check.getLogIDs()) {

		if (block.equals(entry)) {

		    return check;

		}

	    }

	}

	return null;

    }

    public static TreeEntry getTreeEntryFromLeaf(BlockIDEntry entry) {

	for (TreeEntry check : treeEntries) {

	    for (BlockIDEntry block : check.getLeafIDs()) {

		if (block.equals(entry)) {

		    return check;

		}

	    }

	}

	return null;

    }

}
