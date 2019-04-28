package de.gerolmed.wandustry.structure.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureManager;

public interface CreatePieceFactory {
    SimpleStructurePiece create(StructureManager structureManager_1, CompoundTag compoundTag_1);
}
