package vivadaylight3.myrmecology.common.lib;

import vivadaylight3.myrmecology.common.Log;

public class BlockIDEntry {

    private int blockID;
    private int blockMeta;

    public BlockIDEntry(int id, int metadata) {

	blockID = id;
	blockMeta = metadata;

    }

    public BlockIDEntry(int id) {

	blockID = id;
	blockMeta = 0;

    }

    @Override
    public String toString() {

	return this.getBlockID() + ":" + this.getBlockMeta() + ",";

    }

    public int getBlockID() {
	return blockID;
    }

    public void setBlockID(int blockID) {
	this.blockID = blockID;
    }

    public int getBlockMeta() {
	return blockMeta;
    }

    public void setBlockMeta(int blockMeta) {
	this.blockMeta = blockMeta;
    }

    @Override
    public boolean equals(Object entry) {

	if (entry instanceof BlockIDEntry) {

	    if (((BlockIDEntry) entry).getBlockID() == this.getBlockID()
		    && ((BlockIDEntry) entry).getBlockMeta() == this
			    .getBlockMeta()) {

		return true;

	    }

	}

	return false;

    }

}
