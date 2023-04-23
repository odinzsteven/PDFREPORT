package com.reporter.client.model;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class IResourceRetrieverImplTest {
    @Test
    public void name() throws Exception {
        IResourceRetrieverImpl loader = new IResourceRetrieverImpl();
        String content = new String(loader.getByteArrayByUrl(new URL("https://home/web/test.txt")));
        assertEquals(content, "loaded content");
    }
}