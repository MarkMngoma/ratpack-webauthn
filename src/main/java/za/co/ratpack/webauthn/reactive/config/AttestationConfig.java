package za.co.ratpack.webauthn.reactive.config;

import lombok.Getter;

/**
 * @author markmngoma
 * @created at 23:07 on 27/12/2024
 */
@Getter
public class AttestationConfig {
  private String secret;
  private String issuer;
  private long expirationTime;
}
