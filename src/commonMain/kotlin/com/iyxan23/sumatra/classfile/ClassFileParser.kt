package com.iyxan23.sumatra.classfile

import com.iyxan23.sumatra.classfile.models.*
import java.io.DataInputStream
import java.io.InputStream

// https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html
object ClassFileParser {
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
    fun parse(classInputStream: InputStream): ClassFile {
        val stream = DataInputStream(classInputStream)

        val magic = stream.readInt()
        val minorVersion = stream.readUnsignedShort().toUShort()
        val majorVersion = stream.readUnsignedShort().toUShort()
        val constantPoolCount = stream.readUnsignedShort()
        val constantPool: Array<ConstantInfo> = parseConstantPool(stream, constantPoolCount - 1)
        resolveConstantPoolReferences(constantPool)
        val accessFlags = stream.readUnsignedShort().toUShort()
        val thisClass = stream.readUnsignedShort().toUShort()
        val superClass = stream.readUnsignedShort().toUShort()
        val interfacesCount = stream.readUnsignedShort().toUShort()
        val interfaces: Array<UShort> = readUnsignedShorts(stream, interfacesCount)
        val fieldsCount = stream.readUnsignedShort().toUShort()
        val fields: Array<FieldInfo> = parseFields(stream, fieldsCount, constantPool)
        val methodsCount = stream.readUnsignedShort().toUShort()
        val methods: Array<MethodInfo> = parseMethods(stream, methodsCount, constantPool)
        val attributesCount = stream.readUnsignedShort().toUShort()
        val attributes: Array<AttributeInfo> = parseAttributes(stream, attributesCount, constantPool)

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

    private fun parseConstantPool(stream: DataInputStream, constantPoolCount: Int): Array<ConstantInfo> {
        val constantInfos = ArrayList<ConstantInfo>()
        var skip = false

        repeat (constantPoolCount) {
            if (skip) {
                skip = !skip
                return@repeat
            }

            val constInfo = ConstantInfo.parseConstantInfo(stream)
            constantInfos.add(constInfo)

            // 8byte consts like long and double uses two entries in the constant pool for no reason, and its unused..
            // so we need to skip it
            // check: https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.4.5
            if (constInfo.tag == ConstantInfo.Type.LONG || constInfo.tag == ConstantInfo.Type.DOUBLE) {
                skip = true
            }
        }

        return constantInfos.toTypedArray()
    }

    private fun resolveConstantPoolReferences(constantPool: Array<ConstantInfo>) {
        TODO("Not yet implemented")
    }

    private fun readUnsignedShorts(stream: DataInputStream, interfacesCount: UShort): Array<UShort> {
        TODO("Not yet implemented")
    }

    private fun parseFields(
        stream: DataInputStream,
        fieldsCount: UShort,
        constantPool: Array<ConstantInfo>
    ): Array<FieldInfo> {
        TODO("Not yet implemented")
    }

    private fun parseMethods(
        stream: DataInputStream,
        methodsCount: UShort,
        constantPool: Array<ConstantInfo>
    ): Array<MethodInfo> {
        TODO("Not yet implemented")
    }

    private fun parseAttributes(
        stream: DataInputStream,
        attributesCount: UShort,
        constantPool: Array<ConstantInfo>
    ): Array<AttributeInfo> {
        TODO("Not yet implemented")
    }
}
