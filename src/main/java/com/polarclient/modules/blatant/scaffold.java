package com.polarclient.modules.blatant;

import com.google.common.eventbus.Subscribe;
import com.polarclient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import org.lwjgl.Sys;
import utils.Mouse;

import javax.vecmath.Vector3d;
import java.io.IOException;
import java.util.Random;

public class scaffold extends Module {
    private static final float ROTATION_SPEED = 20;
    static Minecraft mc = Minecraft.getMinecraft();
    static int strafeMode = 0;
    static KeyBinding LastForwardKey = mc.gameSettings.keyBindForward;
    static KeyBinding LastBackKey = mc.gameSettings.keyBindBack;
    static int keyLeft = mc.gameSettings.keyBindLeft.getKeyCode();
    static int keyRight = mc.gameSettings.keyBindLeft.getKeyCode();
    static int newPitch = 77;
    static int newYaw = 0;
    private static boolean toggled;
    int tickneeded = 5;
    public scaffold() {
        setName("Scaffold");
        setMode("Polar");
    }

    public static int getDigitsAfterDecimal(double number) {
        // Convert the number to a string
        String numStr = Double.toString(number);
        // Find the position of the decimal point
        int decimalIndex = numStr.indexOf('.');
        // If there is no decimal point, return 0
        if (decimalIndex == -1) {
            return 0;
        }
        // Extract the substring after the decimal point
        String digitsAfterDecimal = numStr.substring(decimalIndex + 1);
        // Return the result
        return Integer.parseInt(digitsAfterDecimal.substring(0, 3));
    }

    @Override
    public void onEnable() {
        if (toggled) {
            onDisable();
            return;
        }
        //mc.gameSettings.keyBindForward = LastBackKey;
        //mc.gameSettings.keyBindBack = LastForwardKey;
        toggled = true;
        tickneeded = 5;
        float x = getDigitsAfterDecimal(mc.thePlayer.getPositionVector().xCoord);
        float z = getDigitsAfterDecimal(mc.thePlayer.getPositionVector().zCoord);
        if (x < 500 && z < 500) {
            System.out.println("case 1");
            newYaw = -45;
        } else if (x < 500 && z > 500) {
            System.out.println("case 2");
            newYaw = -134;
        } else if (x > 500 && z > 500) {
            System.out.println("case 3");
            newYaw = 134;
        } else {
            System.out.println("case 4");
            newYaw = 45;
        }

        newPitch = 77;
        //mc.thePlayer.rotationYaw = newYaw;
        //mc.thePlayer.rotationPitch = newPitch;

        //this.sendQueue.addToSendQueue(new C0CPacketInput(this.moveStrafing, this.moveForward, this.movementInput.jump, this.movementInput.sneak));
    }

    @Override
    public void onDisable() {
        //mc.gameSettings.keyBindForward = LastForwardKey;
        //mc.gameSettings.keyBindBack = LastBackKey;
        toggled = false;
        tickneeded = 5;
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent ev) {
        if (!toggled) return;
        if (mc.thePlayer == null) return;
        if (mc.theWorld == null) return;
        EntityPlayerSP player = mc.thePlayer;
        player.setSprinting(false);
        float lastPitch = player.rotationPitch;
        float lastYaw = player.rotationYaw;
        boolean isRoationGood = true;
        if (Math.round(lastPitch) != newPitch) {
            if (newPitch > Math.round(lastPitch)) {
                player.setPositionAndRotation2(
                        mc.thePlayer.getPositionVector().xCoord,
                        mc.thePlayer.getPositionVector().yCoord,
                        mc.thePlayer.getPositionVector().zCoord,
                        mc.thePlayer.rotationYaw,
                        mc.thePlayer.rotationPitch += 1,
                        1,
                        false
                );
                isRoationGood = false;
            } else {
                player.setPositionAndRotation2(
                        mc.thePlayer.getPositionVector().xCoord,
                        mc.thePlayer.getPositionVector().yCoord,
                        mc.thePlayer.getPositionVector().zCoord,
                        mc.thePlayer.rotationYaw,
                        mc.thePlayer.rotationPitch -= 1,
                        1,
                        false
                );
                isRoationGood = false;
            }
        }
        if (Math.round(lastYaw) != newYaw) {
            if (newYaw > lastYaw) {
                player.rotationYaw += 1;
                isRoationGood = false;
            } else {
                player.rotationYaw -= 1;
                isRoationGood = false;
            }
        }
        if(isRoationGood){

            Mouse.Rclick();
        }
        //player.rotationPitch = -90.0F;
        //player.rotationYaw += ROTATION_SPEED;

        Random rand = new Random();
        strafeMode = rand.nextInt(2);
        if (false) {
            KeyBinding.setKeyBindState(keyLeft, false);
            KeyBinding.setKeyBindState(keyRight, false);
            System.out.println(strafeMode);
            switch (strafeMode) {
                case 1: {
                    KeyBinding.setKeyBindState(keyLeft, true);
                    KeyBinding.setKeyBindState(keyRight, false);
                }
                case 2: {

                    KeyBinding.setKeyBindState(keyLeft, false);
                    KeyBinding.setKeyBindState(keyRight, true);

                }

            }

        }
        player.rotationPitch = lastPitch;
    }

}
