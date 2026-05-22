package net.ceoofgoogle.createclothes.entity;

import net.ceoofgoogle.createclothes.init.CreateClothesModDataComponents;
import net.ceoofgoogle.createclothes.init.CreateClothesModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class ParachuteEntity extends Entity {
    private static final EntityDataAccessor<Integer> ATTACHED_ID = SynchedEntityData.defineId(ParachuteEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> SCALE = SynchedEntityData.defineId(ParachuteEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> PUNCTURES = SynchedEntityData.defineId(ParachuteEntity.class, EntityDataSerializers.INT);

    private static final float MAX_OFFSET_Y = 5.7F;
    private static final float OPEN_THRESHOLD = -0.1F;
    private static final float CLOSE_THRESHOLD = -0.02F;
    private static final float OPEN_LERP = 0.04F;
    private static final float CLOSE_LERP = 0.02F;
    private static final float BASE_HITBOX = 1.0F;
    private static final float MAX_HITBOX_WIDTH = 8.0F;
    private static final float MAX_HITBOX_HEIGHT = 5.0F;
    private static final int MAX_PUNCTURES = 10;
    private static final float BASE_FALL_SPEED = -0.05F;

    private float smoothedTarget = 0.0F;
    private final List<Vec3> punctureOffsets = new ArrayList<>();

    public ParachuteEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(ATTACHED_ID, -1);
        builder.define(SCALE, 0.0F);
        builder.define(PUNCTURES, 0);
    }

    public void setAttached(Entity entity) {
        this.getEntityData().set(ATTACHED_ID, entity == null ? -1 : entity.getId());
    }

    public Entity getAttached() {
        int id = this.getEntityData().get(ATTACHED_ID);
        return id == -1 ? null : this.level().getEntity(id);
    }

    public float getParachuteScale() {
        return this.getEntityData().get(SCALE);
    }

    public int getPunctures() {
        return this.getEntityData().get(PUNCTURES);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerable()) return false;
        if (this.level().isClientSide) return true;

        int current = getPunctures() + 1;
        this.getEntityData().set(PUNCTURES, current);

        Vec3 hitPos = source.getSourcePosition();
        double px = hitPos != null ? hitPos.x - this.getX() : (this.random.nextFloat() - 0.5) * 3;
        double py = hitPos != null ? hitPos.y - this.getY() : 1.0 + this.random.nextFloat() * 2;
        double pz = hitPos != null ? hitPos.z - this.getZ() : (this.random.nextFloat() - 0.5) * 3;
        punctureOffsets.add(new Vec3(px, py, pz));

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.WOOL_BREAK, SoundSource.NEUTRAL, 1.0F, 1.2F);

        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                    this.getX() + px, this.getY() + py, this.getZ() + pz, 12, 0.2, 0.2, 0.2, 0.02);
        }

        if (current >= MAX_PUNCTURES) {
            dropClothAndDiscard();
        }

        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void tick() {
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();

        super.tick();

        Entity attached = getAttached();
        if (attached != null) {
            if (attached.isRemoved() || !hasOpenParachuteInInventory(attached)) {
                if (!this.level().isClientSide) detach(false);
                return;
            }

            if (!this.level().isClientSide && (attached.onGround() || attached.isInWater())) {
                detach(true);
                return;
            }

            float currentScale = getParachuteScale();
            float fallSpeed = (float) attached.getDeltaMovement().y;

            boolean wantsOpen;
            if (currentScale > 0.3F) {
                wantsOpen = fallSpeed < CLOSE_THRESHOLD;
            } else {
                wantsOpen = fallSpeed < OPEN_THRESHOLD;
            }

            if (wantsOpen) {
                float intensity = Math.min(1.0F, (float)(OPEN_THRESHOLD - fallSpeed) / 0.8F);
                smoothedTarget += (intensity - smoothedTarget) * 0.1F;
            } else {
                smoothedTarget += (0.0F - smoothedTarget) * 0.05F;
            }
            smoothedTarget = Math.max(0.0F, Math.min(1.0F, smoothedTarget));

            float lerpSpeed = currentScale < smoothedTarget ? OPEN_LERP : CLOSE_LERP;
            currentScale += (smoothedTarget - currentScale) * lerpSpeed;

            float leak = 0.0F;
            int punctures = getPunctures();
            if (punctures > 0) {
                leak = 0.01F + Math.max(0, punctures - 2) * 0.01F;
            }
            currentScale -= leak;
            currentScale = Math.max(0.0F, Math.min(1.0F, currentScale));

            if (punctures == 0 && currentScale >= 0.97F) currentScale = 1.0F;
            if (currentScale < 0.005F) currentScale = 0.0F;

            if (!this.level().isClientSide) this.getEntityData().set(SCALE, currentScale);

            float offsetY = currentScale * MAX_OFFSET_Y;
            this.xo = attached.xo;
            this.yo = attached.yo + offsetY;
            this.zo = attached.zo;
            this.setPos(attached.getX(), attached.getY() + offsetY, attached.getZ());

            if (attached instanceof LivingEntity living && currentScale > 0.05F) {
                if (getPunctures() < 5) {
                    float liftFactor = 1.0F - (float) getPunctures() / 5.0F;
                    Vec3 delta = living.getDeltaMovement();
                    float limit = (-0.08F / currentScale) * liftFactor + (-2.0F) * (1.0F - liftFactor);
                    if (delta.y < limit) {
                        living.setDeltaMovement(delta.x, limit, delta.z);
                    }
                    living.resetFallDistance();
                }
            }

            double dx = attached.getX() - attached.xo;
            double dz = attached.getZ() - attached.zo;
            double speed = Math.sqrt(dx * dx + dz * dz);

            if (speed > 0.005) {
                float targetYaw = (float) Math.toDegrees(Math.atan2(dx, -dz));
                this.setYRot(net.minecraft.util.Mth.approachDegrees(this.getYRot(), targetYaw, 15.0F));
                float targetPitch = (float) Math.min(35, speed * 200);
                this.setXRot(net.minecraft.util.Mth.approachDegrees(this.getXRot(), targetPitch, 5.0F));
            } else {
                this.setXRot(net.minecraft.util.Mth.approachDegrees(this.getXRot(), 0, 5.0F));
            }

            updateHitbox(currentScale);

            spawnPunctureParticles();
        } else {
            this.setYRot(net.minecraft.util.Mth.approachDegrees(this.getYRot(), 0, 10.0F));
            this.setXRot(net.minecraft.util.Mth.approachDegrees(this.getXRot(), 0, 10.0F));

            float currentScale = getParachuteScale();
            updateHitbox(currentScale);

            float punctureRatio = (float) getPunctures() / MAX_PUNCTURES;
            float fallSpeed = BASE_FALL_SPEED - (punctureRatio * 0.6F);

            Vec3 delta = this.getDeltaMovement();
            this.setDeltaMovement(delta.x * 0.98, fallSpeed, delta.z * 0.98);
            this.setPos(this.getX() + delta.x, this.getY() + fallSpeed, this.getZ() + delta.z);

            if (!this.level().isClientSide) {
                if (this.getY() < this.level().getMinBuildHeight() || this.tickCount > 1200) {
                    dropClothAndDiscard();
                    return;
                }

                if (isOnSolidGround()) {
                    dropClothAndDiscard();
                    return;
                }
            }
        }
    }

    private void detach(boolean deleteItem) {
        Entity attached = getAttached();
        if (attached instanceof Player player) {
            if (deleteItem) {
                if (!deleteOpenParachute(player.getInventory().items)) {
                    deleteOpenParachute(player.getInventory().offhand);
                }
            } else {
                clearOpenParachutes(player.getInventory().items);
                clearOpenParachutes(player.getInventory().offhand);
            }
        }
        setAttached(null);
    }

    private boolean deleteOpenParachute(List<ItemStack> list) {
        for (ItemStack stack : list) {
            if (!stack.isEmpty() && stack.getItem() == CreateClothesModItems.PARACHUTE.get() && stack.getOrDefault(CreateClothesModDataComponents.IS_OPEN.get(), false)) {
                stack.shrink(1);
                return true;
            }
        }
        return false;
    }

    private void clearOpenParachutes(List<ItemStack> list) {
        for (ItemStack stack : list) {
            if (!stack.isEmpty() && stack.getItem() == CreateClothesModItems.PARACHUTE.get() && stack.getOrDefault(CreateClothesModDataComponents.IS_OPEN.get(), false)) {
                stack.set(CreateClothesModDataComponents.IS_OPEN.get(), false);
            }
        }
    }

    private void spawnPunctureParticles() {
        if (!(this.level() instanceof ServerLevel serverLevel)) return;
        if (punctureOffsets.isEmpty()) return;
        if (this.tickCount % 3 != 0) return;

        for (Vec3 offset : punctureOffsets) {
            serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                    this.getX() + offset.x, this.getY() + offset.y, this.getZ() + offset.z,
                    1, 0.05, 0.05, 0.05, 0.005);
        }
    }

    private boolean isOnSolidGround() {
        BlockPos below = BlockPos.containing(this.getX(), this.getY() - 0.1, this.getZ());
        BlockState state = this.level().getBlockState(below);
        return !state.isAir();
    }

    private void dropClothAndDiscard() {
        boolean wasAttached = getAttached() != null;
        float scale = getParachuteScale();
        if (!this.level().isClientSide) {
            detach(wasAttached);
        }
        if (this.level() instanceof ServerLevel serverLevel) {
            ItemStack dropStack;
            if (!wasAttached && scale < 0.03F) {
                dropStack = new ItemStack(CreateClothesModItems.PARACHUTE.get(), 1);
            } else {
                dropStack = new ItemStack(CreateClothesModItems.CLOTH.get(), 1);
            }
            ItemEntity drop = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), dropStack);
            drop.setDefaultPickUpDelay();
            serverLevel.addFreshEntity(drop);
            serverLevel.sendParticles(ParticleTypes.POOF, this.getX(), this.getY() + 1, this.getZ(), 10, 0.5, 0.3, 0.5, 0.02);
        }
        this.discard();
    }

    private void updateHitbox(float scale) {
        float w = BASE_HITBOX + (MAX_HITBOX_WIDTH - BASE_HITBOX) * scale;
        float h = BASE_HITBOX + (MAX_HITBOX_HEIGHT - BASE_HITBOX) * scale;
        float halfW = w / 2.0F;
        float yOffset = 2.5F * scale;
        this.setBoundingBox(new AABB(
                this.getX() - halfW, this.getY() + yOffset, this.getZ() - halfW,
                this.getX() + halfW, this.getY() + yOffset + h, this.getZ() + halfW
        ));
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return this.getBoundingBox().inflate(10);
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    private boolean hasOpenParachuteInInventory(Entity entity) {
        if (entity instanceof Player player) {
            return hasOpenParachute(player.getInventory().items) || hasOpenParachute(player.getInventory().offhand);
        }
        return false;
    }

    private boolean hasOpenParachute(List<ItemStack> list) {
        for (ItemStack stack : list) {
            if (!stack.isEmpty() && stack.getItem() == CreateClothesModItems.PARACHUTE.get() && stack.getOrDefault(CreateClothesModDataComponents.IS_OPEN.get(), false)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity);
    }

    @Override
    public void lerpTo(double pX, double pY, double pZ, float pYaw, float pPitch, int pPosRotationIncremental) {
        if (getAttached() == null) {
            super.lerpTo(pX, pY, pZ, pYaw, pPitch, pPosRotationIncremental);
        }
    }

    @Override protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("Punctures")) {
            this.getEntityData().set(PUNCTURES, compound.getInt("Punctures"));
        }
        punctureOffsets.clear();
        if (compound.contains("PunctureOffsets", Tag.TAG_LIST)) {
            ListTag list = compound.getList("PunctureOffsets", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag pos = list.getCompound(i);
                punctureOffsets.add(new Vec3(pos.getDouble("X"), pos.getDouble("Y"), pos.getDouble("Z")));
            }
        }
    }

    @Override protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Punctures", getPunctures());
        ListTag list = new ListTag();
        for (Vec3 offset : punctureOffsets) {
            CompoundTag pos = new CompoundTag();
            pos.putDouble("X", offset.x);
            pos.putDouble("Y", offset.y);
            pos.putDouble("Z", offset.z);
            list.add(pos);
        }
        compound.put("PunctureOffsets", list);
    }
}
