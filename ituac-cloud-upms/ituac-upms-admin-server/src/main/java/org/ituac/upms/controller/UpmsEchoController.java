package org.ituac.upms.controller;


//import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.Reference;
import org.ituac.api.dubbo.RemoteEchoServiceDubbo;
import org.ituac.api.feign.RemoteEchoServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upms/echo")
public class UpmsEchoController {

    @Autowired
    private RemoteEchoServiceFeign remoteEchoServiceFeign;

    @Reference
    private RemoteEchoServiceDubbo remoteEchoServiceDubbo;


    /**
     * @return 用户信息
     */
    @GetMapping("/feign/info/{title}")
    public String echoFeign(@PathVariable String title) {
        return remoteEchoServiceFeign.echo(title);
    }

    /**
     * @return 用户信息
     */
    @GetMapping("/dubbo/info/{title}")
    public String echoDubbo(@PathVariable String title) {
        return remoteEchoServiceDubbo.echo(title);
    }



}
