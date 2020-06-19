package org.ituac.cms.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;

/**
 * @author ituac
 */


@RestController
@RequestMapping("/cms/echo")
public class CmsEchoController {


    /**
     * @return echo信息
     */
    @GetMapping("/feign/info/{title}")
    public String echo(@PathVariable String title) {
        System.out.println("log cms >>>>>>>>> " + title);
        return "feign cms echo :" + title;
    }


}
