package com.iyxan23.sumatra.classfile

// https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.4
sealed class ConstantInfo(
    val tag: UByte
) {
    data class Utf8(
        val length: UShort,
        val bytes: Array<UShort>,
    ) : ConstantInfo(1u) {
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
    ) : ConstantInfo(3u)

    data class Float(
        val bytes: Int
    ) : ConstantInfo(4u)

    data class Long(
        val highBytes: Int,
        val lowBytes: Int,
    ) : ConstantInfo(5u)

    data class Double(
        val highBytes: Int,
        val lowBytes: Int,
    ) : ConstantInfo(6u)

    data class Class(
        val nameIndex: UShort
    ) : ConstantInfo(7u)

    data class String(
        val stringIndex: UShort,
    ) : ConstantInfo(8u)

    data class FieldRef(
        val classIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(9u)

    data class MethodRef(
        val classIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(10u)

    data class InterfaceMethodRef(
        val classIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(11u)

    data class NameAndType(
        val nameIndex: UShort,
        val descriptorIndex: UShort,
    ) : ConstantInfo(12u)

    data class MethodHandle(
        val referenceKind: UByte,
        val referenceIndex: UShort,
    ) : ConstantInfo(15u)

    data class MethodType(
        val descriptorIndex: UShort
    ) : ConstantInfo(16u)

    data class Dynamic(
        val bootstrapMethodAttrIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(17u)

    data class InvokeDynamic(
        val bootstrapMethodAttrIndex: UShort,
        val nameAndTypeIndex: UShort,
    ) : ConstantInfo(18u)

    data class Module(
        val nameIndex: UShort
    ) : ConstantInfo(19u)
    
    data class Package(
        val nameIndex: UShort
    ) : ConstantInfo(20u)
}
