package com.extrabite.scheduler;

import com.extrabite.repository.BlacklistTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TokenCleanupScheduler {

    private final BlacklistTokenRepository blacklistTokenRepository;

    // 🕐 Run every hour
    @Scheduled(cron = "0 0 * * * ?") // Every hour
    public void cleanExpiredTokens() {
        System.out.println("🧹 Cleaning expired tokens...");
        blacklistTokenRepository.deleteAllExpiredSince(Instant.now());
    }
}