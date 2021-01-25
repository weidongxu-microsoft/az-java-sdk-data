package com.microsoft.azure.data;

import com.azure.core.util.logging.ClientLogger;
import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

public class Main {

    private static final ClientLogger logger = new ClientLogger(Main.class);

    public static void main(String args[]) {

        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .endpoint("https://sa2weidxu.blob.core.windows.net")
                .containerName("container1")
                .credential(new ManagedIdentityCredentialBuilder().build())
                .buildClient();

        long count = containerClient.listBlobs().stream().count();
        logger.info("Number of blobs: {}", count);
    }
}
