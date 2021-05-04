package uz.pdp.lesson7.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.lesson7.entity.User;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null
                && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")){
            User user = (User)authentication.getPrincipal();
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }
}
