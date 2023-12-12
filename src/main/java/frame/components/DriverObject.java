package frame.components;

import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverObject {
    public RemoteWebDriver getRdriver() {
        return rdriver;
    }

    public void setRdriver(RemoteWebDriver rdriver) {
        this.rdriver = rdriver;
    }

    RemoteWebDriver rdriver;
}
