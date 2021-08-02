package uz.developer.communication_system.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.developer.communication_system.entity.SimCard;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {


    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
        && authentication.isAuthenticated()
        && !authentication.getPrincipal().equals("anonymousSimCard")){
            SimCard simCard = (SimCard) authentication.getPrincipal();
            return Optional.of(simCard.getId());
        }
        return Optional.empty();
    }
}
