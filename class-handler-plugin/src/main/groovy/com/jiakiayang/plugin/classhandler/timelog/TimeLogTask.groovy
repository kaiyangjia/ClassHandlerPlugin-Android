package com.jiakiayang.plugin.classhandler.timelog

import com.jiakiayang.plugin.classhandler.utils.InjectUtils;
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

/**
 * Created by jia on 2018/1/2.
 */

public class TimeLogTask extends DefaultTask {

    @TaskAction
    public onAction() {
        println("TimeLog onAction")

        classesPath = 'app\\build\\intermediates\\classes\\debug\\com\\jiakaiyang\\debughelper'

        InjectUtils.injectDir(classesPath
                , "com\\jiakaiyang"
                , "android.util.Log.i(TAG, \"onCreate: \" + System.currentTimeMillis());")

    }
}
