package dk.andreasgabel.talentapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LatestTagFilter extends OncePerRequestFilter {

    @Value("${IMAGE_TAG:latest}")
    private String imageTag;

    private static final Set<String> ALLOWED_PATHS = Set.of(
            "/oops.html", "/api/image-tag", "/actuator/health", "/actuator/info"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        if (!"latest".equals(imageTag)) {
            chain.doFilter(request, response);
            return;
        }

        String path = request.getRequestURI();

        // Allow static resources for oops page and the tag endpoint
        if (ALLOWED_PATHS.contains(path) || path.startsWith("/css/") || path.startsWith("/js/")
                || path.contains("fonts.googleapis") || path.contains("fonts.gstatic")) {
            chain.doFilter(request, response);
            return;
        }

        // Redirect everything else to oops page
        response.sendRedirect("/oops.html");
    }
}
