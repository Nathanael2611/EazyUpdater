package fr.nathanael2611.eazyupdater;

import fr.nathanael2611.eazyupdater.data.UpdateProfile;

import java.io.File;
import java.io.IOException;

public class EazyUpdater {

    private String serverUrl;
    private File directory;
    private UpdateProfile profile;

    public EazyUpdater(String serverUrl, File directory) throws IOException {
        this.serverUrl = serverUrl;
        if(directory.isFile()) {
            throw new IOException("The specified directory is a file");
        }
        this.directory = directory;
        this.profile = new UpdateProfile(serverUrl, directory);

    }

    public UpdateProfile getProfile() {
        return profile;
    }
}
