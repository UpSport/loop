package upsport.loop.rest;

import org.testng.annotations.Test;

import upsport.loop.rest.Main;

public class MainNGTest {

    Main sut = new Main(true);

    @Test
    public void givenNullCallToMainShouldStartServer() throws Exception {
        sut.startServer(null);
    }

}
