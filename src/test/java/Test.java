import fr.nathanael2611.eazyupdater.EazyUpdater;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        try {
            EazyUpdater updater = new EazyUpdater("http://kyrgon.fr/nathanael2611/n-updater/test/", new File("/c:"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
