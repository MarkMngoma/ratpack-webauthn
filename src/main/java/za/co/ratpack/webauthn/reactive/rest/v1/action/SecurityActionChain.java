package za.co.ratpack.webauthn.reactive.rest.v1.action;

import ratpack.func.Action;
import ratpack.handling.Chain;
import za.co.ratpack.webauthn.reactive.functions.handlers.QueryChallengeResourceHandler;
import za.co.ratpack.webauthn.reactive.functions.handlers.WriteAuthenticationResourceHandler;
import za.co.ratpack.webauthn.reactive.functions.handlers.WriteRegistrationResourceHandler;

/**
 * @author markmngoma
 * @created at 22:47 on 27/12/2024
 */
public class SecurityActionChain implements Action<Chain> {
  @Override
  public void execute(Chain chain) throws Exception {
    chain
      .get("SecureResourceChallenge", QueryChallengeResourceHandler.class)
      .post("WriteRegistrationResource", WriteRegistrationResourceHandler.class)
      .put("WriteAuthenticationResource", WriteAuthenticationResourceHandler.class);
  }
}
