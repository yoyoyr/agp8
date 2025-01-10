package com.asm.asm_annotation

import org.objectweb.asm.Opcodes


object AsmMethodOpcodes {

    const val INVOKESTATIC = Opcodes.INVOKESTATIC
    const val INVOKEVIRTUAL = Opcodes.INVOKEVIRTUAL
    const val INVOKESPECIAL = Opcodes.INVOKESPECIAL
    const val INVOKEDYNAMIC = Opcodes.INVOKEDYNAMIC
    const val INVOKEINTERFACE = Opcodes.INVOKEINTERFACE
}



object AsmType {

    const val REPLACE = 0
    const val INSERT_BEFORE = 1
    const val INSERT_AFTER = 2
}