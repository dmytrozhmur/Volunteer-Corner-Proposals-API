package ua.nure.proposalservice.component.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
public class UnsuitableRoleHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        Map<String, String> accessDeniedResponse = Collections.singletonMap(
                accessDeniedException.getClass().getSimpleName(),
                accessDeniedException.getMessage()
        );
        ServletOutputStream responseStream = response.getOutputStream();

        mapper.writeValue(responseStream, accessDeniedResponse);
    }
}
