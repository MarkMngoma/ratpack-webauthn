package za.co.ratpack.webauthn.reactive;

/**
 * @author markmngoma
 * @created at 22:07 on 27/12/2024
 */
public class HttpServer {

  public static final String SERVER_ENVIRONMENT = "RATPACK_ENVIRONMENT";

  public static void main(String[] args) throws Exception {
    ServerCommand serverCommand = new ServerCommand();
    serverCommand.run();
  }
}
