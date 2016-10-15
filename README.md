# java http server

>简易java mvc 框架

## using maven
```
<dependency>
  <groupId>com.github.timeloveboy</groupId>
  <artifactId>moeserver</artifactId>
  <version>0.0.1</version>
</dependency>
```
## 使用说明

+ 1.指定您的modulepackage路径和端口号
+ 2.在modulepackage中，每个类均继承自 extends DefaultHandle


```
public class Buy extends DefaultHandle {
    @Override
    public void GET(HttpRequest req, HttpResponse resp) throws IOException {
        resp.write("get");
    }
}
```
```
public class app {
    public static void main(String[] args) throws Exception {
        Server s = Server.getInstance().RegisterModulePath("com.moe.module").SetPort(8090);
        s.Run();
    }
}

```

现在，请访问[http://localhost:8090/Buy](http://localhost:8090/Buy)

## 函数列表
#### Server
+ getInstance()
+ SetPort(80)
+ RegisterModulePath("com.website.module")
+ Run()

#### Handle
请覆盖DefauldHandle类的以下方法

+ GET
+ POST
+ PUT
+ HEAD
+ DELETE

> moeserver与restful风格的api设计规范可以完美搭配。
您可以：

＋ 在get中执行数据的select操作
＋ 在post中执行insert操作
＋ 在put中执行update操作
＋ 在delete中执行delete操作。

#### Request
以下成员均可访问
```
    public final InetSocketAddress remoteAddress;
    public final InputStream body;
    public final String requestMethod;
    public final URI url;
    public final Headers headers;
    public final Map<String, String> cookies;
```


#### Response
+ code(200)
+ header("Content-Type","text/html")
+ setcookie("Content-Type","text/html")
+ write("")
+ write(bytes)
+ close()
