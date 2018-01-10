package com.jiakiayang.plugin.classhandler

import com.jiakiayang.plugin.classhandler.timelog.TimeLogTask
import com.jiakiayang.plugin.classhandler.timelog.TimeLogTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class ClassHandlerPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create('classHandler', ClassHandlerPluginConfig)

        /*project.task('timelog') << {
            boolean timeLogEnable = project['classHandler'].enable
            println "Hello World, timelog: " + timeLogEnable

            if (!timeLogEnable) {
                return
            }

            android.registerTransform(new TimeLogTransform(project))
        }*/

        project.getTasks().create("timeLog", TimeLogTask.class)
        project.android.registerTransform(new TimeLogTransform(project))
    }
}