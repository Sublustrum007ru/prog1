package controller;

import java.io.IOException;

public interface Operation {
    SiteSettings readFile(String path) throws IOException;

    void writeFile(String path, SiteSettings siteSettings);
} 
