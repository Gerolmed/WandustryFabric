package de.gerolmed.fabricmod.structure.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructurePieceType;
public abstract class BasePiece extends SimpleStructurePiece {


    public BasePiece(StructurePieceType structurePieceType_1, int int_1) {
        super(structurePieceType_1, int_1);
    }

    public BasePiece(StructurePieceType structurePieceType_1, CompoundTag compoundTag_1) {
        super(structurePieceType_1, compoundTag_1);
    }
}
