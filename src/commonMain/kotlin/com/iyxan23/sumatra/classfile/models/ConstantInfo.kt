package com.iyxan23.sumatra.classfile.models

import java.io.DataInputStream

// https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.4
sealed class ConstantInfo(
    val tag: Type
) {
    enum class Type(private val tag: UByte) {
        UTF8(1u),
        INTEGER(3u),
        FLOAT(4u),
        LONG(5u),
        DOUBLE(6u),
        CLASS(7u),
        STRING(8u),
        FIELD_REF(9u),
        METHOD_REF(10u),
        INTERFACE_METHOD_REF(11u),
        NAME_AND_TYPE(12u),
        METHOD_HANDLE(15u),
        METHOD_TYPE(16u),
        DYNAMIC(17u),
        INVOKE_DYNAMIC(18u),
        MODULE(19u),
        PACKAGE(20u);

        companion object {
            fun findTag(tag: UByte): Type? =
                values().find { it.tag == tag }
        }
    }

    data class Utf8(
        val length: UShort,
        val bytes: ByteArray,
    ) : ConstantInfo(Type.UTF8) {
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
        val bytes: Int
    ) : ConstantInfo(Type.INTEGER)

    data class Float(
        val float: kotlin.Float
    ) : ConstantInfo(Type.FLOAT)

    data class Long(
        val long: kotlin.Long
    ) : ConstantInfo(Type.LONG)

    data class Double(
        val highBytes: Int,
        val lowBytes: Int,
    ) : ConstantInfo(Type.DOUBLE)

    data class Class(
        val nameIndex: UShort
    ) : ConstantInfo(Type.CLASS)

    data class String(
        val stringIndex: UShort,
    ) : ConstantInfo(Type.STRING)

    data class FieldRef(
        val classIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(Type.FIELD_REF)

    data class MethodRef(
        val classIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(Type.METHOD_REF)

    data class InterfaceMethodRef(
        val classIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(Type.INTERFACE_METHOD_REF)

    data class NameAndType(
        val nameIndex: UShort,
        val descriptorIndex: UShort,
    ) : ConstantInfo(Type.NAME_AND_TYPE)

    data class MethodHandle(
        val referenceKind: UByte,
        val referenceIndex: UShort,
    ) : ConstantInfo(Type.METHOD_HANDLE)

    data class MethodType(
        val descriptorIndex: UShort
    ) : ConstantInfo(Type.METHOD_TYPE)

    data class Dynamic(
        val bootstrapMethodAttrIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(Type.DYNAMIC)

    data class InvokeDynamic(
        val bootstrapMethodAttrIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(Type.INVOKE_DYNAMIC)

    data class Module(
        val nameIndex: UShort
    ) : ConstantInfo(Type.MODULE)
    
    data class Package(
        val nameIndex: UShort
    ) : ConstantInfo(Type.PACKAGE)
}
