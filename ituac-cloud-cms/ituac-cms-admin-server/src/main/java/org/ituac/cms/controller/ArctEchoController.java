package org.ituac.cms.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ituac
 */


@RestController
@RequestMapping("/ar/echo")
public class ArctEchoController {


    /**
     * @return echo信息
     */
    @GetMapping("/feign/info/{title}")
    public String echo(@PathVariable String title) {
        System.out.println("log cms >>>>>>>>> " + title);
        return "feign cms echo :" + title;
    }


}
