# java http server

>简易java mvc 框架

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