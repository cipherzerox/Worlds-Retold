package xenoscape.worldsretold.defaultmod.init;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import xenoscape.worldsretold.WorldsRetold;

public class ProgressiveWorldGenData extends WorldSavedData {

    private static final String IDENTIFIER = WorldsRetold.MODID + "_ProgressiveWorldGen";

    private boolean hasObtainedDiamond = false;

    public ProgressiveWorldGenData()
    {
        this(IDENTIFIER);
    }

    public ProgressiveWorldGenData(String parIdentifier)
    {
        super(parIdentifier);
        markDirty();
    }

    public static ProgressiveWorldGenData get(World world)
    {
        MapStorage storage = world.getMapStorage();
        ProgressiveWorldGenData data = (ProgressiveWorldGenData) storage.getOrLoadData(ProgressiveWorldGenData.class, IDENTIFIER);
        if (data == null)
        {
            System.out.println("Creating an identifier for progressive world gen.");
            data = new ProgressiveWorldGenData();
            world.setData(IDENTIFIER, data);
        }
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        System.out.println("Recognized WorldSavedData");
        hasObtainedDiamond = nbt.getBoolean("hasObtainedDiamond");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        System.out.println("Writing to WorldSavedData");
        nbt.setBoolean("hasObtainedDiamond", hasObtainedDiamond);
        return nbt;
    }

    public boolean getObtainedDiamond()
    {
    return hasObtainedDiamond;
    }

    public void setObtainedDiamond(boolean parHasObtainedDiamond)
    {
        if (!hasObtainedDiamond) {
            System.out.println("Activating progressive world gen.");
            hasObtainedDiamond = true;
            markDirty();
        }
    }
}
