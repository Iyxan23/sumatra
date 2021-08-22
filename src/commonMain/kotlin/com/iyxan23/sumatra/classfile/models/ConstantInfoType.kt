package com.iyxan23.sumatra.classfile.models

enum class ConstantInfoType(private val tag: UByte) {
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
        fun findTag(tag: UByte): ConstantInfoType? =
            values().find { it.tag == tag }
    }
}