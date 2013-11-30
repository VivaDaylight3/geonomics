package vivadaylight3.myrmecology.common.lib;

public class BlockPosEntry {

    public int xCoord;
    public int yCoord;
    public int zCoord;
    public int ID;
    public int metadata;

    public BlockPosEntry(int x, int y, int z, int id, int meta) {

	xCoord = x;
	yCoord = y;
	zCoord = z;
	ID = id;
	metadata = meta;

    }

    @Override
    public boolean equals(Object entry) {

	if (entry instanceof BlockPosEntry) {

	    if (this.xCoord == ((BlockPosEntry) entry).xCoord
		    && this.yCoord == ((BlockPosEntry) entry).yCoord
		    && this.zCoord == ((BlockPosEntry) entry).zCoord
		    && this.ID == ((BlockPosEntry) entry).ID
		    && this.metadata == ((BlockPosEntry) entry).metadata) {

		return true;

	    }

	}

	return false;

    }

    public BlockPosEntry clone() {

	return this;

    }

    @Override
    public String toString() {

	return this.xCoord + ", " + this.yCoord + ", " + this.zCoord;

    }

    public BlockIDEntry toBlockIDEntry() {

	return new BlockIDEntry(ID, metadata);

    }

    public int getxCoord() {
	return xCoord;
    }

    public void setxCoord(int xCoord) {
	this.xCoord = xCoord;
    }

    public int getyCoord() {
	return yCoord;
    }

    public void setyCoord(int yCoord) {
	this.yCoord = yCoord;
    }

    public int getzCoord() {
	return zCoord;
    }

    public void setzCoord(int zCoord) {
	this.zCoord = zCoord;
    }

    public int getID() {
	return ID;
    }

    public void setID(int iD) {
	ID = iD;
    }

    public int getMetadata() {
	return metadata;
    }

    public void setMetadata(int metadata) {
	this.metadata = metadata;
    }

}
