package com.iyxan23.sumatra.classfile.models

import java.io.DataInputStream

class AttributeInfo(
    val attributeNameIndex: UShort,
    val info: List<UByte>,
) {
    companion object {
        fun parseAttributes(
            stream: DataInputStream,
            attributesCount: UShort,
            constantPool: List<ConstantInfo>
        ): List<AttributeInfo> {
            TODO("Not yet implemented")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AttributeInfo

        if (attributeNameIndex != other.attributeNameIndex) return false
        if (info != other.info) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attributeNameIndex.hashCode()
        result = 31 * result + info.hashCode()
        return result
    }
}
