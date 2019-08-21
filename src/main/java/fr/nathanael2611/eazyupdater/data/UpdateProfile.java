package fr.nathanael2611.eazyupdater.data;

import com.google.gson.reflect.TypeToken;
import fr.nathanael2611.eazyupdater.Helpers;
import fr.nathanael2611.eazyupdater.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UpdateProfile {

    private String serverUrl;
    private File directory;
    protected List<DownloadEntry> filesToDownload;
    protected List<SyncedDirectory> syncedDirectories;

    public UpdateProfile(String serverUrl, File directory) {
        this.serverUrl = serverUrl;
        this.directory = directory;

        try {
            Type listType = new TypeToken<List<DownloadEntry>>() {}.getType();
            filesToDownload = JsonUtils.getGson().fromJson(
                    Helpers.readJsonFromUrl(this.serverUrl + "index.php?action=listfiles&baselink=" + serverUrl),
                    listType
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        syncedDirectories = new ArrayList<>();

        filesToDownload.forEach(f -> {
            boolean isPathAlreadyContained = false;
            for(SyncedDirectory syncedDirectory : syncedDirectories){
                if(syncedDirectory.getRelativePath().equals(f.getRelativePath())) {
                    isPathAlreadyContained = true;
                }
            }
            if(!isPathAlreadyContained) syncedDirectories.add(new SyncedDirectory(
                    f.getRelativePath(),
                    new ArrayList<>()
            ));
        });

        syncedDirectories.forEach( d -> {
            filesToDownload.forEach(f -> {
                if(f.getRelativePath().equals(d.getRelativePath())){
                    d.getFilesMd5().add(f.getMd5());
                }
            });
        });

    }

}
