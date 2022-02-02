package anticope.tanuki.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.network.ClientPlayerEntity;

import anticope.tanuki.events.PushOutOfBlockEvent;
import meteordevelopment.meteorclient.MeteorClient;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "pushOutOfBlocks", at = @At("HEAD"), cancellable = true)
    private void onPushOutOfBlocks(double x, double d, CallbackInfo info) {
        PushOutOfBlockEvent event = PushOutOfBlockEvent.get(x, d);
        MeteorClient.EVENT_BUS.post(event);
        if(event.isCancelled()) info.cancel();
    }
}
