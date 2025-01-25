package de.adrian.projectbee.entities.mounts;

import cn.nukkit.entity.custom.EntityDefinition;
import cn.nukkit.entity.custom.EntityManager;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3f;
import cn.nukkit.nbt.tag.CompoundTag;
import de.adrian.projectbee.entities.MountableEntity;

public class UfoEntity extends MountableEntity {

    public static final String IDENTIFIER = "projectbee:ufo";

    public static final EntityDefinition DEFINITION = EntityDefinition.builder().identifier(UfoEntity.IDENTIFIER).implementation(UfoEntity.class).build();

    private static int i;

    public UfoEntity(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.riderPlayerOffset = new Vector3f(0.0F, 1.8F, -0.5F);
    }

    @Override
    public float getHeight() {
        return 2.0F;
    }

    @Override
    public float getWidth() {
        return 2.0F;
    }

    @Override
    public EntityDefinition getEntityDefinition() {
        return DEFINITION;
    }

    @Override
    public int getNetworkId() {
        return EntityManager.get().getRuntimeId(IDENTIFIER);
    }
}

