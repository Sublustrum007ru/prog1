package controller;

import java.io.IOException;
import java.util.List;

public interface Operation {
    List<SiteSettings> readFile(String path) throws IOException;

    void writeFile(String path, SiteSettings siteSettings);
} 
