## 项目说明
### 约定

### 规范

### 模块化开发

#### 模块化==module? no
  现有框架弊病

  * baselib  几个项目下来不知道那些该保留那些该删除 里面包括太多私有bean
  优化方法 bean 按模块分包
  *
#### 此模块组成结构

* 统一的包名 edu.tjrac.www 代码复制过来不需要改包名
* res-modulename 该模块对应的特有资源文件 (自动化创建的资源默认是放到res里面,所以在编写module时候最好留个新眼,module成熟后主要开发的是主module所以这个框架约到后期越简便)
* 移植更方便 只需要拷贝代码/资源文件夹到目标项目对应文件夹下 不需要关心module gradle配置是否统一

* 通用图标放到baselib res 里面

* 项目主题资源放到 res-theme-default| 新的ui主题放到 res-theme-newyear

* 项目私有图标放到 res 里面

#### 移植步骤

0. 查看activity fragment adapteritem layout 是否在对应res 文件夹下(框架使用前期)
1. drawable shape string等资源创建的时候就应该考虑分包(框架使用前期需要校验)
2. 复制src 代码res 文件夹 移植之后src报资源错误的话找到res中对应的xml剪切到对应res_ 之后如果其他地方报错则剪切到baselib中
3. gradle 引用对应res文件夹