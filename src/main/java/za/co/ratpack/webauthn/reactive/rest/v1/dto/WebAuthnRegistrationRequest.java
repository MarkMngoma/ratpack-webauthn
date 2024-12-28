package za.co.ratpack.webauthn.reactive.rest.v1.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author markmngoma
 * @created at 22:51 on 27/12/2024
 */
@Data
public class WebAuthnRegistrationRequest implements Serializable {
  @Serial
  private static final long serialVersionUID = 7307201132771085143L;
  private String id;
  private String attestationObject;
  private String clientDataJSON;
  private boolean verifyUser;
  private String challenge;
  private String hostname;
  private int port;
}
