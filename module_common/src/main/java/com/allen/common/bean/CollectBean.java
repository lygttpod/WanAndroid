package com.allen.common.bean;

import java.util.List;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/07/31
 *      desc    :
 *      version : 1.0
 * </pre>
 */
public class CollectBean {

    /**
     * curPage : 1
     * datas : [{"author":"腾讯Bugly","chapterId":17,"chapterName":"ContentProvider","courseId":13,"desc":"","envelopePic":"","id":19017,"link":"https://mp.weixin.qq.com/s/jhVzFa6DatRNK9anuDoSUA","niceDate":"26分钟前","origin":"","originId":1323,"publishTime":1533022070000,"title":"Android 7.0中ContentProvider实现原理","userId":7852,"visible":0,"zan":0},{"author":"CrazyCodeBoy","chapterId":17,"chapterName":"ContentProvider","courseId":13,"desc":"","envelopePic":"","id":19016,"link":"http://www.jianshu.com/p/ad8c066e9166","niceDate":"26分钟前","origin":"","originId":1135,"publishTime":1533022067000,"title":"INSTALL FAILED CONFLICTING PROVIDER问题完美解决方案","userId":7852,"visible":0,"zan":0},{"author":"jjlanbupt","chapterId":10,"chapterName":"Activity","courseId":13,"desc":"","envelopePic":"","id":19014,"link":"https://www.jianshu.com/p/f0e53e770e32","niceDate":"27分钟前","origin":"","originId":3205,"publishTime":1533022028000,"title":"你可能不知道的Activity启动的诡异现象探索","userId":7852,"visible":0,"zan":0},{"author":"豆沙包67","chapterId":10,"chapterName":"Activity","courseId":13,"desc":"","envelopePic":"","id":19013,"link":"https://mp.weixin.qq.com/s/-5lyASIaSFV6wG3wfMS9Yg","niceDate":"28分钟前","origin":"","originId":2036,"publishTime":1533021989000,"title":"剖析Activity、Window、ViewRootImpl和View之间的关系","userId":7852,"visible":0,"zan":0},{"author":" DK_BurNIng","chapterId":10,"chapterName":"Activity","courseId":13,"desc":"","envelopePic":"","id":19012,"link":"https://juejin.im/post/5a6fd7b86fb9a01ca47ac6e8","niceDate":"28分钟前","origin":"","originId":2456,"publishTime":1533021985000,"title":"深入理解 Activty 加载速度优化","userId":7852,"visible":0,"zan":0},{"author":"zejian_","chapterId":15,"chapterName":"Service","courseId":13,"desc":"","envelopePic":"","id":19011,"link":"https://blog.csdn.net/javazejian/article/details/52709857","niceDate":"29分钟前","origin":"","originId":2824,"publishTime":1533021927000,"title":"关于Android Service真正的完全详解，你需要知道的一切","userId":7852,"visible":0,"zan":0},{"author":"wuyr","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"思路:\r\n我们的SurfaceView里面有一个Rect二维数组, 用来存放这些矩形, 小猪离开手指之后, 就开始从小猪当前所在的矩形,用广度优先遍历, 找到一条最短的路径, 然后再根据这条路径在Rect数组中找到对应的矩形, 最后根据这些对应的矩形的坐标来确定出Path.","envelopePic":"http://www.wanandroid.com/blogimgs/4002c74f-49e5-4526-99a7-571d80d7127a.png","id":19001,"link":"http://www.wanandroid.com/blog/show/2228","niceDate":"54分钟前","origin":"","originId":3149,"publishTime":1533020404000,"title":"《捉小猪》是一款基于SurfaceView的Android休闲小游戏.","userId":7852,"visible":0,"zan":0},{"author":"guanpj","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"API 来自官网 https://readhub.me，采用 MVP + Dagger 架构进行开发，网络请求使用 Retrofit + RxJava 2 框架，数据库存储使用 Room + RxJava 2 框架。项目模块不多，但是麻雀虽小五脏俱全。适合 Dagger 、RxJava、Room 等框架的入门学习，没什么意外的话会一直维护。","envelopePic":"http://www.wanandroid.com/blogimgs/251b4ff8-5f8c-4435-aa7f-baf404ffa273.png","id":19000,"link":"http://www.wanandroid.com/blog/show/2249","niceDate":"55分钟前","origin":"","originId":3188,"publishTime":1533020356000,"title":"目前功能最完整的 Readhub 客户端","userId":7852,"visible":0,"zan":0},{"author":"香脆的大鸡排","chapterId":224,"chapterName":"基础概念","courseId":13,"desc":"","envelopePic":"","id":18981,"link":"http://www.jianshu.com/p/56fd03f1aaae","niceDate":"1小时前","origin":"","originId":1104,"publishTime":1533018012000,"title":"Android ADB命令?这一次我再也不死记了","userId":7852,"visible":0,"zan":0},{"author":"h080294","chapterId":224,"chapterName":"adb","courseId":13,"desc":"","envelopePic":"","id":18980,"link":"http://www.jianshu.com/p/203eb5f08761","niceDate":"1小时前","origin":"","originId":1290,"publishTime":1533018010000,"title":"Android测试 adb常用命令","userId":7852,"visible":0,"zan":0},{"author":"JerryloveEmily","chapterId":182,"chapterName":"JNI编程","courseId":13,"desc":"","envelopePic":"","id":18977,"link":"https://juejin.im/post/5b5b16535188251afe7b99e5","niceDate":"1小时前","origin":"","originId":3190,"publishTime":1533017053000,"title":"JNI系列之入门Hello JNI C（一）","userId":7852,"visible":0,"zan":0},{"author":"CarGuo","chapterId":371,"chapterName":"Flutter项目","courseId":13,"desc":"超完整的Flutter项目，功能丰富，适合学习和日常使用，已有同款开源React Native、Weex版本。旨在更好的日常管理和维护个人Github。同款Weex版本 ： https://github.com/CarGuo/GSYGithubAppWeex 、同款React Native版本 ： https://github.com/CarGuo/GSYGithubApp。","envelopePic":"http://www.wanandroid.com/blogimgs/f5012c76-735c-4128-a820-cde2ea20bfee.png","id":18976,"link":"http://www.wanandroid.com/blog/show/2251","niceDate":"1小时前","origin":"","originId":3194,"publishTime":1533017045000,"title":"超完整开源Flutter Github客户端App GSYGithubAppFlutter","userId":7852,"visible":0,"zan":0},{"author":"轶匠","chapterId":399,"chapterName":"抓包","courseId":13,"desc":"","envelopePic":"","id":18975,"link":"https://www.jianshu.com/p/276bb5a49e59","niceDate":"1小时前","origin":"","originId":3192,"publishTime":1533017043000,"title":"Charles修改返回值和返回状态","userId":7852,"visible":0,"zan":0},{"author":"CodingEnding","chapterId":387,"chapterName":"对话框","courseId":13,"desc":"PopupLayout是通用弹出布局辅助库，允许开发者从顶部、底部、左侧、右侧和中心这五个位置弹出自己指定的View，此外还提供圆角和动画特性。","envelopePic":"http://www.wanandroid.com/blogimgs/4838a40c-f56c-4a0e-bcb9-3c1aecb45c21.png","id":18974,"link":"http://www.wanandroid.com/blog/show/2252","niceDate":"1小时前","origin":"","originId":3195,"publishTime":1533017012000,"title":"PopupLayout 通用弹出布局辅助库","userId":7852,"visible":0,"zan":0},{"author":"vvengzt","chapterId":97,"chapterName":"音视频","courseId":13,"desc":"","envelopePic":"","id":18972,"link":"https://www.jianshu.com/p/1f78c4211ab7","niceDate":"1小时前","origin":"","originId":3200,"publishTime":1533016961000,"title":"Android音视频开发初探之AudioRecord与AudioTrack完成音频采集与播放","userId":7852,"visible":0,"zan":0},{"author":"光源_Android","chapterId":295,"chapterName":"混淆","courseId":13,"desc":"","envelopePic":"","id":18968,"link":"https://www.jianshu.com/p/158aa484da13","niceDate":"1小时前","origin":"","originId":3199,"publishTime":1533016538000,"title":"写给Android开发者的混淆使用手册","userId":7852,"visible":0,"zan":0},{"author":"LillteZheng","chapterId":400,"chapterName":"ViewPager","courseId":13,"desc":"这个一个 viewpager 工具类，能够帮你快速实现导航栏轮播图，app引导页，viewpager + fragment；内置多种tab指示器，让你告别 viewpager 的繁琐操作，专注逻辑功能","envelopePic":"http://www.wanandroid.com/blogimgs/ea5cac16-f9b7-4b8d-8812-d215a00f2853.png","id":18953,"link":"http://www.wanandroid.com/blog/show/2258","niceDate":"4小时前","origin":"","originId":3204,"publishTime":1533008525000,"title":"ViewPager 工具类，快速实现轮播图，app引导页，tab标签  ViewPagerHelper","userId":7852,"visible":0,"zan":0},{"author":"Mobcase","chapterId":358,"chapterName":"项目基础功能","courseId":13,"desc":" 轻量级的Android图片压缩插件，采用Hook Gradle Task的方式，压缩module，jar，aar中所有的png图片并有策略的转换成webp（已考虑各种情况），全自动压缩无需认为干涉，简单集成，所有开关可配置。已应用在大型商业App中","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","id":18321,"link":"http://www.wanandroid.com/blog/show/2247","niceDate":"2018-07-25","origin":"","originId":3179,"publishTime":1532505705000,"title":"McImage Android端自动化图片压缩插件","userId":7852,"visible":0,"zan":0},{"author":"Jdqm","chapterId":61,"chapterName":"Android测试相关","courseId":13,"desc":"","envelopePic":"","id":18319,"link":"https://www.jianshu.com/p/aa51a3e007e2","niceDate":"2018-07-25","origin":"","originId":3180,"publishTime":1532505534000,"title":"Android单元测试只看这一篇就够了","userId":7852,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 19
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * author : 腾讯Bugly
         * chapterId : 17
         * chapterName : ContentProvider
         * courseId : 13
         * desc :
         * envelopePic :
         * id : 19017
         * link : https://mp.weixin.qq.com/s/jhVzFa6DatRNK9anuDoSUA
         * niceDate : 26分钟前
         * origin :
         * originId : 1323
         * publishTime : 1533022070000
         * title : Android 7.0中ContentProvider实现原理
         * userId : 7852
         * visible : 0
         * zan : 0
         */

        private String author;
        private int chapterId;
        private String chapterName;
        private int courseId;
        private String desc;
        private String envelopePic;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private int originId;
        private long publishTime;
        private String title;
        private int userId;
        private int visible;
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
