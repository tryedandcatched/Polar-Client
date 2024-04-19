package utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.BlockPos;

public class Mouse {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static void Lclick(){
        int key = mc.gameSettings.keyBindAttack.getKeyCode();
        KeyBinding.setKeyBindState(key, true);
        KeyBinding.onTick(key);
        if (AimingAtBlock()) {
            BlockPos p = mc.objectMouseOver.getBlockPos();

            if (p != null) {
                Block bl = mc.theWorld.getBlockState(p).getBlock();
                if (bl != Blocks.air && !(bl instanceof BlockLiquid)) {
                    int e = mc.gameSettings.keyBindAttack.getKeyCode();
                    KeyBinding.setKeyBindState(e, true);
                    KeyBinding.onTick(e);
                }
            }
        } else {
            KeyBinding.setKeyBindState(key, false);
        }
    }
    public static void Rclick(){
        int key = mc.gameSettings.keyBindUseItem.getKeyCode();
        if (AimingAtBlock()) {
            KeyBinding.setKeyBindState(key, true);
            KeyBinding.onTick(key);
            KeyBinding.setKeyBindState(key, false);
        }
    }
    public static boolean AimingAsEntity(){
        if (mc.objectMouseOver != null) {
            switch (mc.objectMouseOver.typeOfHit) {
                case ENTITY:
                    return true;
                case BLOCK:
                    return false;
            }
        }
        return false;
    }

    public static boolean AimingAtBlock(){
        if (mc.objectMouseOver != null) {
            switch (mc.objectMouseOver.typeOfHit) {
                case ENTITY:
                    return false;
                case BLOCK:
                    return true;
            }
        }
        return false;
    }

}
