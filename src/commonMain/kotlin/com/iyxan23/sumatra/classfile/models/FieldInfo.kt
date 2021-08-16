package com.iyxan23.sumatra.classfile.models

data class FieldInfo(
    val accessFlags: UShort,
    val nameIndex: UShort,
    val descriptorIndex: UShort,
    val attributesCount: UShort,
    val attributes: Array<AttributeInfo>, // length: attributesCount
) {
    // had to override equals and hashCode since we have an Array
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FieldInfo

        if (accessFlags != other.accessFlags) return false
        if (nameIndex != other.nameIndex) return false
        if (descriptorIndex != other.descriptorIndex) return false
        if (attributesCount != other.attributesCount) return false
        if (!attributes.contentEquals(other.attributes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accessFlags.hashCode()
        result = 31 * result + nameIndex.hashCode()
        result = 31 * result + descriptorIndex.hashCode()
        result = 31 * result + attributesCount.hashCode()
        result = 31 * result + attributes.contentHashCode()
        return result
    }
}