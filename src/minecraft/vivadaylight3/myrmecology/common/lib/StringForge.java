package vivadaylight3.myrmecology.common.lib;

import java.util.ArrayList;

/**
 * Creates an instance of StringForge. StringForge is used for advanced string
 * management.
 * 
 * @author samueltebbs
 * 
 */
public class StringForge {

    private ArrayList<String> strings = new ArrayList<String>();

    public StringForge() {
    }

    /**
     * Removes the given string
     * 
     * @param str
     */
    public void remove(String str) {

	int index = this.get(str);

	StringForge strFrg = new StringForge();

	for (int k = 0; k < this.size(); k++) {

	    if (k != index) {

		strFrg.append(this.get(k));

	    }

	}

	this.to(strFrg);

    }

    /**
     * Removes the given index
     * 
     * @param index
     */
    public void remove(int index) {

	StringForge strFrg = new StringForge();

	for (int k = 0; k < this.size(); k++) {

	    if (k != index) {

		strFrg.append(this.get(k));

	    }

	}

	this.to(strFrg);

    }

    /**
     * Appens the given StringForge to this one.
     * 
     * @param strFrg
     */
    public void apply(StringForge strFrg) {

	for (int k = 0; k < strFrg.size(); k++) {

	    this.append(strFrg.get(k));

	}

    }

    /**
     * Eleiminates all instances of the given string.
     * 
     * @param str
     */
    public void eliminate(String str) {

	for (int k = 0; k < this.size(); k++) {

	    if (this.get(k).contains(str)) {

		String[] pieces = this.get(k).split(str);

		this.set("", k);

		String result = "";

		for (int i = 0; i < pieces.length; i++) {

		    result += pieces[i];

		}

		this.set(result, k);

		pieces = null;

	    }

	}

    }

    /**
     * Removes the given string from the given index
     * 
     * @param str
     * @param index
     */
    public void eliminate(String str, int index) {

	if (this.get(index).contains(str)) {

	    String[] pieces = this.get(index).split(str);

	    this.set("", index);

	    String result = "";

	    for (int i = 0; i < pieces.length; i++) {

		result += pieces[i];

	    }

	    this.set(result, index);

	}

    }

    /**
     * Trims all strings into one index
     */
    public void trim() {

	String all = this.toString();
	this.clear();
	this.append(all);

    }

    /**
     * Clears all strings
     */
    public void clear() {

	this.strings.clear();

    }

    /**
     * Returns true if this is empty
     * 
     * @return
     */
    public boolean empty() {

	return strings.isEmpty();

    }

    /**
     * Sets the given index to the given string
     * 
     * @param str
     * @param index
     */
    public void set(String str, int index) {

	this.strings.set(index, str);

    }

    /**
     * Adds another index with the given string
     * 
     * @param str
     */
    public void append(String str) {

	strings.add(str);

    }

    /**
     * Places the given string at the given index and moves the current one at
     * that index to the end
     * 
     * @param str
     * @param index
     */
    public void place(String str, int index) {

	if (strings.size() < index) {

	    this.append(str);

	} else {

	    String curStr = strings.get(index);
	    strings.set(index, str);
	    this.append(curStr);

	}

    }

    /**
     * Gets the string at the given index
     * 
     * @param index
     * @return
     */
    public String get(int index) {

	if (this.size() > index) {

	    return strings.get(index);

	} else {

	    return null;

	}

    }

    /**
     * Getst he index of the given string
     * 
     * @param str
     * @return
     */
    public int get(String str) {

	return strings.indexOf(str);

    }

    /**
     * Returns the total length of all strings in this StringForge
     * 
     * @return
     */
    public int length() {

	return this.toString().length();

    }

    /**
     * Returns the length of the string at the given index
     * 
     * @param index
     * @return
     */
    public int length(int index) {

	return this.get(index).length();

    }

    /**
     * Returns the index size
     * 
     * @return
     */
    public int size() {

	return strings.size();

    }

    /**
     * Returns a String conversion of this StringForge
     */
    @Override
    public String toString() {

	String result = "";

	for (int k = 0; k < strings.size(); k++) {

	    result += this.get(k);

	}

	return result;

    }

    /**
     * Reverses the positions of this StringForge's strings. E.g a StringForge
     * with the strings: "Oh," "Hi" "there!" will become "there!" "Hi" "Oh"
     */
    public void reverse() {

	StringForge strFrg = new StringForge();
	strFrg.to(this);

	for (int k = 0; k < this.size(); k++) {

	    this.set(strFrg.get(k), this.size() - (k + 1));

	}

	strFrg.clear();

    }

    // TODO
    /**
     * -#Not finished#-
     * 
     * @param strLength
     * @return
     */
    @Deprecated
    public String[] move(int strLength) {

	ArrayList<String> result = new ArrayList<String>();

	for (int k = 0; k < this.size() - 1; k++) {

	    if (k == 0) {

		result.add(this.get(0));

	    } else {

		if (this.get(k).length() + result.get(k - 1).length() <= strLength) {

		    result.set(k - 1, this.get(k) + result.get(k - 1));

		} else {

		    result.add(k, this.get(k));

		}

	    }

	}

	String[] resultArray = new String[result.size()];

	for (int i = 0; i < resultArray.length; i++) {

	    resultArray[i] = result.get(i);

	}

	return resultArray;

    }

    /**
     * Sets this StringForge to the given StringForge
     * 
     * @param strFrg
     */
    public void to(StringForge strFrg) {

	this.clear();

	for (int k = 0; k < strFrg.size(); k++) {

	    this.append(strFrg.get(k));

	}

    }

    /**
     * Returns true if the given StringForge's indexes are equal to this one's.
     * 
     * @param strFrg
     * @param ignoreCase
     * @return
     */
    public boolean equals(StringForge strFrg, boolean ignoreCase) {

	boolean isEqual = true;

	if (!(this.size() == strFrg.size())) {

	    return false;

	}

	for (int k = 0; k < this.size(); k++) {

	    if (ignoreCase) {

		if (!this.get(k).equalsIgnoreCase(strFrg.get(k))) {

		    isEqual = false;

		}

	    } else {

		if (!this.get(k).equals(strFrg.get(k))) {

		    isEqual = false;

		}

	    }

	}

	return isEqual;

    }

}