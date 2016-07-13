# Login-MVP-Architecture
- MVP架构模拟登陆功能

[原文地址](http://www.jianshu.com/p/f8e9967bac76#comments)

# MVP简介
最近几天在啃MVP，现在的你或许跟几天前的我一样，对MVP还是一脸懵逼，虽然MVP三个字母都认识，但连在一起却不明白到底是个什么东东，没关系，快来干了这碗鸡汤，立马从懵逼到入门，入不了门你来打我，文末统计人数。

--------

首先，MVP是一种设计模式，或者说架构。Google把它列入[Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)--Android 架构蓝图，并给出了官方的例子来解释如何实现MVP。本文实现的登陆功能就是根据[MVP基础架构Demo](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)来实现的。

其次，MVP从何而来？想必都知道是MVC的演化版本，现在比较流行，被广大开发者所认可。被认可的原因我在这里总结一下：
- 代码清晰，容易理解（掌握MVP的前提下）
- 简化了万能的Activity的逻辑
- 解耦了View和Model
- 方便单元测试

一直以来，在MVC模式中Activity的万能角色就备受诟病，一旦逻辑越来越复杂，Activity就越来越臃肿，承担越来越多的职责，代码阅读起来费劲，维护成本跟着提高，而且，在MVC模式中，View 和 Model直接交互，耦合度高，违背了软件开发“高类聚、低耦合”的设计目标--于是MVP横空出世。

关于MVP的理论知识就哔哔这些，想要详细了解的童鞋回头自己做功课去，这里不是重点，本文的重点是把MVP模式实践起来。

# MVP实现登陆
![Android-MVP-Architecture](https://github.com/googlesamples/android-architecture/wiki/images/mvp.png)

这是google samples TODO-MVP项目中的MVP图解。因为本人学习MVP主要也是根据谷歌官方demo来的，所以，这个图直接拿来。这个图怎么看？

- 分为左右两部分，左边没颜色的部分包含的内容是Model，这里面包含了数据实体模型、数据访问接口、SQLite数据库操作、网路数据操作、数据内存缓存，这些都是Model要做的事情，跟MVC没什么差别；

- 右边...那个什么颜色的背景（色盲晚期），注意那个Activity，我们看到VIEW和PRESENTER都被放在了Activity里边，而VIEW的实现类在这里用的是Fragment；

- 隐藏内容：这里面其实还有一个内容--契约类，也就是项目里的XXXContract.java类，是一个接口类，作用是定义VIEW接口和PRESENTER接口提供的接口方法。这个类原则上不属于MVP模式里的任何角色，所以没在上图出现，可以理解。

  [先看看效果](http://m.17sysj.com/video/lpds_c08a0fc39800a) 不方便看视频的就看看图吧！  
  
![Login-MVP](https://github.com/chengshengyang/test/blob/master/21.jpg?raw=true)

-------

![项目代码结构](https://github.com/chengshengyang/test/blob/master/23.png?raw=true)

- 1.创建View接口和Presenter接口基类
	BaseView作为View接口基类，定义了一个重要的接口：

       	void setPresenter(T presenter);

![看见这个接口了吗？](https://github.com/chengshengyang/test/blob/master/26.jpg?raw=true)

![看见了又怎样？](https://github.com/chengshengyang/test/blob/master/25.jpg?raw=true)

  这其实是MVP的一个关键点，通过这个接口，View的实现类（即Fragment）就持有了Presenter的实例，于是，View就可以通过Presenter来操作Model中的数据接口了。如果你要实现MVP模式，记住，不管三七二十一，先写这个基佬，哦，不对，是基类。

  Presenter接口基类里同样定义了一个接口：

      void start();

这个方法就是直接操作Model的，比如加载数据。通过这两个基类的定义的接口就能看出，View和Model不直接交互，而是通过Presenter来操作，这是与MVC的不同之处。

- 2.登陆契约类LoginContract

      	public interface LoginContract {

      		interface Presenter extends BasePresenter {
            	void login();
            	void reset();
      		}

      		interface View extends BaseView<Presenter> {
            	String getUserEmail();
            	String getPassword();

            	boolean isEmailValid(String email);
            	boolean isPasswordValid(String password);
	
           		 boolean setEmailError(String error);
           		 boolean setPasswordError(String error);

            	void showLoginProgress(boolean show);
            	void resetEditView();
            	void toMainAct();
            	void showFailedError();
        		}
      		}

  这个类是首次出现于google的mvp示例中，以前的MVP模式并未见到，这个类定义了View接口和Presenter接口为对方的实例提供的方法。

  比如，我在View中可以获取用户输入的邮箱和密码，判断邮箱密码是否有效，设置邮箱密码输入框错误提示信息，显示登陆ProgressBar等，同样，在Presenter接口中，提供了登陆和重置两个功能，用户通过View上的两个按钮，响应Presenter对应的接口，执行相关的业务逻辑。

  **这个契约类的好处是方便接口统一管理、修改，同时，内容清晰，一目了然。**

- 3.View的实现类LoginFragment implements LoginContract.View
实现接口定义的各个方法，必须持有Presenter，并通过接口

      	void setPresenter（T presenter）

为其赋值。

  注意官方的demo说明里有这段内容：
> Note: in a MVP context, the term "view" is overloaded:

  > - The class android.view.View will be referred to as "Android View"
  > - The view that receives commands from a presenter in MVP, will be simply called "view".

  我来献个丑，翻译一下：
> 注意：在MVP的上下文里，“view”一词有多重含义:

  > - android.view.View被称为“Android View”
  > - 在MVP中，从presenter接收命令的view将被简单地称为“view”。

  什么意思？

  我的理解是这样的：**MVP中的VIEW由两部分组成，一个是view接口，比如上面的LoginContract.View接口；一个是该接口的实现类，比如上面的LoginFragment。view接口负责与presenter交互，presenter调用view接口定义方法来操作view的实现类；具体实现都是在android.view.View里实现的，即LoginFragment。**

- 4.Presenter的实现类LoginPresenter implements LoginContract.Presenter
实现接口定义的各个方法，必须持有Model对象和View对象

      	private final UserRepository mUserRepository;
      
      	private final LoginContract.View mLoginView;

然后，你想让View干嘛，调用View相对应的接口就行了，想要什么数据，想对数据做什么操作，调用Model对象的对应方法就行了；或许你已经发现了：

  **Presenter对View的操作都是通过接口来完成的。**

- 5.Activity的角色，看Google官方Sample里怎么介绍的：
> It uses fragments for two reasons:

  > The separation between Activity and Fragment fits nicely with this implementation of MVP: ***the Activity is the overall controller that creates and connects views and presenters.***
Tablet layout or screens with multiple views take advantage of the Fragments framework.

  献丑二进宫：
  > （MVP中的View实现）使用Fragment有两个原因：

  > Activity与Fragment之间的分离很好的符合了MVP的实现：**Activity作为整体控制器来创建和连接views与presenters。**
Tablet布局或者屏幕上有多个views的布局可以很好的利用Fragments框架。

  在MVP模式里，Activity的功能变得简单了很多，一是创建View布局（Fragment），二是实例化Presenter（LoginPresenter），并将View（Fragment）作为参数，传入到Presenter（LoginPresenter）中，在Presenter（LoginPresenter）构造函数中传递给Presenter（LoginPresenter）持有的View对象，然后View对象调用setPresenter方法，将自身this传递给View实例（Fragment）。也就是上面说的“***创建和连接views与presenters。***”

  来看看Activity代码多简单：

      public class LoginActivity extends AppCompatActivity {    
            @Override    
            protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);        
            setContentView(R.layout.activity_login);
        
            LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);        
            // Create the view        
            if (loginFragment == null) {            
                loginFragment = LoginFragment.newInstance("LOGIN_FRAGMENT");        
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); 
            transaction.add(R.id.contentFrame, loginFragment);        
            transaction.commit(); 
       
            // Create the presenter        
            new LoginPresenter(
                    getApplicationContext(),                
                    UserRepository.getInstance(
                        UserLocalDataSource.getInstance(getApplicationContext()),
                        UserRemoteDataSource.getInstance()),                
                    loginFragment);    
        }
      }

  至此，MVP模式里的VP就可以运行起来了，连通起来了。下面来说说Model。如果对MVC的Model非常熟悉可以跳过。

- 6.Model的创建
在目录结构图中，整个data package里的都是Model的内容，包括实体模型（User类）、本地数据库操作（local package）、远程数据访问（remote package）三部分，跟在MVC里并无差别，这里不展开介绍。

  业务逻辑需要什么样的数据实体、数据操作，在对应的包里面构建就行了，这里要提到的是，Presenter对Model的持有，这里也是通过接口实现的，间接通过UserDataSource接口类，直接通过UserDataSource的实现类UserRepository。而UserRepository同时持有对本地数据和远程数据操作的对象：

      private final UserDataSource mLocalDataSource;
      private final UserDataSource mRemoteDataSource;

# MVP实践总结
使用MVP模式也有一些不尽如人意的地方，比如，类和接口变多了，代码也多了，项目大了可能会不好管理，但这都不是事，用多点代码、多点类文件换取低耦合度、结构清晰、容易理解、易扩展的架构，这买卖值了。

第一遍看的时候懵逼没关系，再看一遍，认真的解读MVP的设计思路，参考代码，发现其实真的是很清晰的思路，并不难，难的是啃下来的决心。这碗鸡汤我干了，你们随意。

相信看完这篇文章会对你理解MVP有所帮助，如果你还是一脸懵逼的话，请举起手来：
![还是一脸懵逼？](https://github.com/chengshengyang/test/blob/master/9.jpg?raw=true)

最后，奉上本文的源码[GitHub:Login-MVP-Architecture](https://github.com/chengshengyang/Login-MVP-Architecture)，如果觉得有用，点个Star表示支持。