package lords.logic.green.rest;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import lords.logic.green.model.exception.BadRequestException;
import lords.logic.green.model.exception.ForbiddenException;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.rest.dto.ExceptionDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

  @ExceptionHandler(
      value = {BadRequestException.class, DuplicateKeyException.class, JwtException.class})
  ResponseEntity<ExceptionDto> handleBadRequest(Exception e) {
    ExceptionDto restException =
        ExceptionDto.builder()
            .type(BadRequestException.class.getSimpleName())
            .message(e.getMessage())
            .build();
    return new ResponseEntity<>(restException, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(
      value = {
        AccessDeniedException.class,
        ForbiddenException.class,
        BadCredentialsException.class,
        AuthenticationException.class
      })
  ResponseEntity<ExceptionDto> handleForbidden(Exception e) {
    log.info(e.getClass().getSimpleName(), e);
    ExceptionDto restException =
        ExceptionDto.builder()
            .type(ForbiddenException.class.getSimpleName())
            .message(e.getMessage())
            .build();
    return new ResponseEntity<>(restException, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(value = {NotFoundException.class, UsernameNotFoundException.class})
  ResponseEntity<ExceptionDto> handleNotFound(Exception e) {
    log.info(e.getClass().getSimpleName(), e);
    ExceptionDto restException =
        ExceptionDto.builder()
            .type(NotFoundException.class.getSimpleName())
            .message(e.getMessage())
            .build();
    return new ResponseEntity<>(restException, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {Exception.class, SQLException.class})
  ResponseEntity<ExceptionDto> handleDefault(Exception e) {
    log.error("Internal error", e);
    ExceptionDto restException =
        ExceptionDto.builder()
            .type(HttpServerErrorException.InternalServerError.class.getSimpleName())
            .message(e.getMessage())
            .build();
    return new ResponseEntity<>(restException, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
