package fr.nathanael2611.eazyupdater.data;

import fr.nathanael2611.eazyupdater.EazyUpdater;

import java.io.File;

public class Updater {

    private EazyUpdater eazyUpdater;

    public Updater(EazyUpdater eazyUpdater) {
        this.eazyUpdater = eazyUpdater;
    }

    public void update() {
        this.eazyUpdater.getProfile().syncedDirectories.forEach(d -> {
            d.removeSurplus(new File("test"));
        });

        this.eazyUpdater.getProfile().filesToDownload.forEach(f -> {
            f.download(new File("test"));
        });
    }

}
