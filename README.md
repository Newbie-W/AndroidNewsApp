# AndroidNewsApp

毕业设计，简易的新闻类app制作中。
&nbsp;

---
使用了SQLite作为数据库，<br>
使用了[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)加载图片（头像显示部分使用了）<br>
使用了[Glide](https://github.com/bumptech/glide)加载新闻图片<br>
使用了[MobTech](http://www.mob.com/)的短信认证功能<br>
使用[CircleImageView](https://github.com/hdodenhof/CircleImageView)显示圆形的头像（侧滑栏、个人信息中的头像）<br>
使用了凤凰网视频做为视频接口<br>
使用了[JiaoZiVideoPlayer](https://github.com/Jzvd/JiaoZiVideoPlayer)加载视频<br>


目前实现了，
- 引导页，自动跳转至登录页
- 登录页、注册页间的跳转，实现注册功能
- 登录方式上，实现了用户名-密码方式、手机号-（验证码或密码）登录三种方式
- 登录后跳转至主页面（新闻展示，RecyclerView显示列表），有回到顶部的按钮，可查询新闻（暂时只可根据标题内容查询）
- 新闻页，使用了新闻接口，可以获取新闻并显示。点击新闻后，该条新闻会存在本地数据库中（不过在目前在网络获取到的新闻，都会缓存至数据库了，此举可能多余），并跳转至新闻详情页
- 视频页
- 新闻下拉刷新，将数据库中的新闻显示出来，并实现每次刷新获取新闻。
- 实现了发布新闻页（目前只可发纯文字新闻）（此功能有待完善）
- 实现了侧滑菜单栏，侧滑栏点击头像，出现修改个人信息界面，并实现修改信息功能
- 实现了收藏、关注等各种列表界面（ListView），实现收藏、历史记录及对应查删功能

目前的实现都是大概的轮廓，之后会一步步增加内容+完善界面，实体类上还有些问题，之后会更改。