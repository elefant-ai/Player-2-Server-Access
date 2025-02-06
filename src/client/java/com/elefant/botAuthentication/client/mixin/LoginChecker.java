package com.elefant.botAuthentication.client.mixin;


import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.minecraft.network.NetworkPhase;
import net.minecraft.network.NetworkState;
import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.CookiePackets;
import net.minecraft.network.packet.LoginPackets;
import net.minecraft.network.packet.c2s.common.CookieResponseC2SPacket;
import net.minecraft.network.packet.c2s.login.EnterConfigurationC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.state.LoginStates;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(LoginStates.class)
public class LoginChecker {

    @Mutable
    @Shadow @Final public static NetworkState<ServerLoginPacketListener> C2S;

    @Mutable
    @Shadow @Final public static NetworkState.Factory<ServerLoginPacketListener, PacketByteBuf> C2S_FACTORY;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void init(CallbackInfo ci) {
        System.out.println("HI I DID IT YAY");
        C2S_FACTORY = NetworkStateBuilder.c2s(
                NetworkPhase.LOGIN,
                builder -> builder.add(LoginPackets.HELLO_C2S, LoginHelloC2SPacket.CODEC)
                        .add(LoginPackets.KEY, LoginKeyC2SPacket.CODEC)
                        .add(LoginPackets.CUSTOM_QUERY_ANSWER, LoginQueryResponseC2SPacket.CODEC)
                        .add(LoginPackets.LOGIN_ACKNOWLEDGED, EnterConfigurationC2SPacket.CODEC)
                        .add(CookiePackets.COOKIE_RESPONSE, CookieResponseC2SPacket.CODEC)
                        .add(VerifyOriginPayload.TYPE, VerifyOriginPayload.CODEC)
        );
        C2S = C2S_FACTORY.bind(PacketByteBuf::new);

    }
}