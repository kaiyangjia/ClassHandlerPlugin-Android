package com.jiakiayang.plugin.classhandler

import org.gradle.api.Plugin
import org.gradle.api.Project

class ClassHandlerPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create('classHandler', ClassHandlerPluginConfig)

        project.task('timelog') << {
            boolean timeLogEnable = project['classHandler'].enable
            println "Hello World, timelog: " + timeLogEnable

            if (!timeLogEnable) {
                return
            }
        }
    }
}