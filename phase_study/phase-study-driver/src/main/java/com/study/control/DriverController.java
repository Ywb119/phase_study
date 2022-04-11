package com.study.control;

import com.study.model.Car;
import com.study.model.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping(value = "/driver")
@Slf4j
public class DriverController {

    /****
     * 司机信息
     */
    @GetMapping(value = "/info/{id}")
//    @RequestMapping(value = "/info/{id}")
    public Driver info(@PathVariable(value = "id") String id, HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        return new Driver(id, "张三", 1f,
                new Car("1", "HK-001", "丰田", "黑色", 1, 1), 1);
    }

    @Value("${server.port}")
    private int port;

    /****
     * 更新司机信息
     */
    @PutMapping(value = "/status/{id}/{status}")
    public Driver status(@PathVariable(value = "id") String id, @PathVariable(value = "status") Integer status) {
        log.info("当前服务占用的端口为:{}", port);
        //修改状态后的司机信息
        return new Driver("1001", "张三", 1f,
                new Car("1", "HK-001", "丰田", "黑色", 1, 1), 1);
    }
}
