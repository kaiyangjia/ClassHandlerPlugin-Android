package com.jiakiayang.plugin.classhandler.utils

import javassist.ClassPool
import javassist.CtClass
import javassist.CtConstructor
import javassist.CtMethod;

/**
 * Created by jia on 2017/12/28.
 *
 * 作者：huachao1001
 链接：https://www.jianshu.com/p/417589a561da
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

public class InjectUtils {
    private static ClassPool pool = ClassPool.getDefault()

    /**
     * inject class Source Code
     * @param path
     * @param packageName
     * @param injectStr
     */
    public static void injectDir(String path, String packageName, String injectStr) {
        pool.appendClassPath(path)
        File dir = new File(path)
        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->

                String filePath = file.absolutePath
                //确保当前文件是class文件，并且不是系统自动生成的class文件
                if (filePath.endsWith(".class")
                        && !filePath.contains('R$')
                        && !filePath.contains('R.class')
                        && !filePath.contains("BuildConfig.class")) {
                    // 判断当前目录是否是在我们的应用包里面
                    int index = filePath.indexOf(packageName)
                    boolean isMyPackage = index != -1
                    if (isMyPackage) {
                        int end = filePath.length() - 6 // .class = 6
                        String className = filePath.substring(index, end)
                                .replace('\\', '.').replace('/', '.')
                        //开始修改class文件
                        CtClass c = pool.getCtClass(className)

                        if (c.isFrozen()) {
                            c.defrost()
                        }

                        CtMethod[] methods = c.getDeclaredMethods()

                        methods.each {
                            CtMethod method ->
                                method.insertBefore(injectStr)
                        }

/*                        CtConstructor[] cts = c.getDeclaredConstructors()
                        if (cts == null || cts.length == 0) {
                            //手动创建一个构造函数
                            CtConstructor constructor = new CtConstructor(new CtClass[0], c)
                            constructor.insertBeforeBody(injectStr)
                            c.addConstructor(constructor)
                        } else {
                            cts[0].insertBeforeBody(injectStr)
                        }*/
                        c.writeFile(path)
                        c.detach()
                    }
                }
            }
        }
    }
}
