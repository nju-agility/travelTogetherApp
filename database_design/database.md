共有9个表：

**!!**对于重要的，或者没有默认值的字段加粗了（看仔细）, 所有表名小写，且为复数

1. users
2. activities



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

