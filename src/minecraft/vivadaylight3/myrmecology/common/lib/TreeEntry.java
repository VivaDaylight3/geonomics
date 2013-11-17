package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;

public class TreeEntry {

    private ArrayList<BlockIDEntry> logIDs = new ArrayList<BlockIDEntry>();
    private ArrayList<BlockIDEntry> leafIDs = new ArrayList<BlockIDEntry>();

    public TreeEntry(BlockIDEntry[] logArray, BlockIDEntry[] leafArray) {

	for (BlockIDEntry log : logArray) {

	    logIDs.add(log);

	}

	for (BlockIDEntry leaf : leafArray) {

	    leafIDs.add(leaf);

	}

    }

    public ArrayList<BlockIDEntry> getLogIDs() {

	return logIDs;

    }

    public ArrayList<BlockIDEntry> getLeafIDs() {

	return leafIDs;

    }

    public TreeEntry(BlockIDEntry log, BlockIDEntry leaf) {

	logIDs.add(log);

	leafIDs.add(leaf);

    }

    @Override
    public String toString() {

	String result = "";

	result = result + "[Log>";

	for (BlockIDEntry block : this.getLogIDs()) {

	    result = result + block.toString();

	}

	result = result + "/Leaf>";

	for (BlockIDEntry leaf : this.getLeafIDs()) {

	    result = result + leaf.toString();

	}

	result = result + "]";

	return result;

    }

    @Override
    public boolean equals(Object entry) {

	int numEqual = 0;

	if (entry instanceof TreeEntry) {

	    for (BlockIDEntry block : logIDs) {

		if (block.equals(entry)) {

		    numEqual++;

		}

	    }

	    if (numEqual > 0) {

		return true;

	    }

	}

	return false;

    }

}
