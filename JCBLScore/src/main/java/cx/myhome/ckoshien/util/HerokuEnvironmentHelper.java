package cx.myhome.ckoshien.util;

import java.net.URI;
import java.net.URISyntaxException;
public class HerokuEnvironmentHelper {

    private static HerokuEnvironmentHelper instance;

	public static HerokuEnvironmentHelper getInstance()
			throws URISyntaxException {
		if (instance == null) {
			instance = new HerokuEnvironmentHelper();
		}
		return instance;
	}

	private URI DB_URI;
	private static final String DB_URI_ENV_NAME = "CLEARDB_DATABASE_URL";

	private HerokuEnvironmentHelper() throws URISyntaxException {
		DB_URI = new URI(System.getenv(DB_URI_ENV_NAME));
	}

	public String getUrl() {
		System.out.println("jdbc:mysql://" + DB_URI.getHost() + DB_URI.getPath());
		return "jdbc:mysql://" + DB_URI.getHost() + DB_URI.getPath();
	}

	public String getUsername() {
		System.out.println(DB_URI.getUserInfo().split(":")[0]);
		return DB_URI.getUserInfo().split(":")[0];
	}

	public String getPassword() {
		System.out.println(DB_URI.getUserInfo().split(":")[1]);
		return DB_URI.getUserInfo().split(":")[1];
	}

}