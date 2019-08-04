package com.itsector.backoffice.entrypoint;

import com.itsector.backoffice.configuration.JwtTokenUtil;
import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.entrypoint.vo.request.LoginRequestVo;
import com.itsector.backoffice.usecase.users.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private Login login;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, Login login) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.login = login;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestVo request) throws Exception {
        User user = login.execute(request.getUserName(), request.getPassword());
        authenticate(request.getUserName(), request.getPassword());

        final String token = jwtTokenUtil.generateToken();
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
