package com.asm.plugin.method_time;

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.ClassVisitor
import groovyjarjarasm.asm.Opcodes

class MethodTimeVisitor(nextClassVisitor: ClassVisitor) :
    ClassVisitor(Opcodes.ASM9, nextClassVisitor) {

    private var className: String? = null

    private val shouldInterceptPKG = mutableListOf<String>().apply {
        add("com/asm/agp8/ui")
        add("com/commsource")
        add("com/meitu")
        add("com/pixocial/library/albumkit")//相册工具
    }


    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        className = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor? {
        if (cv != null) {

            var shouldIntercept = false

            run ForBreak@{
                shouldInterceptPKG.forEach {
                    if (className?.startsWith(it) == true) {
                        shouldIntercept = true
                        return@ForBreak
                    }
                }
            }


            if (shouldIntercept) {

                val index = className!!.lastIndexOf("/") + 1

                return MethodTimeMethodVisitor(
                    className!!.substring(index, className!!.length),
                    className!!, //fullname  调试用
                    cv.visitMethod(access, name, descriptor, signature, exceptions),
                    access,
                    name,
                    descriptor
                )
            } else {
                return cv.visitMethod(access, name, descriptor, signature, exceptions)
            }
        } else {
            return null
        }
//        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }
}
