package net.ceoofgoogle.createclothes.init;

import net.ceoofgoogle.createclothes.CreateClothes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreateClothesModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, CreateClothes.MOD_ID);

    public static final Holder<SoundEvent> UI_HIT_01 = register("ui_hit_01");
    public static final Holder<SoundEvent> UI_HIT_02 = register("ui_hit_02");
    public static final Holder<SoundEvent> UI_HIT_03 = register("ui_hit_03");
    public static final Holder<SoundEvent> UI_HIT_04 = register("ui_hit_04");
    public static final Holder<SoundEvent> UI_HIT_05 = register("ui_hit_05");
    public static final Holder<SoundEvent> UI_HIT_06 = register("ui_hit_06");
    public static final Holder<SoundEvent> UI_HIT_07 = register("ui_hit_07");
    public static final Holder<SoundEvent> UI_HIT_08 = register("ui_hit_08");
    public static final Holder<SoundEvent> UI_HIT_09 = register("ui_hit_09");
    public static final Holder<SoundEvent> UI_HIT_10 = register("ui_hit_10");
    public static final Holder<SoundEvent> UI_HIT_11 = register("ui_hit_11");

    @SuppressWarnings("unchecked")
    public static final Holder<SoundEvent>[] UI_HITS = new Holder[]{
        UI_HIT_01, UI_HIT_02, UI_HIT_03, UI_HIT_04, UI_HIT_05, UI_HIT_06,
        UI_HIT_07, UI_HIT_08, UI_HIT_09, UI_HIT_10, UI_HIT_11
    };

    private static Holder<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CreateClothes.MOD_ID, name)));
    }
}
