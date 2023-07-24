package shop.study.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() { //223p - 엔티티 공통 속성 공통화
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = "";

        if (authentication != null) {
            userId = authentication.getName();
        }

        return Optional.of(userId);
    }
}
