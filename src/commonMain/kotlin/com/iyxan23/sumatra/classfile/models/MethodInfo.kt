package com.iyxan23.sumatra.classfile.models

import java.io.DataInputStream

class MethodInfo(
    val accessFlags: UShort,
    val nameIndex: UShort,
    val descriptorIndex: UShort,
    val attributes: List<AttributeInfo>,
) {
    companion object {
        fun parseMethods(
            stream: DataInputStream,
            methodsCount: UShort,
            constantPool: List<ConstantInfo>
        ): List<MethodInfo> {
            TODO("Not yet implemented")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MethodInfo

        if (accessFlags != other.accessFlags) return false
        if (nameIndex != other.nameIndex) return false
        if (descriptorIndex != other.descriptorIndex) return false
        if (attributes != other.attributes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accessFlags.hashCode()
        result = 31 * result + nameIndex.hashCode()
        result = 31 * result + descriptorIndex.hashCode()
        result = 31 * result + attributes.hashCode()
        return result
    }
}
