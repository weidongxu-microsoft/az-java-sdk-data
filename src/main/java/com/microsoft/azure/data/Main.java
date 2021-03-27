package com.microsoft.azure.data;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.logging.ClientLogger;
import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

public class Main {

    private static final ClientLogger logger = new ClientLogger(Main.class);

    public static void main(String args[]) {
        runVault();

//        runStorage();
    }

    private static void runVault() {
        SecretClient secretClient = new SecretClientBuilder()
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
                .vaultUrl("https://kv6weidxu.vault.azure.net/")
                .credential(new ManagedIdentityCredentialBuilder().build())
                .buildClient();
        KeyVaultSecret keyVaultSecret = secretClient.getSecret("noop");
        System.out.println(keyVaultSecret.getName());
    }

    private static void runStorage() {
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .endpoint("https://sa2weidxu.blob.core.windows.net")
                .containerName("container1")
                .credential(new ManagedIdentityCredentialBuilder().build())
                .buildClient();

        long count = containerClient.listBlobs().stream().count();
        logger.info("Number of blobs: {}", count);
    }
}
