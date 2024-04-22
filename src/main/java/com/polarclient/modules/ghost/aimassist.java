package com.polarclient.modules.ghost;

import com.polarclient.modules.Module;
import com.polarclient.modules.Modules;
import com.polarclient.modules.blatant.tickbase;
import com.polarclient.modules.setting.Setting;
import com.polarclient.modules.setting.settingType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY;

public class aimassist extends Module {
    public static String name = "Aim Assist";
    static Minecraft mc = Minecraft.getMinecraft();
    static EntityPlayerSP player = mc.thePlayer;
    static WorldClient world = mc.theWorld;
    static float range = 3.5f;
    static int fov = 50;
    static int speed = 30;
    protected static boolean toggled = false;
    private Setting speedSetting = new Setting();
    public aimassist(){
        setName("Aim Assist");
        speedSetting.setType(settingType.Slider);
        speedSetting.name = "Speed";
        speedSetting.setMaxValue(100);
        speedSetting.setMinValue(0);
        speedSetting.setValue(this.speed);
        settingList.add(speedSetting);
    }
    @Override
    public void onEnable(){
        System.out.println(("Enable"));
        toggled = true;
    }
    @Override
    public void onDisable(){
        System.out.println(("Disable"));
        toggled = false;
    }
    static boolean stopInEntity = true;
    public static Entity getEnemy() {
        Entity entity = null;
        //gest the closest entity in the fov and the specified range
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityLivingBase) {
                EntityLivingBase e = (EntityLivingBase) o;
                if (e != mc.thePlayer && e.getDistanceToEntity(mc.thePlayer) <= range) {
                    float[] angle = getAngle(e);
                    float yaw = angle[0];
                    float pitch = angle[1];
                    float yawDifference = Math.abs(mc.thePlayer.rotationYaw - yaw);
                    float pitchDifference = Math.abs(mc.thePlayer.rotationPitch - pitch);
                    int lastfov = fov;
                    if (System.currentTimeMillis() - tickbase.lastTime > 0 &&
                            System.currentTimeMillis() - tickbase.lastTime < 350) {
                        fov = 360;
                    } else {
                        fov = lastfov;
                    }
                    if (yawDifference <= (float) fov && pitchDifference <= (float) fov) {
                        if (entity == null) {
                            entity = e;
                        } else if (e.getDistanceToEntity(mc.thePlayer) < entity.getDistanceToEntity(mc.thePlayer)) {
                            entity = e;
                        }
                    }
                }
            }
        }
        return entity;
    }

    @SubscribeEvent
    public void OnTick(TickEvent.RenderTickEvent ev){
        if (Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        if(Minecraft.getMinecraft().theWorld == null){
            return;
        }
        if (toggled){
            this.speed = this.speedSetting.getValue();
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            WorldClient world = Minecraft.getMinecraft().theWorld;
            Entity entity = getEnemy();
            if (entity != null ) {
                if(stopInEntity){
                    if(mc.objectMouseOver.typeOfHit == ENTITY) {
                        return;
                    }
                }
                float[] angle = getAngle(entity);
                float yaw = angle[0];
                float pitch = angle[1];
                    float yawDifference = Math.abs(mc.thePlayer.rotationYaw - yaw);
                    float pitchDifference = Math.abs(mc.thePlayer.rotationPitch - pitch);
                    float rotaionValue = (mc.thePlayer.rotationYaw) + (yaw - mc.thePlayer.rotationYaw);
                    float rotationPitch = (mc.thePlayer.rotationPitch) + (pitch - mc.thePlayer.rotationPitch);
                    //make it smooth

                boolean shouldInstante = tickbase.afterTickbase;
                if (System.currentTimeMillis() - tickbase.lastTime > 0 &&
                        System.currentTimeMillis() - tickbase.lastTime < 300) {
                    mc.thePlayer.rotationYaw = mc.thePlayer.rotationYaw + (yaw - mc.thePlayer.rotationYaw) ;
                    mc.thePlayer.rotationPitch = mc.thePlayer.rotationPitch + (pitch - mc.thePlayer.rotationPitch) ;
                } else {
                    speed = speedSetting.getValue() - 100;
                    mc.thePlayer.rotationYaw = mc.thePlayer.rotationYaw + (yaw - mc.thePlayer.rotationYaw) / speed;
                    mc.thePlayer.rotationPitch = mc.thePlayer.rotationPitch + (pitch - mc.thePlayer.rotationPitch) / (speed * 10);
                }
            }
        }
    }
    public static float[] getAngle(Entity e) {
        double x = e.posX - mc.thePlayer.posX;
        double y = e.posY + (double) e.getEyeHeight() - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight());
        double z = e.posZ - mc.thePlayer.posZ;
        double d = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float) (-(Math.atan2(y, d) * 180.0 / Math.PI));
        float rnd = (float) (1 + (Math.random() * (5- 1)));
        // make it smoother
        yaw = mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw) + rnd;
        pitch = mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch) + rnd;


        return new float[] { yaw, pitch };
    }
}
