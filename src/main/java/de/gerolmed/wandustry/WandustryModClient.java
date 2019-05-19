package de.gerolmed.wandustry;

import net.fabricmc.api.ClientModInitializer;

public class WandustryModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Containers.registerClient();
    }
}
