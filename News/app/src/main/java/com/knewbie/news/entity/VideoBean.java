package com.knewbie.news.entity;

import java.util.ArrayList;
import java.util.List;

public class VideoBean {
    /*
    * 使用到的量
    * id：                 VideoBean.bodyList.infoId
    * 标题：               VideoBean.bodyList.memberItem.name
    * 来源（作者）：       VideoBean.bodyList.weMedia.name
    * 时间：               VideoBean.systemTime
    * 视频网址：           VideoBean.bodyList. ... videoFiles
    * 图片网址：           VideoBean.bodyList.memberItem.image
    *
    * */

    public VideoBean() {
        bodyList = new ArrayList<>();
        header = new ArrayList<>();
    }

    /**
     * relate :
     * title :
     * relateDes :
     * systemTime : 1585477392487
     * header : []
     * bodyList : [{"title":"美国要求安理会明确写入\u201c新冠病毒源于中国\u201d遭拒","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/62F384E4478518D0D747438D38DBCDA6F7FD432A_size16_w320_h200.jpeg"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"1","infoId":"a0403dd0-a7d7-40d5-8860-53977f193b78","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/w200_h200_q100_blur/p0.ifengimg.com/pmop/2016/1222/FCDB9112F716C731245051D931E5B86CBA7A8EC5_size45_w300_h300.png","name":"环球时报","desc":"报道多元世界 解读复杂中国","id":"367951","followed":0},"memberItem":{"ucmsId":"6649809843538370566","duration":138,"image":"https://x0.ifengimg.com/res/2020/62F384E4478518D0D747438D38DBCDA6F7FD432A_size16_w320_h200.jpeg","guid":"a0403dd0-a7d7-40d5-8860-53977f193b78","playTime":"166421","commentNo":"63","shareTimes":"114","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"美国要求安理会决议明确写入\u201c新冠病毒源于中国\u201d遭到拒绝","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002--zuCMvKIUNf3oj6B80Q7b6gERC-_AZpVPSHDb05XSg0c__","cpName":"一点资讯","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"联合国,新型冠状病毒,美国,国际地方新闻","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-102-9987642-081134.mp4","filesize":9215,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-535-066-081134.mp3","filesize":2160,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-102-9987642-081134/index.m3u8","filesize":9215,"spliteTime":"","spliteNo":0,"isSplite":0}]}},{"title":"自取其辱！港媒拿台湾问题碰瓷被世卫官员挂电话","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/cmpp/2020_14/19abd5d2b5ef2ee.png"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"2","infoId":"5a264dec-89ca-4968-b520-eab6f472ccd5","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/w200_h200_q100_blur/p0.ifengimg.com/pmop/2016/1222/FCDB9112F716C731245051D931E5B86CBA7A8EC5_size45_w300_h300.png","name":"环球时报","desc":"报道多元世界 解读复杂中国","id":"367951","followed":0},"memberItem":{"ucmsId":"6649677937593298944","duration":62,"image":"https://x0.ifengimg.com/ucms/2020_13/DE56799464E09E0246B84705155934E68B0A81B0_w640_h360.jpg","guid":"5a264dec-89ca-4968-b520-eab6f472ccd5","playTime":"408293","commentNo":"122","shareTimes":"444","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"20200328","name":"香港记者询问WHO是否会考虑台湾的成员资格 总干事：拜拜","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002zgoJwBlG3YUPGQTHM--5q-_L8EkpJ6CqMxCADOOKfWj6U__","cpName":"自媒体","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"香港,台湾,WHO","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34962191-102-009-224825.mp4","filesize":5625,"spliteTime":"61.547","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34962191-535-066-224825.mp3","filesize":962,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34962191-102-009-224825/index.m3u8","filesize":5625,"spliteTime":"61.547","spliteNo":1,"isSplite":1}]}},{"title":"台高官称\u201c大陆取消ECFA对台影响不大\u201d 岛内名嘴打脸","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/e0.ifengimg.com/12/pmop/2020/0329/F9EB799630B6CF8BC3D67C4AF8CDE4B9305DC0F9_size66_w1280_h720.jpg"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"3","infoId":"ce9f1b9e-53e3-4212-b00c-1dbb23de4f33","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190830/11/wemedia/9ab1ae36df94865e114c5d7cd1318745f749e9c6_size75_w200_h200.png","name":"宝岛近况","desc":"台湾时政热点内容播报！","id":"1536822","followed":0},"memberItem":{"ucmsId":"6649941681758023714","duration":131,"image":"https://e0.ifengimg.com/12/pmop/2020/0329/F9EB799630B6CF8BC3D67C4AF8CDE4B9305DC0F9_size66_w1280_h720.jpg","guid":"ce9f1b9e-53e3-4212-b00c-1dbb23de4f33","playTime":"11972","commentNo":"1","shareTimes":"0","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"台高官称\u201c大陆取消ECFA对台影响不大\u201d，岛内名嘴共同反对","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002tvkyEBCQagIxRVxbuMjL--e2EZOBMhGRPtLsMT6P-_xyQ__","cpName":"今日头条","searchPath":"51-10077","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"新闻资讯,时政评论,大陆","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39790354-102-9987642-164309.mp4","filesize":16330,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39790354-535-066-164309.mp3","filesize":2124,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39790354-102-9987642-164309/index.m3u8","filesize":16330,"spliteTime":"","spliteNo":0,"isSplite":0}]}},{"title":"特朗普要强制隔离 纽约州长怒了:你要宣战？","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/thmaterial/2020_13/9B6C7EA82F2A4F0EA2386187D7A84D18_w1920_h1080.jpg"}],"abstractDesc":"","showType":"picplay","createDate":"","updateDate":"","memberType":"video","itemId":"4","infoId":"e942650a-6a45-4584-a4f9-f892f16e973b","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190418/15/wemedia/158eb2ab003ca4df67e6fab5ddf997bfbfda1f24_size12_w200_h200.png","name":"燃新闻","desc":"有温暖 有深度 有情怀","id":"629445","followed":0},"memberItem":{"ucmsId":"6649907196987457597","duration":39,"image":"https://x0.ifengimg.com/thmaterial/2020_13/9B6C7EA82F2A4F0EA2386187D7A84D18_w1920_h1080.jpg","guid":"e942650a-6a45-4584-a4f9-f892f16e973b","playTime":"140565","commentNo":"5","shareTimes":"19","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"特朗普要强制隔离 纽约州州长：这相当于联邦政府向各州\u201c宣战\u201d","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002M0bALHDgWEZ2RBpNspM9CYzH-_Gg6rFeWFy4HDohW8Uo__","cpName":"官网","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"新闻资讯,社会事件,特朗普,新冠状病毒","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34987044-102-009-151725.mp4","filesize":5945,"spliteTime":"39","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34987044-535-066-151725.mp3","filesize":708,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"curtmp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34987072-102-009-152025.mp4","filesize":3414,"spliteTime":"22","spliteNo":1,"isSplite":1},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34987044-102-009-151725/index.m3u8","filesize":5945,"spliteTime":"39","spliteNo":1,"isSplite":1}]}},{"title":"墨西哥一州长称穷人对新冠病毒免疫 因为确诊的都是富人","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/E02DE8692ACC47635702D2B8ED428E487EC6A8AA_size22_w696_h391.jpeg"}],"abstractDesc":"","showType":"picplay","createDate":"","updateDate":"","memberType":"video","itemId":"5","infoId":"e73f6d58-cfb9-430e-8365-692f8ce2bad5","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/w200_h200_q100_blur/e0.ifengimg.com/02/2019/0610/9C6CBBA12DED79B1DE323284D82DD8DB0B75577B_size5_w120_h120.jpeg","name":"参考视频","desc":"《参考消息》官方视频专区。换个角度看世界，给您带来不一样视频资讯。","id":"1448867","followed":0},"memberItem":{"ucmsId":"6649865988621738057","duration":46,"image":"https://x0.ifengimg.com/thmaterial/2020_13/78DBDDF68B9741CFA655DEA15E72392F_w698_h392.jpg","guid":"e73f6d58-cfb9-430e-8365-692f8ce2bad5","playTime":"44770","commentNo":"31","shareTimes":"11","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"墨西哥州长宣称穷人对新冠病毒免疫 因为确诊的都是富人","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002SwqPSvmMqV5nebZ-_jN-_6ZyGRczzwCrpvgt2DqvMyxqU__","cpName":"今日头条","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"墨西哥,新型冠状病毒,国际地方新闻","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34843469-102-009-164825.mp4","filesize":6440,"spliteTime":"46","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34843469-535-066-164825.mp3","filesize":769,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"curtmp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34964850-102-009-165125.mp4","filesize":3032,"spliteTime":"21","spliteNo":1,"isSplite":1},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34843469-102-009-164825/index.m3u8","filesize":6440,"spliteTime":"46","spliteNo":1,"isSplite":1}]}},{"title":"警方通报云南15岁少女酒店跳楼 当事人：被逼卖淫，拿刀吓我","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/C0176DFA66AEFA684CB2CC8135D740F098209C83_size16_w640_h360.jpeg"}],"abstractDesc":"","showType":"picplay","createDate":"","updateDate":"","memberType":"video","itemId":"6","infoId":"a543629d-e7e8-400e-9d61-e6c850a994d2","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/w200_h200_q100_blur/x0.ifengimg.com/res/2019/A38E6B8FF53F6AAF833FB563F54C53EAFF533BBF_size3_w243_h225.jpeg","name":"凤梨视频","desc":"一网打尽超好看的资讯短视频！","id":"1239861","followed":0},"memberItem":{"ucmsId":"6649887748536676449","duration":64,"image":"https://x0.ifengimg.com/res/2020/C0176DFA66AEFA684CB2CC8135D740F098209C83_size16_w640_h360.jpeg","guid":"a543629d-e7e8-400e-9d61-e6c850a994d2","playTime":"52673","commentNo":"5","shareTimes":"14","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"警方通报云南15岁少女酒店跳楼，当事人：被逼卖淫，拿刀吓我","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002dV21zdKY1xXOLLEpQbjolGoGNQH0qVXvm7em5Aq4HPI__","cpName":"官网","searchPath":"55-10062","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"云南,社会犯罪案件,社会事件","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39787813-102-9987642-130209.mp4","filesize":9835,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39787813-535-066-130209.mp3","filesize":1005,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39787813-102-9987642-130209/index.m3u8","filesize":9835,"spliteTime":"","spliteNo":0,"isSplite":0}]}},{"title":"死亡人数破万 意大利教堂摆满了棺材","imageList":[{"image":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20200329/8/wemedia/22cfa676d6981c95cc66f1aaaa385af649b84917_size438_w640_h360.png"}],"abstractDesc":"","showType":"picplay","createDate":"","updateDate":"","memberType":"video","itemId":"7","infoId":"01b99f98-867a-4abf-83a5-76b5c6fcd180","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20181018/11/wemedia/b1ddc3f6253ee8e7ae56859aef54e42e5f1df15d_size41_w200_h200.png","name":"8斗","desc":"全球热点事件，最新现场视频。","id":"1174133","followed":0},"memberItem":{"ucmsId":"6649644536832213162","duration":53,"image":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20200329/8/wemedia/22cfa676d6981c95cc66f1aaaa385af649b84917_size438_w640_h360.png","guid":"01b99f98-867a-4abf-83a5-76b5c6fcd180","playTime":"78276","commentNo":"7","shareTimes":"7","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"意大利死亡人数破万居全球第一！教堂摆满棺材 物资濒临崩溃","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002MBoxpbpqefDGZz8fU5gl5j9--PJQ0WtJRaLN4MExm5H8__","cpName":"大风号","searchPath":"51-52","copyright":"原创","simId":"","yvId":"","recommendType":"editor","keyWords":"新闻资讯,意大利,新冠状病毒","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984650-102-9987636-081037.mp4","filesize":10160,"spliteTime":"53.248","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984650-535-066-081037.mp3","filesize":833,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984650-102-9987636-081037/index.m3u8","filesize":10160,"spliteTime":"53.248","spliteNo":1,"isSplite":1}]}},{"title":"","imageList":[],"abstractDesc":"","showType":"survey","createDate":"","updateDate":"","memberType":"survey","itemId":"8","infoId":"survey8","tag":"","weMedia":{},"memberItem":{"surveyId":"16176"}},{"title":"留学生曝隔离点床上有虫子、血迹：没病也得病了","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/ucms/2020_14/6CFDA9C01B6D4F616A47B767D8C9B0950D2CDAE9_w640_h360.jpg"}],"abstractDesc":"","showType":"pic","createDate":"","updateDate":"","memberType":"video","itemId":"9","infoId":"69083727-4cb0-4b21-815e-ef161290ce0a","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20170814/13/wemedia/35a9b9bd72822f569cba9e97bf5341a588551cf0_size30_w200_h200.png","name":"今日视讯","desc":"国内外人情冷暖大事小情一网打尽。","id":"780306","followed":0},"memberItem":{"ucmsId":"6649888749398278144","duration":69,"image":"https://x0.ifengimg.com/ucms/2020_14/6CFDA9C01B6D4F616A47B767D8C9B0950D2CDAE9_w640_h360.jpg","guid":"69083727-4cb0-4b21-815e-ef161290ce0a","playTime":"51893","commentNo":"73","shareTimes":"13","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"20200329","name":"留学生曝隔离点床上有虫子、血迹：没病也得病了","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002U2LReotGndtTmfUIwNQ5if76XvvGNA5RoyeZqQQM50w__","cpName":"一点资讯","searchPath":"55-10062","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"留学生,回国,隔离,隔离环境,新冠疫情,境外输入","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34986186-102-009-125025.mp4","filesize":10614,"spliteTime":"68.886","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34986186-535-066-125025.mp3","filesize":1076,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34986186-102-009-125025/index.m3u8","filesize":10614,"spliteTime":"68.886","spliteNo":1,"isSplite":1}]}},{"title":"钟南山：按照推断 中国没有大量的\u201c无症状感染者\u201d","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/ucms/2020_14/FD0803062552C876BB18097B30DC97333BC25E67_w480_h270.jpg"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"10","infoId":"86d03c98-a45b-46da-9866-5acddddfb17e","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20170814/13/wemedia/35a9b9bd72822f569cba9e97bf5341a588551cf0_size30_w200_h200.png","name":"今日视讯","desc":"国内外人情冷暖大事小情一网打尽。","id":"780306","followed":0},"memberItem":{"ucmsId":"6649862660441505792","duration":226,"image":"https://x0.ifengimg.com/ucms/2020_14/FD0803062552C876BB18097B30DC97333BC25E67_w480_h270.jpg","guid":"86d03c98-a45b-46da-9866-5acddddfb17e","playTime":"148586","commentNo":"2","shareTimes":"32","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"20200329","name":"钟南山：按照推断 中国没有大量的\u201c无症状感染者\u201d","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002ma8a82WxLL8I--m-_l8Tqrxz9W55hsahaFbChq--BtOdaA__","cpName":"今日视讯","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"钟南山","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34985611-102-009-110225.mp4","filesize":16428,"spliteTime":"225.622","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34985611-535-066-110225.mp3","filesize":3525,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34985611-102-009-110225/index.m3u8","filesize":16428,"spliteTime":"225.622","spliteNo":1,"isSplite":1}]}},{"title":"真实纪录片：美国穷人的生活状态是怎样的？","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/ucms/2020_14/24263F986E8A816349FCFE81B5DEA9FF1DB9F504_w852_h480.jpg"}],"abstractDesc":"","showType":"pic","createDate":"","updateDate":"","memberType":"video","itemId":"11","infoId":"7cccec2a-b3ab-49cb-8467-acb1e32daa4c","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20170814/13/wemedia/35a9b9bd72822f569cba9e97bf5341a588551cf0_size30_w200_h200.png","name":"今日视讯","desc":"国内外人情冷暖大事小情一网打尽。","id":"780306","followed":0},"memberItem":{"ucmsId":"6649891229242769408","duration":2546,"image":"https://x0.ifengimg.com/ucms/2020_14/24263F986E8A816349FCFE81B5DEA9FF1DB9F504_w852_h480.jpg","guid":"7cccec2a-b3ab-49cb-8467-acb1e32daa4c","playTime":"27039","commentNo":"2","shareTimes":"11","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"20200329","name":"真实纪录片：美国穷人的生活状态是怎样的？","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002H77xTHFXO7mw5qNd--SCkPdt2MRbu9IOD7phDUMtbJDo__","cpName":"今日视讯","searchPath":"129-10054","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"美国","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34986227-102-009-125625.mp4","filesize":233385,"spliteTime":"2546.307","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34986227-535-066-125625.mp3","filesize":39786,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34986227-102-009-125625/index.m3u8","filesize":233385,"spliteTime":"2546.307","spliteNo":1,"isSplite":1}]}},{"title":"\u201c不明生物\u201d闯入工厂多人围堵 放生时意外不断","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/952330F417E241B65A41C0AAEC4A90EFD8850237_size951_w1280_h720.png"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"12","infoId":"fd27769f-6e62-497e-b48c-c460dec9d53d","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190418/15/wemedia/158eb2ab003ca4df67e6fab5ddf997bfbfda1f24_size12_w200_h200.png","name":"燃新闻","desc":"有温暖 有深度 有情怀","id":"629445","followed":0},"memberItem":{"ucmsId":"6649738804368252973","duration":46,"image":"https://x0.ifengimg.com/thmaterial/2020_13/B5D9A6B6E8A44B15BA6FD4EB71E01136_w1280_h720.png","guid":"fd27769f-6e62-497e-b48c-c460dec9d53d","playTime":"111616","commentNo":"6","shareTimes":"18","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"怪兽\u201c四不像\u201d闯入工厂多人围追堵截 放生时出意外民警当场崩溃","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002QZ6ZQfd7RkOKY4K63LWPoz9B--Ejo--wS43GW-_-_sQC3cg__","cpName":"官网","searchPath":"55-71","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"动物新闻,野生动物,四不像,放生","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984987-102-009-092525.mp4","filesize":7603,"spliteTime":"46","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984987-535-066-092525.mp3","filesize":772,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"curtmp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34985005-102-009-092825.mp4","filesize":3412,"spliteTime":"20","spliteNo":1,"isSplite":1},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984987-102-009-092525/index.m3u8","filesize":7603,"spliteTime":"46","spliteNo":1,"isSplite":1}]}},{"title":"西班牙近万医护感染 派军机赴中国采购医用物资","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/ucms/2020_14/BA410D7AC03970BC64AB4D9FF1AD420AA9F756A2_w324_h181.png"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"13","infoId":"3faffbcb-fc86-4c6f-8995-d1e47ced1cf6","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20200323/14/wemedia/558b06aa360621564eb0af9465a732b90466f6d0_size71_w200_h200.png","name":"聚焦央视","desc":"聚焦央视热点资讯内容","id":"1583386","followed":0},"memberItem":{"ucmsId":"6649869129752784896","duration":38,"image":"https://x0.ifengimg.com/ucms/2020_14/BA410D7AC03970BC64AB4D9FF1AD420AA9F756A2_w324_h181.png","guid":"3faffbcb-fc86-4c6f-8995-d1e47ced1cf6","playTime":"89310","commentNo":"5","shareTimes":"667","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"疫情蔓延全球 西班牙军用货机赴中国采购医用物资","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002otfeVKAFY--EYZjthGfvCMoY1d7CvhG-_PxyY85TFyBfY__","cpName":"凤凰cut","searchPath":"140-10056","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"CCTV_4,新闻资讯,西班牙,新型冠状病毒","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/ifeng_5e8015e1e3319402484451_high.mp4","filesize":6240,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34985847-535-066-112937.mp3","filesize":607,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/ifeng_5e8015e1e3319402484451_high/index.m3u8","filesize":6240,"spliteTime":"","spliteNo":0,"isSplite":0}]}},{"title":"特朗普：美国能从中国的抗疫经历中学到很多","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/ucms/2020_13/5BAFBF26FC3CBD780E9C680054E2263463DC1CCF_w640_h360.jpg"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"14","infoId":"af5318f1-7109-42af-b10f-d64a3c9000fa","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/w200_h200_q100_blur/p0.ifengimg.com/pmop/2016/1222/FCDB9112F716C731245051D931E5B86CBA7A8EC5_size45_w300_h300.png","name":"环球时报","desc":"报道多元世界 解读复杂中国","id":"367951","followed":0},"memberItem":{"ucmsId":"6649617733916823552","duration":138,"image":"https://x0.ifengimg.com/ucms/2020_13/5BAFBF26FC3CBD780E9C680054E2263463DC1CCF_w640_h360.jpg","guid":"af5318f1-7109-42af-b10f-d64a3c9000fa","playTime":"198892","commentNo":"200","shareTimes":"116","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"20200328","name":"特朗普：美国能从中国的抗疫经历中学到很多","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002raa6dVU-_qVvjOSjJMdIp2XtEPVkTDS1OJRaMSeeADOA__","cpName":"自媒体","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"中国,美国,特朗普,疫情","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34983134-102-009-184925.mp4","filesize":12667,"spliteTime":"138.176","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34983134-535-066-184925.mp3","filesize":2159,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34983134-102-009-184925/index.m3u8","filesize":12667,"spliteTime":"138.176","spliteNo":1,"isSplite":1}]}},{"title":"破10万！美国拥有全球最多的确诊病例，可怕的是很多州没检测","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/D61208B3E6698EA82D213ACFBFA4633D5D95BD70_size11_w320_h200.jpeg"}],"abstractDesc":"","showType":"pic","createDate":"","updateDate":"","memberType":"video","itemId":"15","infoId":"0def8519-7df9-4002-84db-ec744c8382a3","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/w200_h200_q100_blur/p0.ifengimg.com/pmop/2016/1222/FCDB9112F716C731245051D931E5B86CBA7A8EC5_size45_w300_h300.png","name":"环球时报","desc":"报道多元世界 解读复杂中国","id":"367951","followed":0},"memberItem":{"ucmsId":"6649809790400725005","duration":165,"image":"https://x0.ifengimg.com/res/2020/D61208B3E6698EA82D213ACFBFA4633D5D95BD70_size11_w320_h200.jpeg","guid":"0def8519-7df9-4002-84db-ec744c8382a3","playTime":"134341","commentNo":"25","shareTimes":"53","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"破10万！美国拥有全球最多的确诊病例，可怕的是很多州没检测","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002lCWKd--qNouWuc9o1Mh8i2P8E42fQvDhv3mLXTpTAqmY__","cpName":"一点资讯","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"新冠状病毒,新闻资讯,社会事件","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39730046-102-9987642-080709.mp4","filesize":10989,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39730046-535-066-080709.mp3","filesize":2570,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39730046-102-9987642-080709/index.m3u8","filesize":10989,"spliteTime":"","spliteNo":0,"isSplite":0}]}},{"title":"意大利政府会场如考场 官员们戴上了口罩手套","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/C109B98DB24E64BD05553B435B3C000F9452AFC2_size1284_w1387_h780.png"}],"abstractDesc":"","showType":"pic","createDate":"","updateDate":"","memberType":"video","itemId":"16","infoId":"aa4deea4-aac5-4999-9353-cd066aaa6937","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190418/15/wemedia/158eb2ab003ca4df67e6fab5ddf997bfbfda1f24_size12_w200_h200.png","name":"燃新闻","desc":"有温暖 有深度 有情怀","id":"629445","followed":0},"memberItem":{"ucmsId":"6649669813293563989","duration":31,"image":"https://x0.ifengimg.com/res/2020/C109B98DB24E64BD05553B435B3C000F9452AFC2_size1284_w1387_h780.png","guid":"aa4deea4-aac5-4999-9353-cd066aaa6937","playTime":"119348","commentNo":"11","shareTimes":"23","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"意大利财政部设考试式会场 官员们戴上口罩手套 相隔超过一米","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002b4Fi12QzZAXZrGcaorKj0Rt8uqOi0--m-_zmL0vFDC7sM__","cpName":"官网","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"意大利,新型冠状病毒,戴口罩,会场,考场","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39725688-102-9987642-224834.mp4","filesize":4704,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39725688-535-066-224834.mp3","filesize":480,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39725688-102-9987642-224834/index.m3u8","filesize":4704,"spliteTime":"","spliteNo":0,"isSplite":0}]}},{"title":"美军爆发新冠病毒 台名嘴：恐将传给\u201c台军\u201d","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/ucms/2020_13/A90735967F384F790230C181BFFD709BC0BBC6CD_w640_h360.jpg"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"17","infoId":"ca62d039-8fb8-4465-80d3-8c86ea39dec0","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190314/23/wemedia/4b63bbf8427e7b0082d4790b4c2596ae970cb58b_size94_w200_h200.png","name":"宝岛那些事儿","desc":"台海新鲜事儿，提前呈给你\u201c批阅\u201d","id":"1184965","followed":0},"memberItem":{"ucmsId":"6649265500759011870","duration":67,"image":"https://x0.ifengimg.com/ucms/2020_13/A90735967F384F790230C181BFFD709BC0BBC6CD_w640_h360.jpg","guid":"ca62d039-8fb8-4465-80d3-8c86ea39dec0","playTime":"156289","commentNo":"37","shareTimes":"51","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"美军爆发新冠病毒，台名嘴：恐将传给\u201c台军\u201d","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002iMM7esWAPJFChraFmjGxIelXiXVxw6pl5XpNw--LAnFc__","cpName":"大风号","searchPath":"51-10077","copyright":"原创","simId":"","yvId":"","recommendType":"editor","keyWords":"冠状病毒,国际地方新闻,时政评论,台湾省","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34960715-102-9987636-190525.mp4","filesize":10414,"spliteTime":"67.478","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34960715-535-066-190525.mp3","filesize":1055,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/p34960715-102-9987636-190525/index.m3u8","filesize":10414,"spliteTime":"67.478","spliteNo":1,"isSplite":1}]}},{"title":"印度排队买菜现场：地上画满了框 一人站一个","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/701314675F337DC54A7EEC8374A3CF05C1BEA742_size524_w1920_h1080.jpeg"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"18","infoId":"f7cdb2e2-d7a7-4bab-939d-8c335bed0353","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190418/15/wemedia/158eb2ab003ca4df67e6fab5ddf997bfbfda1f24_size12_w200_h200.png","name":"燃新闻","desc":"有温暖 有深度 有情怀","id":"629445","followed":0},"memberItem":{"ucmsId":"6649646300218269765","duration":41,"image":"https://x0.ifengimg.com/res/2020/701314675F337DC54A7EEC8374A3CF05C1BEA742_size524_w1920_h1080.jpeg","guid":"f7cdb2e2-d7a7-4bab-939d-8c335bed0353","playTime":"132950","commentNo":"3","shareTimes":"28","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"这集看过！印度\u201c北欧式\u201d排队 一人一个框 有序排队保持距离","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002gpsW1TpZMvAHJPumEkpihPV0Ph4e2Mic3Hp-_tU2dpMQ__","cpName":"官网","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"印度,社会事件,新闻资讯,防疫,排队买菜","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39769010-102-9987642-210234.mp4","filesize":7771,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39769010-535-066-210234.mp3","filesize":634,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39769010-102-9987642-210234/index.m3u8","filesize":7771,"spliteTime":"","spliteNo":0,"isSplite":0}]}},{"title":"疫情持续恶化 美国小哥：快学学中国人吧","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/thmaterial/2020_13/77F2AF19E56D4755A0EAC6B5E4B3A52E_w640_h360.jpg"}],"abstractDesc":"","showType":"pic","createDate":"","updateDate":"","memberType":"video","itemId":"19","infoId":"ab1a9ef2-973c-463c-aebe-bbc71a2ac972","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190628/9/wemedia/73245af1e855f4491a134eb67992c64d0214182f_size25_w200_h200.png","name":"中国日报网","desc":"中国日报网, 国家级综合性媒体网站。","id":"365205","followed":0},"memberItem":{"ucmsId":"6649650590680879104","duration":382,"image":"https://x0.ifengimg.com/thmaterial/2020_13/77F2AF19E56D4755A0EAC6B5E4B3A52E_w640_h360.jpg","guid":"ab1a9ef2-973c-463c-aebe-bbc71a2ac972","playTime":"334871","commentNo":"58","shareTimes":"375","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"20200328","name":"疫情持续恶化 美国小哥：快学学中国人怎么在家隔离的","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002THOIDQeortRucyt4KrebDoaF6DiOI51qkhEp9eW1nAQ__","cpName":"自媒体","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"美国,疫情,中国","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984969-102-009-092225.mp4","filesize":54226,"spliteTime":"382","spliteNo":1,"isSplite":1},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984969-535-066-092225.mp3","filesize":6010,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"curtmp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984989-102-009-092525.mp4","filesize":4677,"spliteTime":"32","spliteNo":1,"isSplite":1},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/p34984969-102-009-092225/index.m3u8","filesize":54226,"spliteTime":"382","spliteNo":1,"isSplite":1}]}},{"title":"比尔·盖茨：美国目前检测能力不足 4月中旬难好转","imageList":[{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/ucms/2020_14/D8BB967B763B03D85DFDC71DFE7C5370B69D350C_w640_h360.jpg"}],"abstractDesc":"","showType":"standard","createDate":"","updateDate":"","memberType":"video","itemId":"20","infoId":"a2f2f8e2-defa-4d42-875c-5550aebcff0a","tag":"","weMedia":{"headPic":"https://d.ifengimg.com/q100/img1.ugc.ifeng.com/newugc/20190627/15/wemedia/bdc268c3c899ddff40a2b1ca03f64c217886701d_size14_w200_h200.png","name":"封面新闻","desc":"封面新闻，引领亿万年轻人的生活方式","id":"310808","followed":0},"memberItem":{"ucmsId":"6649664742245802070","duration":28,"image":"https://x0.ifengimg.com/ucms/2020_14/D8BB967B763B03D85DFDC71DFE7C5370B69D350C_w640_h360.jpg","guid":"a2f2f8e2-defa-4d42-875c-5550aebcff0a","playTime":"28263","commentNo":"0","shareTimes":"1","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"30秒丨比尔·盖茨：美国目前检测能力不足，四月中旬情况难以好转","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002uZR8RM3UReN9l2EGnSirAyEUy0kwe6AQAvnVtd4xTs0__","cpName":"官网","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"新冠状病毒,比尔盖茨,美国疫情,新冠疫情,病毒检测","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39771242-102-9987642-220934.mp4","filesize":3202,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39771242-535-066-220934.mp3","filesize":443,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/28/s39771242-102-9987642-220934/index.m3u8","filesize":3202,"spliteTime":"","spliteNo":0,"isSplite":0}]}}]
     */

