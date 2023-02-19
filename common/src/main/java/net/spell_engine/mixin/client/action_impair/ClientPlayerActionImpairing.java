package net.spell_engine.mixin.client.action_impair;

import net.minecraft.client.network.ClientPlayerEntity;
import net.spell_engine.api.effect.EntityActionsAllowed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerActionImpairing {
    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Input;tick(ZF)V", shift = At.Shift.AFTER))
    private void tickMovement_ModifyInput(CallbackInfo ci) {
        var clientPlayer = (ClientPlayerEntity)((Object)this);
        var actionsAllowed = ((EntityActionsAllowed.ControlledEntity) clientPlayer).actionImpairing();
        if (!actionsAllowed.canMove()) {
            clientPlayer.input.movementForward = 0;
            clientPlayer.input.movementSideways = 0;
        }
    }
}
