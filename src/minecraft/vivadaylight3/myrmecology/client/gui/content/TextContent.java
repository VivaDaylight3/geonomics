package vivadaylight3.myrmecology.client.gui.content;

public class TextContent {

    public String[] getContent() {

	return null;

    }

    protected String newBookPage(boolean newLines, String... strings) {

	String result = "";
	for (int k = 0; k < strings.length; k++) {

	    result += strings[k];

	    if (newLines) {

		result += getNewLine(strings[k]);

	    }

	}

	return result;

    }

    protected String getNewLine(String string) {

	double div = 0;
	double dec = 0;

	int length;

	if (string.length() > 22) {

	    div = string.length() / 22;
	    dec = div - Math.ceil(div);

	    length = (int) Math.ceil(22 * dec);

	} else {

	    length = string.length();

	}

	String result = "";

	int count = 23 - length;

	for (int k = 0; k < count; k++) {

	    result += " ";

	}

	return result;

    }

}
