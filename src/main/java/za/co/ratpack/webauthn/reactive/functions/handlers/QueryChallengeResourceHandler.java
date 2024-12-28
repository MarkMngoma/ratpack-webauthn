package za.co.ratpack.webauthn.reactive.functions.handlers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;
import za.co.ratpack.webauthn.reactive.config.AttestationConfig;
import za.co.ratpack.webauthn.reactive.functions.helpers.HttpContentHelper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author markmngoma
 * @created at 22:54 on 27/12/2024
 */
public class QueryChallengeResourceHandler implements Handler {

  private final HttpContentHelper httpContentHelper;
  private final ObjectMapper objectMapper;
  private final AttestationConfig attestationConfig;

  @Inject
  public QueryChallengeResourceHandler(HttpContentHelper httpContentHelper, ObjectMapper objectMapper, AttestationConfig attestationConfig) {
    this.httpContentHelper = httpContentHelper;
    this.objectMapper = objectMapper;
    this.attestationConfig = attestationConfig;
  }

  @Override
  public void handle(Context context) throws Exception {
    var rpOrigin = context.getRequest().getHeaders().asMultiValueMap().getOrDefault("Origin", "none");
    Promise.value(Algorithm.HMAC256(attestationConfig.getSecret()))
      .map(algorithm -> JWT.create()
        .withAudience(rpOrigin)
        .withIssuer(attestationConfig.getIssuer())
        .withExpiresAt(Instant.now().plus(attestationConfig.getExpirationTime(), ChronoUnit.MILLIS))
        .sign(algorithm)
      )
      .map(challengeToken -> objectMapper.createObjectNode().put("challengeToken", challengeToken))
      .then(response -> this.httpContentHelper.renderJson(context, response, Status.OK));
  }
}
