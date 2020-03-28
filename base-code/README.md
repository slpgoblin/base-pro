# 单项目后端架构
    适用于小项目

## 模块说明
* code-api:接口
* code-basic:基础模块,数据中心
* code-service:业务逻辑
* code-comp:公共组件
* code-common:公共类，工具类等
* code-web:HTTP接口服务
* sql-file:存放sql脚本

## POJO
* DO:实体类
* DTO:数据传输对象。web->Controller->Service
* BO:业务层对象。service->Controller
* VO:显示层对象。controller->web

---

## 技术栈
* [hutool工具类](https://hutool.cn/docs/#/)

## Git Commit Message
提交信息分为三大块:
>* Type, 本次提交类型(Type)及简要说明(Subject), 必填
>* Body, 本次提交详细说明(Body), 可选
>* Footer, 代码提交时所在分支(branch)及版本号(version), 必填

### 示例模板
```
${Type}: ${Subject}

${Body, optional}

br.: ${branch}
ver: ${version}
```
### Type
#### 类型具体来说，Type 分为：
>* feat: 增加新功能；
>* fix: 修复错误；
>* docs: 修改文档、注释；
>* style: 修改样式；
>* refactor: 代码重构；
>* test: 增加测试模块，不涉及生产环境的代码；
>* chore: 更新核心模块，包配置文件，不涉及生产环境的代码；
>* log: 新增日志代码；

### Subject：
> * 标题标题不超过50个字符，结尾不需要对应的句号；应该使用祈使句来描述，比如：修复标点符号错误。

### Body：
>* 正文并不是所有的 Commit 都需要正文，所以这一模块是可选的，必要的时候对本次 Commit 做一些背景说明，阐释具体的原因和内容，但是不解释具体的过程。注意：正文的文字尽量不要超过72个字符。

### Footer:
>* br.: ${branch} 分支
>* ver: ${version} 版本 只在对接口修改的时候需要标记版本，其他更新可以不写

### Example
```
fix: 性别字段调整

表设计字段含义修改，sex字段本来是文字男女，现在改为枚举，0女，1男

br.: gjs-dev
ver: 1.0.1
```
---


## [项目WIKI地址](https://github.com/slpgoblin/base-pro.git)