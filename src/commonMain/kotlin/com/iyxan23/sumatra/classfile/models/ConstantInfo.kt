package com.iyxan23.sumatra.classfile.models

import java.io.DataInputStream

// https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.4
sealed class ConstantInfo(
    val tag: ConstantInfoType
) {
    /**
     * This function will be called by [com.iyxan23.sumatra.classfile.ClassFileParser] to resolve the references of a
     * ConstantInfo, like, setting the name of a [Class], etc
     */
    open fun resolveReferences(constantPool: Array<ConstantInfo>) {}

    data class Utf8(
        val length: UShort,
        val bytes: ByteArray,
    ) : ConstantInfo(ConstantInfoType.UTF8) {
        val string get() = String(bytes)

        // had to override equals and hashCode since we have an Array
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Utf8

            if (length != other.length) return false
            if (!bytes.contentEquals(other.bytes)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = length.hashCode()
            result = 31 * result + bytes.contentHashCode()
            return result
        }
    }

    data class Integer(
        val integer: Int
    ) : ConstantInfo(ConstantInfoType.INTEGER)

    data class Float(
        val float: kotlin.Float
    ) : ConstantInfo(ConstantInfoType.FLOAT)

    data class Long(
        val long: kotlin.Long
    ) : ConstantInfo(ConstantInfoType.LONG)

    data class Double(
        val double: kotlin.Double
    ) : ConstantInfo(ConstantInfoType.DOUBLE)

    data class Class(
        val nameIndex: UShort
    ) : ConstantInfo(ConstantInfoType.CLASS) {

    }

    data class String(
        val stringIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.STRING)

    data class FieldRef(
        val classIndex: UShort,
        val nameAndConstantInfoTypeIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.FIELD_REF)

    data class MethodRef(
        val classIndex: UShort,
        val nameAndConstantInfoTypeIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.METHOD_REF)

    data class InterfaceMethodRef(
        val classIndex: UShort,
        val nameAndConstantInfoTypeIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.INTERFACE_METHOD_REF)

    data class NameAndConstantInfoType(
        val nameIndex: UShort,
        val descriptorIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.NAME_AND_TYPE)

    data class MethodHandle(
        val referenceKind: UByte,
        val referenceIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.METHOD_HANDLE)

    data class MethodConstantInfoType(
        val descriptorIndex: UShort
    ) : ConstantInfo(ConstantInfoType.METHOD_TYPE)

    data class Dynamic(
        val bootstrapMethodAttrIndex: UShort,
        val nameAndConstantInfoTypeIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.DYNAMIC)

    data class InvokeDynamic(
        val bootstrapMethodAttrIndex: UShort,
        val nameAndConstantInfoTypeIndex: UShort,
    ) : ConstantInfo(ConstantInfoType.INVOKE_DYNAMIC)

    data class Module(
        val nameIndex: UShort
    ) : ConstantInfo(ConstantInfoType.MODULE)
    
    data class Package(
        val nameIndex: UShort
    ) : ConstantInfo(ConstantInfoType.PACKAGE)
}
