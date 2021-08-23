package com.iyxan23.sumatra.classfile.models

import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

// https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html
class ClassFile private constructor(
    val magic: Int,
    val minorVersion: UShort,
    val majorVersion: UShort,
    val constantPool: List<ConstantInfo>,
    val accessFlags: UShort,
    val thisClass: UShort,
    val superClass: UShort,
    val interfaces: List<UShort>,
    val fields: List<FieldInfo>,
    val methods: List<MethodInfo>,
    val attributes: List<AttributeInfo>,
) {
    companion object {
        fun from(file: File): ClassFile = from(FileInputStream(file))
        fun from(stream: InputStream): ClassFile = from(DataInputStream(stream))

        /* ClassFile {
         *     u4             magic;
         *     u2             minor_version;
         *     u2             major_version;
         *     u2             constant_pool_count;
         *     cp_info        constant_pool[constant_pool_count-1];
         *     u2             access_flags;
         *     u2             this_class;
         *     u2             super_class;
         *     u2             interfaces_count;
         *     u2             interfaces[interfaces_count];
         *     u2             fields_count;
         *    field_info     fields[fields_count];
         *     u2             methods_count;
         *     method_info    methods[methods_count];
         *     u2             attributes_count;
         *     attribute_info attributes[attributes_count];
         * }
         */
        fun from(stream: DataInputStream): ClassFile {
            val magic = stream.readInt()
            val minorVersion = stream.readUnsignedShort().toUShort()
            val majorVersion = stream.readUnsignedShort().toUShort()

            val constantPoolCount = stream.readUnsignedShort()
            val constantPool = ConstantInfo.parseConstantPool(stream, constantPoolCount - 1)
            ConstantInfo.resolveConstantPoolReferences(constantPool)

            val accessFlags = stream.readUnsignedShort().toUShort()
            val thisClass = stream.readUnsignedShort().toUShort()
            val superClass = stream.readUnsignedShort().toUShort()

            val interfacesCount = stream.readUnsignedShort().toUShort()
            val interfaces: List<UShort> = readUnsignedShorts(stream, interfacesCount)

            val fieldsCount = stream.readUnsignedShort().toUShort()
            val fields: List<FieldInfo> = FieldInfo.parseFields(stream, fieldsCount, constantPool)

            val methodsCount = stream.readUnsignedShort().toUShort()
            val methods: List<MethodInfo> = MethodInfo.parseMethods(stream, methodsCount, constantPool)

            val attributesCount = stream.readUnsignedShort().toUShort()
            val attributes: List<AttributeInfo> = AttributeInfo.parseAttributes(stream, attributesCount, constantPool)

            return ClassFile(
                magic,
                minorVersion,
                majorVersion,
                constantPool,
                accessFlags,
                thisClass,
                superClass,
                interfaces,
                fields,
                methods,
                attributes
            )
        }

        private fun readUnsignedShorts(stream: DataInputStream, interfacesCount: UShort): List<UShort> {
            TODO("Not yet implemented")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClassFile

        if (magic != other.magic) return false
        if (minorVersion != other.minorVersion) return false
        if (majorVersion != other.majorVersion) return false
        if (constantPool != other.constantPool) return false
        if (accessFlags != other.accessFlags) return false
        if (thisClass != other.thisClass) return false
        if (superClass != other.superClass) return false
        if (interfaces != other.interfaces) return false
        if (fields != other.fields) return false
        if (methods != other.methods) return false
        if (attributes != other.attributes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = magic
        result = 31 * result + minorVersion.hashCode()
        result = 31 * result + majorVersion.hashCode()
        result = 31 * result + constantPool.hashCode()
        result = 31 * result + accessFlags.hashCode()
        result = 31 * result + thisClass.hashCode()
        result = 31 * result + superClass.hashCode()
        result = 31 * result + interfaces.hashCode()
        result = 31 * result + fields.hashCode()
        result = 31 * result + methods.hashCode()
        result = 31 * result + attributes.hashCode()
        return result
    }


}