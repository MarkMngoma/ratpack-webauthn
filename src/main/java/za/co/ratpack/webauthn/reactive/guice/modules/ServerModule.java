package za.co.ratpack.webauthn.reactive.guice.modules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.webauthn4j.converter.jackson.WebAuthnCBORModule;
import com.webauthn4j.converter.jackson.WebAuthnJSONModule;
import com.webauthn4j.converter.util.ObjectConverter;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.MDC;
import ratpack.error.ClientErrorHandler;
import ratpack.error.ServerErrorHandler;
import ratpack.handling.RequestId;
import ratpack.logging.MDCInterceptor;
import za.co.ratpack.webauthn.reactive.domain.flyway.FlywayMigratorService;
import za.co.ratpack.webauthn.reactive.functions.handlers.GlobalErrorHandler;
import za.co.ratpack.webauthn.reactive.rest.v1.action.SecurityActionChain;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * @author markmngoma
 * @created at 22:43 on 27/12/2024
 */
public class ServerModule extends AbstractModule {

  private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  @Override
  protected void configure() {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));

    bind(ClientErrorHandler.class).to(GlobalErrorHandler.class);
    bind(ServerErrorHandler.class).to(GlobalErrorHandler.class);

    bind(FlywayMigratorService.class);
    bind(SecurityActionChain.class);
  }

  @Provides
  @Singleton
  public MDCInterceptor getMDCInterceptor() {
    return MDCInterceptor.withInit(execution -> execution.maybeGet(RequestId.class)
      .ifPresent(requestId -> MDC.put("requestId", requestId.toString())));
  }

  @Provides
  @Singleton
  public Validator beanValidator() {
    return Validation.byDefaultProvider()
      .configure()
      .messageInterpolator(new ParameterMessageInterpolator())
      .buildValidatorFactory()
      .getValidator();
  }

  @Provides
  @Singleton
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }

  @Provides
  @Singleton
  public ObjectMapper objectMapper() {
    var objectConverter = new ObjectConverter();
    return new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .registerModule(new WebAuthnCBORModule(objectConverter))
      .registerModule(new WebAuthnJSONModule(objectConverter))
      .registerModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))))
      .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
      .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
      .setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT))
      .setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }
}
