package com.iyxan23.sumatra.classfile.models

data class AttributeInfo(
    val attributeNameIndex: UShort,
    val attributeLength: Int,
    val info: Array<UByte>, // length: attributeLength
) {
    // had to override equals and hashCode since we have an Array
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AttributeInfo

        if (attributeNameIndex != other.attributeNameIndex) return false
        if (attributeLength != other.attributeLength) return false
        if (!info.contentEquals(other.info)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attributeNameIndex.hashCode()
        result = 31 * result + attributeLength
        result = 31 * result + info.contentHashCode()
        return result
    }
}
