package com.asm.plugin.method_time

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import org.objectweb.asm.ClassVisitor

abstract class MethodTimeFactory : AsmClassVisitorFactory<MethodTimeParameter> {

    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        return MethodTimeVisitor(nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return true
    }

}