    private String relate;
    private String title;
    private String relateDes;
    private String systemTime;
    private List<Header> header;
    private List<BodyListBean> bodyList;

    public String getRelate() {
        return relate;
    }

    public void setRelate(String relate) {
        this.relate = relate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelateDes() {
        return relateDes;
    }

    public void setRelateDes(String relateDes) {
        this.relateDes = relateDes;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public List<BodyListBean> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<BodyListBean> bodyList) {
        this.bodyList = bodyList;
    }

    /**
     * Auto-generated: 2020-03-30 8:52:37
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Header {

        private String title;
        private List<BodyListBean.ImageListBean> imageList;
        private String abstractDesc;
        private String showType;
        private String createDate;
        private String updateDate;
        private String memberType;
        private String itemId;
        private String infoId;
        private String tag;
        private BodyListBean.WeMediaBean weMedia;
        private BodyListBean.MemberItemBean memberItem;

        public Header() {

        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setImageList(List<BodyListBean.ImageListBean> imageList) {
            this.imageList = imageList;
        }
        public List<BodyListBean.ImageListBean> getImageList() {
            return imageList;
        }

        public void setAbstractDesc(String abstractDesc) {
            this.abstractDesc = abstractDesc;
        }
        public String getAbstractDesc() {
            return abstractDesc;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }
        public String getShowType() {
            return showType;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
        public String getCreateDate() {
            return createDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
        public String getUpdateDate() {
            return updateDate;
        }

        public void setMemberType(String memberType) {
            this.memberType = memberType;
        }
        public String getMemberType() {
            return memberType;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }
        public String getItemId() {
            return itemId;
        }

        public void setInfoId(String infoId) {
            this.infoId = infoId;
        }
        public String getInfoId() {
            return infoId;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
        public String getTag() {
            return tag;
        }

        public void setWeMedia(BodyListBean.WeMediaBean weMedia) {
            this.weMedia = weMedia;
        }
        public BodyListBean.WeMediaBean getWeMedia() {
            return weMedia;
        }

        public void setMemberItem(BodyListBean.MemberItemBean memberItem) {
            this.memberItem = memberItem;
        }
        public BodyListBean.MemberItemBean getMemberItem() {
            return memberItem;
        }

    }

    public static class BodyListBean {
        /**
         * title : 美国要求安理会明确写入“新冠病毒源于中国”遭拒
         * imageList : [{"image":"https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/62F384E4478518D0D747438D38DBCDA6F7FD432A_size16_w320_h200.jpeg"}]
         * abstractDesc :
         * showType : standard
         * createDate :
         * updateDate :
         * memberType : video
         * itemId : 1
         * infoId : a0403dd0-a7d7-40d5-8860-53977f193b78
         * tag :
         * weMedia : {"headPic":"https://d.ifengimg.com/w200_h200_q100_blur/p0.ifengimg.com/pmop/2016/1222/FCDB9112F716C731245051D931E5B86CBA7A8EC5_size45_w300_h300.png","name":"环球时报","desc":"报道多元世界 解读复杂中国","id":"367951","followed":0}
         * memberItem : {"ucmsId":"6649809843538370566","duration":138,"image":"https://x0.ifengimg.com/res/2020/62F384E4478518D0D747438D38DBCDA6F7FD432A_size16_w320_h200.jpeg","guid":"a0403dd0-a7d7-40d5-8860-53977f193b78","playTime":"166421","commentNo":"63","shareTimes":"114","dislikeNo":"0","isColumn":"0","columnYear":"","columnMonth":"","programNo":"","name":"美国要求安理会决议明确写入\u201c新冠病毒源于中国\u201d遭到拒绝","pcUrl":"","mUrl":"https://v.ifeng.com/c/v/v002--zuCMvKIUNf3oj6B80Q7b6gERC-_AZpVPSHDb05XSg0c__","cpName":"一点资讯","searchPath":"51-52","copyright":"转载","simId":"","yvId":"","recommendType":"editor","keyWords":"联合国,新型冠状病毒,美国,国际地方新闻","feedbackFeatures":"","videoFiles":[{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-102-9987642-081134.mp4","filesize":9215,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-535-066-081134.mp3","filesize":2160,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-102-9987642-081134/index.m3u8","filesize":9215,"spliteTime":"","spliteNo":0,"isSplite":0}]}
         */

        private String title;
        private String abstractDesc;
        private String showType;
        private String createDate;
        private String updateDate;
        private String memberType;
        private String itemId;
        private String infoId;
        private String tag;
        private WeMediaBean weMedia;
        private MemberItemBean memberItem;
        private List<ImageListBean> imageList;

        public BodyListBean() {
            weMedia = new WeMediaBean();
            memberItem = new MemberItemBean();
            //imageList = new ArrayList<>();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstractDesc() {
            return abstractDesc;
        }

        public void setAbstractDesc(String abstractDesc) {
            this.abstractDesc = abstractDesc;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getMemberType() {
            return memberType;
        }

        public void setMemberType(String memberType) {
            this.memberType = memberType;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getInfoId() {
            return infoId;
        }

        public void setInfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public WeMediaBean getWeMedia() {
            return weMedia;
        }

        public void setWeMedia(WeMediaBean weMedia) {
            this.weMedia = weMedia;
        }

        public MemberItemBean getMemberItem() {
            return memberItem;
        }

        public void setMemberItem(MemberItemBean memberItem) {
            this.memberItem = memberItem;
        }

        public List<ImageListBean> getImageList() {
            return imageList;
        }

        public void setImageList(List<ImageListBean> imageList) {
            this.imageList = imageList;
        }

        public static class WeMediaBean {
            /**
             * headPic : https://d.ifengimg.com/w200_h200_q100_blur/p0.ifengimg.com/pmop/2016/1222/FCDB9112F716C731245051D931E5B86CBA7A8EC5_size45_w300_h300.png
             * name : 环球时报
             * desc : 报道多元世界 解读复杂中国
             * id : 367951
             * followed : 0
             */

            private String headPic;
            private String name;
            private String desc;
            private String id;
            private int followed;

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getFollowed() {
                return followed;
            }

            public void setFollowed(int followed) {
                this.followed = followed;
            }
        }

        public static class MemberItemBean {
            /**
             * ucmsId : 6649809843538370566
             * duration : 138
             * image : https://x0.ifengimg.com/res/2020/62F384E4478518D0D747438D38DBCDA6F7FD432A_size16_w320_h200.jpeg
             * guid : a0403dd0-a7d7-40d5-8860-53977f193b78
             * playTime : 166421
             * commentNo : 63
             * shareTimes : 114
             * dislikeNo : 0
             * isColumn : 0
             * columnYear :
             * columnMonth :
             * programNo :
             * name : 美国要求安理会决议明确写入“新冠病毒源于中国”遭到拒绝
             * pcUrl :
             * mUrl : https://v.ifeng.com/c/v/v002--zuCMvKIUNf3oj6B80Q7b6gERC-_AZpVPSHDb05XSg0c__
             * cpName : 一点资讯
             * searchPath : 51-52
             * copyright : 转载
             * simId :
             * yvId :
             * recommendType : editor
             * keyWords : 联合国,新型冠状病毒,美国,国际地方新闻
             * feedbackFeatures :
             * videoFiles : [{"useType":"mp4500k","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-102-9987642-081134.mp4","filesize":9215,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"mp3","mediaUrl":"https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-535-066-081134.mp3","filesize":2160,"spliteTime":"","spliteNo":0,"isSplite":0},{"useType":"hls500k","mediaUrl":"http://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-102-9987642-081134/index.m3u8","filesize":9215,"spliteTime":"","spliteNo":0,"isSplite":0}]
             *  videoFiles列表有3个元素。第一个为MP4，视频；第二个为MP3，只有声音；
             */

            private String ucmsId;
            private int duration;
            private String image;
            private String guid;
            private String playTime;
            private String commentNo;
            private String shareTimes;
            private String dislikeNo;
            private String isColumn;
            private String columnYear;
            private String columnMonth;
            private String programNo;
            private String name;
            private String pcUrl;
            private String mUrl;
            private String cpName;
            private String searchPath;
            private String copyright;
            private String simId;
            private String yvId;
            private String recommendType;
            private String keyWords;
            private String feedbackFeatures;
            private List<VideoFilesBean> videoFiles;

            public MemberItemBean() {
                videoFiles = new ArrayList<>();
            }

            public String getUcmsId() {
                return ucmsId;
            }

            public void setUcmsId(String ucmsId) {
                this.ucmsId = ucmsId;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getGuid() {
                return guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getPlayTime() {
                return playTime;
            }

            public void setPlayTime(String playTime) {
                this.playTime = playTime;
            }

            public String getCommentNo() {
                return commentNo;
            }

            public void setCommentNo(String commentNo) {
                this.commentNo = commentNo;
            }

            public String getShareTimes() {
                return shareTimes;
            }

            public void setShareTimes(String shareTimes) {
                this.shareTimes = shareTimes;
            }

            public String getDislikeNo() {
                return dislikeNo;
            }

            public void setDislikeNo(String dislikeNo) {
                this.dislikeNo = dislikeNo;
            }

            public String getIsColumn() {
                return isColumn;
            }

            public void setIsColumn(String isColumn) {
                this.isColumn = isColumn;
            }

            public String getColumnYear() {
                return columnYear;
            }

            public void setColumnYear(String columnYear) {
                this.columnYear = columnYear;
            }

            public String getColumnMonth() {
                return columnMonth;
            }

            public void setColumnMonth(String columnMonth) {
                this.columnMonth = columnMonth;
            }

            public String getProgramNo() {
                return programNo;
            }

            public void setProgramNo(String programNo) {
                this.programNo = programNo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPcUrl() {
                return pcUrl;
            }

            public void setPcUrl(String pcUrl) {
                this.pcUrl = pcUrl;
            }

            public String getMUrl() {
                return mUrl;
            }

            public void setMUrl(String mUrl) {
                this.mUrl = mUrl;
            }

            public String getCpName() {
                return cpName;
            }

            public void setCpName(String cpName) {
                this.cpName = cpName;
            }

            public String getSearchPath() {
                return searchPath;
            }

            public void setSearchPath(String searchPath) {
                this.searchPath = searchPath;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getSimId() {
                return simId;
            }

            public void setSimId(String simId) {
                this.simId = simId;
            }

            public String getYvId() {
                return yvId;
            }

            public void setYvId(String yvId) {
                this.yvId = yvId;
            }

            public String getRecommendType() {
                return recommendType;
            }

            public void setRecommendType(String recommendType) {
                this.recommendType = recommendType;
            }

            public String getKeyWords() {
                return keyWords;
            }

            public void setKeyWords(String keyWords) {
                this.keyWords = keyWords;
            }

            public String getFeedbackFeatures() {
                return feedbackFeatures;
            }

            public void setFeedbackFeatures(String feedbackFeatures) {
                this.feedbackFeatures = feedbackFeatures;
            }

            public List<VideoFilesBean> getVideoFiles() {
                return videoFiles;
            }

            public void setVideoFiles(List<VideoFilesBean> videoFiles) {
                this.videoFiles = videoFiles;
            }

            public static class VideoFilesBean {
                /**
                 * useType : mp4500k
                 * mediaUrl : https://ips.ifeng.com/video19.ifeng.com/video09/2020/03/29/s39780224-102-9987642-081134.mp4
                 * filesize : 9215
                 * spliteTime :
                 * spliteNo : 0
                 * isSplite : 0
                 */

                private String useType;
                private String mediaUrl;
                private int filesize;
                private String spliteTime;
                private int spliteNo;
                private int isSplite;

                public String getUseType() {
                    return useType;
                }

                public void setUseType(String useType) {
                    this.useType = useType;
                }

                public String getMediaUrl() {
                    return mediaUrl;
                }

                public void setMediaUrl(String mediaUrl) {
                    this.mediaUrl = mediaUrl;
                }

                public int getFilesize() {
                    return filesize;
                }

                public void setFilesize(int filesize) {
                    this.filesize = filesize;
                }

                public String getSpliteTime() {
                    return spliteTime;
                }

                public void setSpliteTime(String spliteTime) {
                    this.spliteTime = spliteTime;
                }

                public int getSpliteNo() {
                    return spliteNo;
                }

                public void setSpliteNo(int spliteNo) {
                    this.spliteNo = spliteNo;
                }

                public int getIsSplite() {
                    return isSplite;
                }

                public void setIsSplite(int isSplite) {
                    this.isSplite = isSplite;
                }
            }
        }

        public static class ImageListBean {
            /**
             * image : https://d.ifengimg.com/w640_h360_q100_blur/x0.ifengimg.com/res/2020/62F384E4478518D0D747438D38DBCDA6F7FD432A_size16_w320_h200.jpeg
             */

            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
