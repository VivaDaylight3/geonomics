package vivadaylight3.myrmecology.common.lib;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class Url {

    public static String openUrl(String par) throws IOException {

	URL url = new URL(par);
	java.io.InputStream is;
	is = url.openConnection().getInputStream();
	java.io.BufferedReader reader = new java.io.BufferedReader(
		new java.io.InputStreamReader(is));
	String buf = "";
	String line = null;

	while ((line = reader.readLine()) != null) {
	    buf += "\n" + line;
	}

	reader.close();
	return buf;

    }

    public static String getLatestVersion() throws IOException {

	String parameters = "http://login.minecraft.net/?user="
		+ URLEncoder.encode("samuel.tebbs@yahoo.com", "UTF-8")
		+ "&password=" + URLEncoder.encode("Pears33", "UTF-8")
		+ "&version=" + 13;
	String result = openUrl(parameters);

	return result;

    }

}
