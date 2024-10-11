/**
 * This Interceptor reads the Request Params and finds the according user (or throws an error)
 */

package ch.hackathon.backend.interceptors;

import ch.hackathon.backend.models.User;
import ch.hackathon.backend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String username = request.getHeader("X-authentik-username");
        String email = request.getHeader("X-authentik-email");
        String name = request.getHeader("X-authentik-name");

        if (username == null || email == null || name == null) {
            // headers not set, throw error
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Set the corret X-authentik headers!");
        }

        // find user
        User user = userService.findUserByMail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User not found, please create an account first!"));

        // set user attribute
        request.setAttribute("user", user);

        return true;
    }
}
