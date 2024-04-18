package utils;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.BlockPos;

public class Mouse {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static void Rclick(){
        mc.thePlayer.swingItem();
        if (mc.objectMouseOver != null) {
            switch (mc.objectMouseOver.typeOfHit) {
                case ENTITY:
                    mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
                    break;
                case BLOCK:
                    BlockPos blockpos = mc.objectMouseOver.getBlockPos();
                    if (mc.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air) {
                        mc.playerController.clickBlock(blockpos, mc.objectMouseOver.sideHit);
                        break;
                    }
            }
        }
    }
    public static boolean AimingAsEntity(){
        if (mc.objectMouseOver != null) {
            switch (mc.objectMouseOver.typeOfHit) {
                case ENTITY:
                    return true;
            }
        }
        return false;
    }

}
