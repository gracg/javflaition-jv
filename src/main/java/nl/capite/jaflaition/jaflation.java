package nl.capite.jaflaition;

import io.javalin.Javalin;
import nl.capite.jaflaition.helpers.BlsHelper;

import java.io.IOException;

public class jaflation {
    public static void main(String[] args) throws IOException {
        BlsHelper bls = new BlsHelper("cpi-series.xlsx");

        int port = System.getenv("jaflation-port")==null? 1913 : Integer.valueOf(System.getenv("jaflation-port"));
        Javalin app = Javalin.create(config -> {config.enableCorsForAllOrigins();}).start(port);
        app.get("/api/data",ctx -> {
            ctx.json(bls.analyze());
        });
    }
}
