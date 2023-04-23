package com.reporter.client.model;

import com.itextpdf.styledxmlparser.resolver.resource.IResourceRetriever;

import java.io.*;
import java.net.URL;

public class IResourceRetrieverImpl implements PdfResourceRetriever {
    @Override
    public InputStream getInputStreamByUrl(URL url) throws IOException {
        String path = url.getPath();
        if (path.startsWith("/"))
            path = path.substring(1);

        return getClass().getClassLoader().getResourceAsStream(path);
    }

    @Override
    public byte[] getByteArrayByUrl(URL url) throws IOException {
        return getInputStreamByUrl(url).readAllBytes();
    }
}
