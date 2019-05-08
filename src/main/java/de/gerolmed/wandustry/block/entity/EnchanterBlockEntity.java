package de.gerolmed.wandustry.block.entity;

import de.gerolmed.wandustry.BlockEntities;
import de.gerolmed.wandustry.Blocks;
import de.gerolmed.wandustry.enchanting.EnchantingManager;
import de.gerolmed.wandustry.enchanting.EnchantingRecipe;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EnchanterBlockEntity extends BasicBlockEntity implements BlockEntityClientSerializable, Tickable {

    private static final Vec3d[] MANA_OBTAIN_DIRECTIONS = new Vec3d[] {
            new Vec3d(1, 0, 0),
            new Vec3d(-1, 0, 0),
            new Vec3d(0, 0, 1),
            new Vec3d(0, 0, -1)
    };

    private ArrayList<ItemStack> itemStacks;

    private final int SLOT_COUNT = 5;
    private final int EXTRACTOR_DISTANCE = 3;

    private boolean ready = true;
    private int ticker = 0, tickerReset = 10;

    private static final Logger LOGGER = LogManager.getLogger(EnchanterBlockEntity.class);
    private EnchantingRecipe recipe;
    private boolean hasPower = false;
    private int enchantmentTime = 0;

    public float enchantProgress;

    public EnchanterBlockEntity() {
        super(BlockEntities.ENCHANTER);
        itemStacks = new ArrayList<>();
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag_1) {

        int i = 0;
        for(ItemStack itemStack : itemStacks) {
            compoundTag_1.put("item"+i, itemStack.toTag(new CompoundTag()));
            i++;
        }

        compoundTag_1.putBoolean("hasPower", hasPower);
        compoundTag_1.putInt("enchantmentTime", enchantmentTime);

        return super.toTag(compoundTag_1);
    }

    @Override
    public void fromTag(CompoundTag compoundTag_1) {

        for(int i = 0; i < SLOT_COUNT; i++) {
            if(compoundTag_1.containsKey("item"+i)) {
                this.itemStacks.add(ItemStack.fromTag(compoundTag_1.getCompound("item"+i)));
            }
        }

        hasPower = compoundTag_1.getBoolean("hasPower");
        enchantmentTime = compoundTag_1.getInt("enchantmentTime");

        recipe = EnchantingManager.getRecipe(itemStacks);

        super.fromTag(compoundTag_1);
    }

    /**
     * Tries to add an item to the list. If this is not possible this method will return false
     *
     * @param itemStack item to add
     * @return was successful
     */
    public boolean addItemStack(ItemStack itemStack) {

        if(!ready)
            return false;
        ready = false;
        ticker = tickerReset;

        if(itemStacks.size()>= SLOT_COUNT)
            return false;

        boolean result = this.itemStacks.add(itemStack);

        if(result)
            checkForEnchant();


        return result;
    }

    @SuppressWarnings("unchecked")
    private void checkForEnchant() {
        recipe = EnchantingManager.getRecipe((List<ItemStack>) itemStacks.clone());

        enchantmentTime = 0;
        hasPower = false;

        if(recipe == null)
            return;


        hasPower = getCurrentPowerLevel() >= recipe.getPowerLevel();

        if(hasPower)
            enchant();

    }

    private void enchant() {

        enchantmentTime++;

        enchantProgress = enchantmentTime / (float)recipe.getEnchantDurationTick();
        animate();


        if(enchantmentTime < recipe.getEnchantDurationTick())
            return;

        itemStacks.clear();

        for(ItemStack itemStack : recipe.getResult())
            itemStacks.add(itemStack.copy());

        recipe = null;


        checkForEnchant();
    }

    private int getCurrentPowerLevel() {
        int power = 0;
        for(Vec3d dir : MANA_OBTAIN_DIRECTIONS) {
            for(int i = 1; i <= EXTRACTOR_DISTANCE; i++) {
               Vec3d posVec =  dir.multiply(i);
                BlockPos blockPos = new BlockPos(getPos().getX() + posVec.x, getPos().getY() + posVec.y, getPos().getZ() + posVec.z);
                BlockState state = getWorld().getBlockState(blockPos);

                if(state == null)
                    continue;

                if(state.getBlock() == Blocks.BLOCK_MANA_EXTRACTOR) {
                    power++; //TODO: actually grab power level of tile entity
                    break;
                }

            }
        }
        return power;
    }

    public ArrayList<Vec3d> getPowerProvidersPositions() {

        ArrayList<Vec3d> providers = new ArrayList<>();

        for(Vec3d dir : MANA_OBTAIN_DIRECTIONS) {
            for(int i = 1; i <= EXTRACTOR_DISTANCE; i++) {
                Vec3d posVec =  dir.multiply(i);
                BlockPos blockPos = new BlockPos(getPos().getX() + posVec.x, getPos().getY() + posVec.y, getPos().getZ() + posVec.z);
                BlockState state = getWorld().getBlockState(blockPos);

                if(state == null)
                    continue;

                if(state.getBlock() == Blocks.BLOCK_MANA_EXTRACTOR) {
                    Vec3d position = new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    position = position.add(0.5, 0.5, 0.5);
                    providers.add(position);
                    break;
                }

            }
        }

        return providers;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ItemStack> clearAllItems() {
        if(!ready)
            return new ArrayList<>();
        ready = false;
        ticker = tickerReset;

        ArrayList<ItemStack> items = (ArrayList<ItemStack>) itemStacks.clone();
        itemStacks.clear();
        checkForEnchant();
        return items;
    }

    public ItemStack clearLastItem() {

        if(!ready)
            return null;
        ready = false;
        ticker = tickerReset;

        ItemStack last = null;

        if(itemStacks.size() > 0) {
            last = itemStacks.get(itemStacks.size()-1);
            itemStacks.remove(last);
            checkForEnchant();
        }

        return last;
    }


    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        this.fromTag(compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return this.toTag(compoundTag);
    }

    @Override
    public void tick() {

        clickReduction();

        if(recipe == null)
            return;

        hasPower = recipe.getPowerLevel() <= getCurrentPowerLevel();

        if(hasPower)
            enchant();
    }

    private void clickReduction() {
        if(ready)
            return;
        ticker--;
        if(ticker <= 0)
            ready = true;
    }

    public ArrayList<ItemStack> getItemStacks() {
        return itemStacks;
    }

    public boolean isEnchanting() {
        return recipe != null;
    }

    public EnchantingRecipe getRecipe() {
        return recipe;
    }

    public boolean hasContents() {
        return !itemStacks.isEmpty();
    }

    public boolean hasPower() {
        return hasPower;
    }

    public int getRequiredPower() {
        return recipe.getPowerLevel();
    }

    private void animate() {
        //TODO: show particles taking mana
        if(world == null)
            return;

        Vec3d thisPosition = new Vec3d(pos.getX()+.5, pos.getY()+.5, pos.getZ()+.5);

        for(Vec3d position : getPowerProvidersPositions()) {

            Vec3d origin = new Vec3d(position.x, position.y, position.z);
            Vec3d velocity = thisPosition.subtract(origin);

            double speed = 0.25 * (velocity.length()/3);



            velocity = velocity.normalize().multiply(speed);

            for(int i = 0; i < 3; i++)
                world.addParticle(ParticleTypes.END_ROD,true, origin.x, origin.y, origin.z, velocity.x, velocity.y, velocity.z);
        }
    }
}
