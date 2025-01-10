package com.asm.plugin

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
//import com.asm.plugin.method_hook.TransformBytecodeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.asm.plugin.method_time.MethodTimeFactory
import org.gradle.api.tasks.compile.JavaCompile

abstract class AsmPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("我是插件")

////        // 创建转换任务
////        val transformTask = project.tasks.create("transformBytecode", TransformBytecodeTask::class.java)
////
////        // 将转换任务设置为在 classes 任务之前运行
////        project.tasks.withType(JavaCompile::class.java) { compileTask ->
////            compileTask.dependsOn(transformTask)
////        }
//
        val androidComponents = project.extensions.getByType(
            AndroidComponentsExtension::class.java
        )
//
        androidComponents.onVariants(androidComponents.selector().all()) { variant ->
            variant.instrumentation.transformClassesWith(
                MethodTimeFactory::class.java,
                InstrumentationScope.ALL
            ){

            }
            variant.instrumentation.setAsmFramesComputationMode(
                FramesComputationMode.COPY_FRAMES
            )
        }
        println("插件插入完成")
    }
}
