package fr.nathanael2611.eazyupdater.data;

import fr.nathanael2611.eazyupdater.Helpers;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;

public class DownloadEntry {

    private String fileName;
    private String downloadUrl;
    private String relativePath;
    private String md5;
    private int size;

    public DownloadEntry(
            String fileName,
            String downloadUrl,
            String relativePath,
            String md5,
            int size
    ) {
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
        this.relativePath = relativePath;
        this.md5 = md5;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getMd5() {
        return md5;
    }

    public int getSize() {
        return size;
    }

    public void download(File baseDir) {
        baseDir.mkdir();
        new File(baseDir, getRelativePath()).mkdir();

        File file = new File(new File(baseDir, getRelativePath()), getFileName());

        if(file.exists()) {
            String md5 = Helpers.md5File(file);
            if(!md5.equals(getMd5())){
                try {
                    FileUtils.forceDelete(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (BufferedInputStream in = new BufferedInputStream(new URL(getDownloadUrl()).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
        }
    }
}
