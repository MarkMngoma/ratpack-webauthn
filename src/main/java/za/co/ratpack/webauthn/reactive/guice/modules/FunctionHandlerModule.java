package za.co.ratpack.webauthn.reactive.guice.modules;

import com.google.inject.AbstractModule;
import za.co.ratpack.webauthn.reactive.functions.handlers.LoggingMDCHandler;
import za.co.ratpack.webauthn.reactive.functions.handlers.QueryChallengeResourceHandler;
import za.co.ratpack.webauthn.reactive.functions.handlers.ServerResponseHandler;
import za.co.ratpack.webauthn.reactive.functions.handlers.ThrowableHandler;
import za.co.ratpack.webauthn.reactive.functions.handlers.WriteAuthenticationResourceHandler;
import za.co.ratpack.webauthn.reactive.functions.handlers.WriteRegistrationResourceHandler;

/**
 * @author markmngoma
 * @created at 22:42 on 27/12/2024
 */
public class FunctionHandlerModule extends AbstractModule {

  @Override
  protected void configure() {
    // Handlers
    bind(ServerResponseHandler.class);
    bind(LoggingMDCHandler.class);
    bind(ThrowableHandler.class);

    bind(QueryChallengeResourceHandler.class);
    bind(WriteAuthenticationResourceHandler.class);
    bind(WriteRegistrationResourceHandler.class);
  }
}
