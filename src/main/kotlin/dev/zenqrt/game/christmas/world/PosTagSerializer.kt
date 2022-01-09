package dev.zenqrt.game.christmas.world

import net.minestom.server.coordinate.Pos
import net.minestom.server.tag.Tag
import net.minestom.server.tag.TagReadable
import net.minestom.server.tag.TagSerializer
import net.minestom.server.tag.TagWritable

class PosTagSerializer : TagSerializer<Pos> {
    override fun read(reader: TagReadable): Pos = Pos(
        reader.getTag(X_TAG)!!,
        reader.getTag(Y_TAG)!!,
        reader.getTag(Z_TAG)!!,
        reader.getTag(YAW_TAG)!!,
        reader.getTag(PITCH_TAG)!!
    )

    override fun write(writer: TagWritable, value: Pos?) {
        writer.setTag(X_TAG, value?.x)
        writer.setTag(Y_TAG, value?.y)
        writer.setTag(Z_TAG, value?.z)
        writer.setTag(YAW_TAG, value?.yaw)
        writer.setTag(PITCH_TAG, value?.pitch)
    }

    companion object {
        private val X_TAG = Tag.Double("X")
        private val Y_TAG = Tag.Double("Y")
        private val Z_TAG = Tag.Double("Z")
        private val YAW_TAG = Tag.Float("Yaw")
        private val PITCH_TAG = Tag.Float("Pitch")
    }
}