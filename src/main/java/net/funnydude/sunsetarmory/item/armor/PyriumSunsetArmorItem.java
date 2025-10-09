package net.funnydude.sunsetarmory.item.armor;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.funnydude.sunsetarmory.item.ModArmorMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.constant.dataticket.SerializableDataTicket;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.function.Consumer;


public class PyriumSunsetArmorItem extends ImbuableChestplateArmorItem {
    public PyriumSunsetArmorItem(Type slot, Properties settings) {
        super(ModArmorMaterials.PYRIUM_SUNSET_ARMOR_MATERIAL, slot, settings,
                new AttributeContainer(AttributeRegistry.MAX_MANA, 100, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.02, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    public GeoArmorRenderer<?> supplyRenderer() {
        return null;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }

    @Override
    public InteractionResultHolder<ItemStack> swapWithEquipmentSlot(Item item, Level level, Player player, InteractionHand hand) {
        return super.swapWithEquipmentSlot(item, level, player, hand);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return super.getDefaultAttributeModifiers(stack);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public Component getHighlightTip(ItemStack item, Component displayName) {
        return super.getHighlightTip(item, displayName);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public boolean isPiglinCurrency(ItemStack stack) {
        return super.isPiglinCurrency(stack);
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return super.makesPiglinsNeutral(stack, wearer);
    }

    @Override
    public float getXpRepairRatio(ItemStack stack) {
        return super.getXpRepairRatio(stack);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        super.onStopUsing(stack, entity, count);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return super.getCraftingRemainingItem(itemStack);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return super.hasCraftingRemainingItem(stack);
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return super.getEntityLifespan(itemStack, level);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return super.hasCustomEntity(stack);
    }

    @Override
    public @Nullable Entity createEntity(Level level, Entity location, ItemStack stack) {
        return super.createEntity(level, location, stack);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
        return super.doesSneakBypassUse(stack, level, pos, player);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, LivingEntity entity) {
        return super.canEquip(stack, armorType, entity);
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return super.getEquipmentSlot(stack);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return super.isBookEnchantable(stack, book);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return super.getArmorTexture(stack, entity, slot, layer, innerModel);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return super.onEntitySwing(stack, entity);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        return super.onEntitySwing(stack, entity, hand);
    }

    @Override
    public int getDamage(ItemStack stack) {
        return super.getDamage(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return super.getMaxDamage(stack);
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return super.isDamaged(stack);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, damage);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return super.canPerformAction(stack, itemAbility);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return super.getMaxStackSize(stack);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return super.getEnchantmentValue(stack);
    }

    @Override
    public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.isPrimaryItemFor(stack, enchantment);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(stack, enchantment);
    }

    @Override
    public int getEnchantmentLevel(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.getEnchantmentLevel(stack, enchantment);
    }

    @Override
    public ItemEnchantments getAllEnchantments(ItemStack stack, HolderLookup.RegistryLookup<Enchantment> lookup) {
        return super.getAllEnchantments(stack, lookup);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        return super.shouldCauseBlockBreakReset(oldStack, newStack);
    }

    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
        return super.canContinueUsing(oldStack, newStack);
    }

    @Override
    public @Nullable String getCreatorModId(ItemStack itemStack) {
        return super.getCreatorModId(itemStack);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return super.canDisableShield(stack, shield, entity, attacker);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return super.getBurnTime(itemStack, recipeType);
    }

    @Override
    public void onAnimalArmorTick(ItemStack stack, Level level, Mob horse) {
        super.onAnimalArmorTick(stack, level, horse);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        super.onDestroyed(itemEntity, damageSource);
    }

    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
        return super.isEnderMask(stack, player, endermanEntity);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return super.canElytraFly(stack, entity);
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        return super.elytraFlightTick(stack, entity, flightTicks);
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return super.canWalkOnPowderedSnow(stack, wearer);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return super.isDamageable(stack);
    }

    @Override
    public AABB getSweepHitBox(ItemStack stack, Player player, Entity target) {
        return super.getSweepHitBox(stack, player, target);
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        return super.getFoodProperties(stack, entity);
    }

    @Override
    public boolean isNotReplaceableByPickAction(ItemStack stack, Player player, int inventorySlot) {
        return super.isNotReplaceableByPickAction(stack, player, inventorySlot);
    }

    @Override
    public boolean canGrindstoneRepair(ItemStack stack) {
        return super.canGrindstoneRepair(stack);
    }

    @Override
    public boolean canBeHurtBy(ItemStack stack, DamageSource source) {
        return super.canBeHurtBy(stack, source);
    }

    @Override
    public ItemStack applyEnchantments(ItemStack stack, List<EnchantmentInstance> enchantments) {
        return super.applyEnchantments(stack, enchantments);
    }

    @Override
    public double getBoneResetTime() {
        return super.getBoneResetTime();
    }

    @Override
    public boolean shouldPlayAnimsWhileGamePaused() {
        return super.shouldPlayAnimsWhileGamePaused();
    }

    @Override
    public double getTick(Object itemStack) {
        return super.getTick(itemStack);
    }

    @Override
    public boolean isPerspectiveAware() {
        return super.isPerspectiveAware();
    }

    @Override
    public <D> @Nullable D getAnimData(long instanceId, SerializableDataTicket<D> dataTicket) {
        return super.getAnimData(instanceId, dataTicket);
    }

    @Override
    public <D> void setAnimData(Entity relatedEntity, long instanceId, SerializableDataTicket<D> dataTicket, D data) {
        super.setAnimData(relatedEntity, instanceId, dataTicket, data);
    }

    @Override
    public <D> void syncAnimData(long instanceId, SerializableDataTicket<D> dataTicket, D data, Entity entityToTrack) {
        super.syncAnimData(instanceId, dataTicket, data, entityToTrack);
    }

    @Override
    public <D> void triggerAnim(Entity relatedEntity, long instanceId, @Nullable String controllerName, String animName) {
        super.triggerAnim(relatedEntity, instanceId, controllerName, animName);
    }

    @Override
    public void stopTriggeredAnim(Entity relatedEntity, long instanceId, @Nullable String controllerName, @Nullable String animName) {
        super.stopTriggeredAnim(relatedEntity, instanceId, controllerName, animName);
    }

    @Override
    public void triggerArmorAnim(Entity relatedEntity, long instanceId, @Nullable String controllerName, String animName) {
        super.triggerArmorAnim(relatedEntity, instanceId, controllerName, animName);
    }

    @Override
    public void stopTriggeredArmorAnim(Entity relatedEntity, long instanceId, @Nullable String controllerName, @Nullable String animName) {
        super.stopTriggeredArmorAnim(relatedEntity, instanceId, controllerName, animName);
    }

    @Override
    public @Nullable AnimatableInstanceCache animatableCacheOverride() {
        return super.animatableCacheOverride();
    }

    @Override
    public Object getRenderProvider() {
        return super.getRenderProvider();
    }
}
