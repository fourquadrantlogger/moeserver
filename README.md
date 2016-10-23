# java http server

>萌萌哒的java restful api框架

+ 0.0.1 采用了sun.HttpServer线程模型
+ 0.0.2 采用了netty4引擎和jetty引擎

## using maven
+ 0.0.1 采用了sun.HttpServer线程模型
```
<dependency>
  <groupId>com.github.timeloveboy</groupId>
  <artifactId>moeserver</artifactId>
  <version>0.0.1</version>
</dependency>
```

+ 0.0.2 添加了netty4和jetty引擎
```
<dependency>
  <groupId>com.github.timeloveboy</groupId>
  <artifactId>moeserver</artifactId>
  <version>0.0.2</version>
</dependency>
```
## 使用说明

+ 1.指定您的modulepackage路径和端口号
+ 2.在modulepackage中，每个类均继承自 extends DefaultHandle


```
public class Buy extends DefaultHandle {
    @Override
    public void GET(IHttpRequest req, IHttpResponse resp) throws IOException {
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
```
Server s = Server.getInstance();
s.RegisterDriver(new nettyServer());
s.RegisterDriver(new sunServer());
s.RegisterDriver(new jettyServer());
s.RegisterModulePath("webdemo.routers").SetPort(8090);
s.Run();
```
#### Handle
请覆盖DefauldHandle类的以下方法

+ GET
+ POST
+ PUT
+ HEAD
+ DELETE

> moeserver与restful风格的api设计规范可以完美搭配。
您可以：

+ 在get中执行数据的select操作
+ 在post中执行insert操作
+ 在put中执行update操作
+ 在delete中执行delete操作。

#### Request
```
   public InetSocketAddress getRemoteAddress()
   
   public InputStream getBody()
   
   public String getRequestMethod() 

   public URI getUrl() 

   public Map getHeaders()

   public Map<String, String> getCookies()
```


#### Response
```
public IHttpResponse code(Integer responsecode) 

public IHttpResponse header(String key, String value)

public IHttpResponse setcookie(String key, String value) 

public void write(String data) 

public void write(byte[] data) 

public void close() 
```

## 不服跑个分

### 机器
```
ubuntu 16.04
15.7 GiB
Intel® Xeon(R) CPU X5650 @ 2.67GHz × 12
64 位
```

### c=10 n=10000
```
localhost:~ timeloveboy$ ab -n 10000 -c 10  http://localhost:8090/I
```
+ sun.线程模式 12688.99 [#/sec] (mean)

+ netty事件模式 19350.82 [#/sec] (mean)

+ jetty  16883.56 [#/sec] (mean)

+ golang 19955.14 [#/sec] (mean)
### c=100 n=100000

```
localhost:~ timeloveboy$ ab -n 100000 -c 100  http://localhost:8090/I
```
+ sun.线程模式  10409.97 [#/sec] (mean)

+ netty事件模式 18835.13 [#/sec] (mean)

+ jetty 18025.67 [#/sec] (mean)

+ golang 20826.30 [#/sec] (mean)
