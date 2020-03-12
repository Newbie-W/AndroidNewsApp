# AndroidNewsApp

简易的新闻类app制作中。
&nbsp;

---
使用了SQLite作为数据库，<br>
使用了[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)加载图片，<br>
使用[CircleImageView](https://github.com/hdodenhof/CircleImageView)显示圆形的头像（侧滑栏里的头像）<br>

目前实现了，
- 引导页，自动跳转至登录页
- 登录页、注册页间的跳转，实现注册功能
- 登录后跳转至主页面（新闻展示，RecyclerView显示列表），有回到顶部的按钮
- 新闻页，使用了新闻接口，可以获取新闻并显示。点击新闻后，该条新闻会存在本地数据库中，并跳转至新闻详情页
- 新闻下拉刷新，将数据库中的新闻显示出来，并实现每次刷新、换至下10条数据库存储的新闻。
- 实现了发布新闻页（目前只可发纯文字新闻）（此功能有待完善）
- 实现了侧滑菜单栏，实现了收藏、关注等各种列表界面（ListView）

目前的实现都是大概的轮廓，之后会一步步增加内容+完善界面，实体类上还有些问题，之后会更改。（目前登录的话，可用的用户名密码均0。）