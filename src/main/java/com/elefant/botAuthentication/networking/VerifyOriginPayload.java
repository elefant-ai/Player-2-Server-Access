package com.elefant.botAuthentication.networking;

import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.listener.ServerCommonPacketListener;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;

public record VerifyOriginPayload(String input) implements Packet<ServerLoginPacketListener> {
    public static final PacketCodec<PacketByteBuf, VerifyOriginPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, VerifyOriginPayload::input, VerifyOriginPayload::new);


    @Override
    public PacketType<VerifyOriginPayload> getPacketType() {
        return TYPE;
    }

    public static final PacketType<VerifyOriginPayload> TYPE = new PacketType<>(NetworkSide.SERVERBOUND, ElefantNetworkingConstants.VERIFY_ORIGIN);

    @Override
    public void apply(ServerLoginPacketListener listener) {

    }

}
