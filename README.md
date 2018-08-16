# WanAndroid
# MVP+RxJava+Retrofit+Arouter+模块化 的练手项目
* 1、为何写这个项目：
> 在此前MVP分支基础上，实现模块化开发，目的是走一边模块化开发的流程，熟悉各个步骤要做的事及模块化开发过程中遇到的坑及解决办法

* 2、通过这个项目我们能学到什么：   
> a、了解模块化开发大致流程   
b、了解到ARouter在跨模块中的用法  
c、了解模块化开发会遇到哪些问题  
d、动手实现适合自己的模块化开发架构  
 

## 如有问题可以提issue或加群讨论
<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=33dacdd367ca0b5a9ba96a196a6658666b442b3ec528850e377d50f3d607f26b"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="STV&amp;RxHttp交流群" title="STV&amp;RxHttp交流群"></a>

或者手动加QQ群：688433795

### 架构图

<img src="http://osnoex6vf.bkt.clouddn.com/wan_android_project.png"/>

<img src="http://osnoex6vf.bkt.clouddn.com/wan_android_moduleframework.png"/>


### 踩坑之路
#### 1、ARouter模块化遇到的问题

* 在每个module中定义各个module中路由url的时候一级路径必须相同，最好以module名字开头，例如：
```
    String moduleArticleUserCollectActivityPath = "/module_article/collect/activity";
    String moduleArticleSearchResultFragmentPath = "/module_article/search/result/fragment";

    String moduleLoginLoginActivityPath = "/module_login/login/activity";

    String moduleUserMineFragmentPath = "/module_user/mine/fragment";
    String moduleUserAboutActivityPath = "/module_user/about/activity";

```
* 尽量把ARouter相关的定义写在公共module中，便于其他模块引用
* 如何引入ARouter

```
   //在base里边添加依赖
   
   //module_base添加依赖
   api 'com.alibaba:arouter-api:1.4.0'
   annotationProcessor 'com.alibaba:arouter-compiler:1.2.1'


   //在各个module中还是要添加ARouter的注解依赖
   
   //module_xxx 添加ARouter的注解依赖
   annotationProcessor 'com.alibaba:arouter-compiler:1.2.1'


   //同时不要忘记在所有module中添加如下配置
   
   javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }

```

#### 2、butterknife在模块化遇到的问题
* butterknife在module中使用的时候，当module作为library使用的时候使用R2代替原来的R可以正常使用，而且是手动替换R为R2,这是butterknife作者给出的解决办法，但是当module作为独立工程运行的使用就不能使用R2了，如果还自己重新替换原来的R2为R,可想而知这是很大的工作量，所以如果使用模块化开发的话还是放弃使用butterknife吧，直接使用插件生成FindviewById就行（也咨询过一些大厂的开发人员，他们竟然从来不使用butterknife...）
* 另外如果你在模块化中使用butterknife有以上问题的解决方案的话，欢迎提出一起讨论

#### 3、点击事件id的判断在模块化遇到的问题
* 不能使用switch了  要是有if   else if  进行判断
<img src="http://osnoex6vf.bkt.clouddn.com/id_switch_error.png"/>

#### 4、根据不同模式加载不同AndroidManifest遇到的问题
* 如果你是一般的使用方法，请把Manifest改成小写manifest即可
```
    sourceSets {
        main {
            if(isModuleUserBuildAsApp.toBoolean()){
                Manifest.srcFile 'src/main/java/com/allen/user/debug/AndroidManifest.xml'
            }else{
                Manifest.srcFile 'src/main/AndroidManifest.xml'
                //集成开发模式下排除debug文件夹中的所有Java文件
                java {
                    exclude 'debug/**'
                }
            }
        }
    }
```
```
//编译报错
No signature of method: static org.gradle.api.java.archives.Manifest.srcFile() is applicable for argument types: (java.lang.String) values: [src/main/debug/AndroidManifest.xml]
```




