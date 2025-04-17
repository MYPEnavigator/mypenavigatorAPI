package com.example.mypenavigatorapi.auth.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class FirebaseInitializer {

    @PostConstruct
    public void init() throws IOException {
        String serviceAccountJson = "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"mype-navigator\",\n" +
                "  \"private_key_id\": \"bc593041fb4651025ae434309c3124af9844cdc4\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQChep5lI+XUwG+O\\nOg5zHN6yEX7bIoUP2d/SoUlHES7vYq7qP81G/nqUpznKXu7fCABGCDGSf8QaiV/r\\npTRAUK3rpk9D8AqQ4hgq4bkOVKZAS0Mbwasi+7msREdPyqR0zewBDtd7g29UeFNk\\nMVlLrUA444HPJhlpDXQVck8Jj9p8Aqy/66Pj75Qbri25ytCGVmAwETgi66E1x+KB\\njHFOs9eQdHi0LzeUacoZkpaFO64xLMJQuth7t5AjDlQ+YcS+9LE/Cg2E6u6XKNAL\\nqG/ORvmBHt72zkbmGZzWcAm7/dD0bl5DjEcJlv56io06ApH/TLVJ2Y1ebr227P9l\\nk5whsRKFAgMBAAECggEAApqAKPszPPyNf+WybtgzES9qum+oy6iuLQHCAiX5U5o/\\nEQW00oioiZkibr+Cyr1HPEA5d/ypeOnbzk6UVFbvEVRveDxLsbc3f29/i2Cn6o1c\\nGGIFV2cA51GVgPUwaBxO1w7tQGK0d5BWFd1qBjT8CvH3svmQ1NEG9pAogCvqo6Lk\\nnNdvpt+8IK5HpKg4FvFvY2EX6UHZPpDC0xNarH//Pj/3sAv1zPzeE3tN94wrxJef\\np0hEdMil+njIeBIcYRuccDYBs/12LDpfVeNSIfrVvvQGXx1/Rsd2SMZIHm9Rk7tz\\nN9qNMaNURUrKjd9T8Se8+7ey4QuAOr1bZ50xSukT6QKBgQDheEo6qDx4lSpaLkG4\\nrl2SJMYww9lmAE0L8xfa7QItcVEMhZyVfori0p1j8J8wtrk3JcQ5mMzPejop77Ca\\nviAAp0CCQkVeKuzuIbu9acU6WJ0YUgjztjH1nMB5UEtRzhTzfcI3KAoLBDc6SX3C\\nSxBb5inklILmU+PDD1zj0CIxeQKBgQC3WCRQXHh25U2ivez+/tCRRy0DYsN3neYr\\nHRy24b5TN+JZuq5WghpLN0NX/9hdXI5/bZuylURIStXZ7oOB3zSSNM6XjnzGJlnP\\n8lFhoUoT6K/HKrt3Y4Y+Rma//MZMvocfR12JFOtgKnddALUFRnECrX/oYBmvnqOX\\nLDdMwoOSbQKBgFdwlxj4dbst3ibrGYHN3Wv35wSyb3UnWLnxpKa6k8Wb1JrOllND\\nqAkfNPq1Lodt1ZaVhuvvXj54YGYzFoJsiWy3cEUT/mLussQHQQ/MHTjrdMEhz2b0\\nmZtvFxX1FcC781QayV8qn/p1wBVSuvrGsalyMXz8O2en0QSsyJFw/lHBAoGAbKYy\\nFuFFjgfC4x8z88SjaoRIW4pKlXcf8Aq3enT6aaxjWZM0PxWoaUxdfyehW2o/E6qr\\nqr+kEEcwxY3/ppXUPJRInS2WKYKOlJlfusBu+q9Yg2mCYHS9F+h2eW1dQhtiTZno\\nBxU7iuh/hcCmhXLCA7k3ZEiW0Cjtfud3+egHOl0CgYAxXAEIcdfZZuJG/Ad3lnEw\\nZ9NebIDuyTqe610oKdjfNs8lVEP0W80Qb+MnpF7XgEYa9L5Tr8kTQvdqXi+G7qsa\\nXQQYreUK/yY/2tSDlmU2MNtRP+OdaFnmqtBGOwKJGO4T+TWo0CUZJfzqdXnd9Y6s\\nsoOLnlcJmGbsI9rcz1WR1A==\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"firebase-adminsdk-4s3k9@mype-navigator.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"110059931287544550351\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-4s3k9%40mype-navigator.iam.gserviceaccount.com\",\n" +
                "  \"universe_domain\": \"googleapis.com\"\n" +
                "}";

        InputStream serviceAccount = new ByteArrayInputStream(serviceAccountJson.getBytes(StandardCharsets.UTF_8));

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        System.out.println("Firebase App initialized successfully");
    }
}