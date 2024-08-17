package lords.logic.green.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lords.logic.green.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class AuthResponse {
  private User user;
  private String token;
}
