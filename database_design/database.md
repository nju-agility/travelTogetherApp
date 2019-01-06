共有10个表：

**!!**对于重要的，或者没有默认值的字段加粗了（看仔细）, 所有表名小写，且为复数

1. users
2. activities
3. admins
4. relative_paths
5. user_applies
6. user_comments
7. user_records
8. tags
9. activitiy_tags
10. user_tags


## 1. users
主键：account

外建：activity_id ==> activities.aid

字段如下:

1. **accout**
    
    varchar(32) 账户：使用email

2. **name**

    varchar(10) 姓名非空 
    
3. gender

    tinyint 默认是0，表示男，1，表示女

4. age
    
    tinyint 年龄 默认18
5. in

    tinyint 是否进入活动 默认0未进入，1进入

6. activity_id

    int 目前参加的活动id 外建引用了activities的aid 默认为0 所以activities有个aid为0的记录，它只是为了满足外建的约束而存在

7. city

    varchar(10) 城市 默认南京
8. status

    tinyint 账户的状态 默认是0

        -1 停用状态
        0 初始注册状态，需要验证身份    
        1 已经上传照片，等待验证
        2 身份验证成功 

9. **code**

    varchar(18) 身份证号码 默认为null 可以不用

10. **passwd**

    varchar(100) 密码 非空

11. num_0f_score

    int unsigined 默认1 评论此人的次数，用于计算平均得分

12. score

    tinyint 默认100 此人的平均得分

## 2. activities
主键：aid

1. aid

    int 自增字段 
2. status

    tinyint 默认0
    
        0 是刚创建
        1 是已经验证发布
        3 是已经撤销
        4 是正在进行中
        5 结束
3. **owner**

    varchar(32) 默认'',为避免表相互引用外建，所以应该从应用层保证一致性

4. city
   
   varchar(10) 默认是南京
5. location

    varchar(45) 默认是鼓楼
6. **title**

    varchar(45) 活动标题
7. **details**

    varchar(200) 活动详情

8. **time_start** 

    varchar(20) 默认是是2019-01-01应该属于设计错误吧 但是不想用可以默认是当前系统时间的timestamp 
    不过必须添加自己的字段

9. **time_end**

    varchar(20) 默认2020-01-01 **同上**

10. score

    活动的平均得分

11. num_of_score

    评价这个活动的人数，作用计算平均得分

12. num_0f_pic

    这个活动的宣传图片的张数，默认是0
13. type

    活动的类型，默认是outgoing


## 3. admins
主键：无

1. **account**

    varchar(10) not null

2. **passwd**

    varchar(100) not null

## 4. relative_paths
1. **item**

    int  not null
        1 用户头像
        2 用户身份证
        3 发布活动的宣传图片
        4 用户拍摄的上传的图片
2. **path**

    这些项的图片存储的父路径，
    路径组装 = path + 唯一标识(account) +“_” + [序号] + ".png/jpeg"

## 5. user_applies
主键：aid,account
此表中存储 “用户申请活动" 的申请
1. **aid**

    int not null 外建到activities的aid
2. **account**

    varchar(32) not null 外建到users.account

## 6. user_comments
主键：account_commenter, account_commented
1. **account_commenter**

    评论者 not null 外键到 users.account
2. **account_commented**

    被评论者 not null 外键到 users.account
3. comment

    评论 not null 默认是 'good'
4. score

    得分 not null 默认 100

## 7. user_records
用户参加过的活动记录表
1. **account**

    外键到 users.account

2. **aid**

    活动id 外键
3. **comment**

    尽管都是comment， 有别于user_comments.comment，这个是对活动的评价，后者是对用户的评价

4. score

    对此活动的评分 默认100

5. num_0f_pic

    用户上传的照片数量 默认是0


## 8. tags
主键: tid
1. tid

    int not null 自增字段 标签id
2. **tag**

    varchar(20) not null 没有默认值 标签

## 9. activity_tags
1. **aid**

    活动id 外键到activities.aid

2. **tid**

    标签 外键到tags.tid

## 10. user_tags
1. **account**

    用户 外键到users.account

2. **tid**
    标签 外键到tags.tid

