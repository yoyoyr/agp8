package com.asm.plugin.method_time

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter


//int api, MethodVisitor methodVisitor, int access, String name, String descriptor
open class MethodTimeMethodVisitor(
    val className: String,
    val fullClassName: String,
    methodVisitor: MethodVisitor,
    access: Int,
    name: String?,
    descriptor: String?,
) : AdviceAdapter(Opcodes.ASM9, methodVisitor, access, name, descriptor) {

    private var timeLocalIndex = 0

    override fun onMethodEnter() {


        if (shouldIntercept(className, name)) {
//            logger.println("${className}.$name")
            mv.visitMethodInsn(
                INVOKESTATIC,
                "com/asm/agp8/util/TextUtil",
                "methodIn",
                "()V",
                false
            )

            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            timeLocalIndex = newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, timeLocalIndex);
        }

        super.onMethodEnter()
    }

    override fun onMethodExit(opcode: Int) {

        if (shouldIntercept(className, name)) {
            mv.visitVarInsn(LLOAD, timeLocalIndex)
            mv.visitLdcInsn("$className.$name")

            mv.visitMethodInsn(
                INVOKESTATIC,
                "com/asm/agp8/util/TextUtil",
                "methodOut",
                "(JLjava/lang/String;)V",
                false
            )
        }
        super.onMethodExit(opcode)
    }

    private fun shouldIntercept(className: String, name: String): Boolean {

        //指定类加方法全名
//        ignoreFullMethod.forEach {
//            if (it == "$className.$name") {
//                return false
//            }
//        }

        //匹配方法名即可，不指定类
        ignoreMethodName.forEach {
            if (it == name) {
                return false
            }
        }

        //指定类下的指定方法
        ignoreMethodStartWith.forEach {
            if (name.startsWith(it)) {
                return false
            }
        }

        return true
    }


    private val ignoreMethodName = mutableListOf<String>().apply {
//        add("onSensorChanged")//重力感应回调

        add("<init>")//构造方法
        add("<clinit>")//静态构造方法
    }

    private val ignoreMethodStartWith = mutableListOf<String>().apply {
        add("access\$")
    }

}