package com.iyxan23.sumatra.classfile.models

// https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html
data class ClassFile(
    val magic: Int,
    val minorVersion: UShort,
    val majorVersion: UShort,
    val constantPoolCount: UShort,
    val constantPool: Array<ConstantInfo>, // length: constantPoolCount
    val accessFlags: UShort,
    val thisClass: UShort,
    val superClass: UShort,
    val interfacesCount: UShort,
    val interfaces: Array<UShort>, // length: interfacesCount
    val fieldsCount: UShort,
    val fields: Array<FieldInfo>, // length: fieldsCount
    val methodsCount: UShort,
    val methods: Array<MethodInfo>, // length: methodsCount
    val attributesCount: UShort,
    val attributes: Array<AttributeInfo>, // length: attributesCount
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClassFile

        if (magic != other.magic) return false
        if (minorVersion != other.minorVersion) return false
        if (majorVersion != other.majorVersion) return false
        if (constantPoolCount != other.constantPoolCount) return false
        if (!constantPool.contentEquals(other.constantPool)) return false
        if (accessFlags != other.accessFlags) return false
        if (thisClass != other.thisClass) return false
        if (superClass != other.superClass) return false
        if (interfacesCount != other.interfacesCount) return false
        if (!interfaces.contentEquals(other.interfaces)) return false
        if (fieldsCount != other.fieldsCount) return false
        if (!fields.contentEquals(other.fields)) return false
        if (methodsCount != other.methodsCount) return false
        if (!methods.contentEquals(other.methods)) return false
        if (attributesCount != other.attributesCount) return false
        if (!attributes.contentEquals(other.attributes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = magic
        result = 31 * result + minorVersion.hashCode()
        result = 31 * result + majorVersion.hashCode()
        result = 31 * result + constantPoolCount.hashCode()
        result = 31 * result + constantPool.contentHashCode()
        result = 31 * result + accessFlags.hashCode()
        result = 31 * result + thisClass.hashCode()
        result = 31 * result + superClass.hashCode()
        result = 31 * result + interfacesCount.hashCode()
        result = 31 * result + interfaces.contentHashCode()
        result = 31 * result + fieldsCount.hashCode()
        result = 31 * result + fields.contentHashCode()
        result = 31 * result + methodsCount.hashCode()
        result = 31 * result + methods.contentHashCode()
        result = 31 * result + attributesCount.hashCode()
        result = 31 * result + attributes.contentHashCode()
        return result
    }
}