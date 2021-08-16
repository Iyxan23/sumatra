package com.iyxan23.sumatra.classfile

// https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html
data class ClassFile(
    val magic: Int,
    val minorVersion: UShort,
    val majorVersion: UShort,
    val constantPoolCount: UShort,
)