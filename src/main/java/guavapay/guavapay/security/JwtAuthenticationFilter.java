package guavapay.guavapay.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import guavapay.guavapay.dto.LoginJwtTokenResponse;
import guavapay.guavapay.dto.UsersLogin;
import guavapay.guavapay.model.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import static guavapay.guavapay.security.JwtProperties.JWT_TOKEN_EXPIRATION_TIME;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res)  {
        try {
            UsersLogin loginDetails = objectMapper
                    .readValue(req.getInputStream(), UsersLogin.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDetails.getUsername(),
                            loginDetails.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        Users users = (Users) auth.getPrincipal();

        String token = JWT.create()
                .withSubject(users.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_TIME))
                .withIssuedAt(new Date())
                .withClaim("Description", "My Description")
                .withClaim("Company","My Company")
                .sign(Algorithm.HMAC512(JwtProperties.JWT_SECRET.getBytes()));


        String content = objectMapper.writeValueAsString(new LoginJwtTokenResponse(token,
                users.getUsername()));

        PrintWriter writer = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        writer.write(content);
        writer.flush();
    }




}
