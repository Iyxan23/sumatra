package com.iyxan23.sumatra.classfile.models

import java.io.DataInputStream

object ConstantInfoParser {
    /* cp_info {
     *   u1 tag;
     *   u1 info[];
     * }
     */
    fun parseConstantInfo(stream: DataInputStream): ConstantInfo {
        val tag: UByte
        return when (ConstantInfoType.findTag(stream.readUnsignedByte().toUByte().also { tag = it })) {
            /* CONSTANT_Utf8_info {
             *    u1 tag;
             *    u2 length;
             *    u1 bytes[length];
             * }
             */
            ConstantInfoType.UTF8 -> {
                val length = stream.readUnsignedShort()
                val arr = ByteArray(length)
                stream.read(arr)

                ConstantInfo.Utf8(length.toUShort(), arr)
            }

            /* CONSTANT_Integer_info {
             *     u1 tag;
             *     u4 bytes;
             * }
             */
            ConstantInfoType.INTEGER -> {
                ConstantInfo.Integer(stream.readInt())
            }

            /* CONSTANT_Float_info {
             *     u1 tag;
             *     u4 bytes;
             * }
             */
            ConstantInfoType.FLOAT -> {
                @Suppress("RemoveRedundantQualifierName") // <- false positive
                (ConstantInfo.Float(Float.fromBits(stream.readInt())))
            }

            /* CONSTANT_Long_info {
             *    u1 tag;
             *    u4 high_bytes;
             *    u4 low_bytes;
             * }
             */
            ConstantInfoType.LONG -> {
                ConstantInfo.Long(stream.readLong())
            }

            /* CONSTANT_Double_info {
             *     u1 tag;
             *     u4 high_bytes;
             *     u4 low_bytes;
             * }
             */
            ConstantInfoType.DOUBLE -> {
                ConstantInfo.Double(stream.readDouble())
            }

            /* CONSTANT_Class_info {
             *     u1 tag;
             *     u2 name_index;
             * }
             */
            ConstantInfoType.CLASS -> {
                ConstantInfo.Class(stream.readUnsignedShort().toUShort())
            }

            /* CONSTANT_String_info {
             *     u1 tag;
             *     u2 string_index;
             * }
             */
            ConstantInfoType.STRING -> {
                ConstantInfo.String(stream.readUnsignedShort().toUShort())
            }

            /* CONSTANT_Fieldref_info {
             *     u1 tag;
             *     u2 class_index;
             *     u2 name_and_type_index;
             * }
             */
            ConstantInfoType.FIELD_REF -> {
                ConstantInfo.FieldRef(
                    stream.readUnsignedShort().toUShort(),
                    stream.readUnsignedShort().toUShort(),
                )
            }

            /* CONSTANT_Methodref_info {
             *     u1 tag;
             *     u2 class_index;
             *     u2 name_and_type_index;
             * }
             */
            ConstantInfoType.METHOD_REF -> {
                ConstantInfo.MethodRef(
                    stream.readUnsignedShort().toUShort(),
                    stream.readUnsignedShort().toUShort(),
                )
            }

            /* CONSTANT_InterfaceMethodref_info {
             *     u1 tag;
             *     u2 class_index;
             *     u2 name_and_type_index;
             * }
             */
            ConstantInfoType.INTERFACE_METHOD_REF -> {
                ConstantInfo.InterfaceMethodRef(
                    stream.readUnsignedShort().toUShort(),
                    stream.readUnsignedShort().toUShort(),
                )
            }

            /* CONSTANT_NameAndConstantInfoType_info {
             *     u1 tag;
             *     u2 name_index;
             *     u2 descriptor_index;
             * }
             */
            ConstantInfoType.NAME_AND_TYPE -> {
                ConstantInfo.NameAndConstantInfoType(
                    stream.readUnsignedShort().toUShort(),
                    stream.readUnsignedShort().toUShort(),
                )
            }

            /* CONSTANT_MethodHandle_info {
             *     u1 tag;
             *     u1 reference_kind;
             *     u2 reference_index;
             * }
             */
            ConstantInfoType.METHOD_HANDLE -> {
                ConstantInfo.MethodHandle(
                    stream.readUnsignedByte().toUByte(),
                    stream.readUnsignedShort().toUShort()
                )
            }

            /* CONSTANT_MethodConstantInfoType_info {
             *     u1 tag;
             *     u2 descriptor_index;
             * }
             */
            ConstantInfoType.METHOD_TYPE -> {
                ConstantInfo.MethodConstantInfoType(stream.readUnsignedShort().toUShort())
            }

            /* CONSTANT_Dynamic_info {
             *     u1 tag;
             *     u2 bootstrap_method_attr_index;
             *     u2 name_and_type_index;
             * }
             */
            ConstantInfoType.DYNAMIC -> {
                ConstantInfo.Dynamic(
                    stream.readUnsignedShort().toUShort(),
                    stream.readUnsignedShort().toUShort(),
                )
            }

            /* CONSTANT_InvokeDynamic_info {
             *     u1 tag;
             *     u2 bootstrap_method_attr_index;
             *     u2 name_and_type_index;
             * }
             */
            ConstantInfoType.INVOKE_DYNAMIC -> {
                ConstantInfo.InvokeDynamic(
                    stream.readUnsignedShort().toUShort(),
                    stream.readUnsignedShort().toUShort(),
                )
            }

            /* CONSTANT_Module_info {
             *     u1 tag;
             *     u2 name_index;
             * }
             */
            ConstantInfoType.MODULE -> {
                ConstantInfo.Module(stream.readUnsignedShort().toUShort())
            }

            /* CONSTANT_Package_info {
             *     u1 tag;
             *     u2 name_index;
             * }
             */
            ConstantInfoType.PACKAGE -> {
                ConstantInfo.Package(stream.readUnsignedShort().toUShort())
            }

            null -> {
                throw UnsupportedOperationException(
                    "Unknown ConstantInfo with the tag $tag, maybe this is class is compiled in a newer " +
                            "version of java's bytecode specification? this JVM implementation supports Java SE 11"
                )
            }
        }
    }
}