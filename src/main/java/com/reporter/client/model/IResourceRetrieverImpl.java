package com.reporter.client.model;

import com.itextpdf.styledxmlparser.resolver.resource.IResourceRetriever;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class IResourceRetrieverImpl implements IResourceRetriever {

    private final String workDir;

    public IResourceRetrieverImpl(String workDir) {
        this.workDir = workDir;
    }

    @Override
    public InputStream getInputStreamByUrl(URL url) throws IOException {
        return new ByteArrayInputStream(getByteArrayByUrl(url));
    }

    @Override
    public byte[] getByteArrayByUrl(URL url) throws IOException {
        String path = url.getPath();
        String file = path.substring(path.indexOf("/web/"));
        return Files.readAllBytes(Path.of(workDir, file));
    }
}
