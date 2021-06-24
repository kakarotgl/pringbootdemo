package com.example.demo;

import com.example.demo.utils.ExcelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        String path = "D:\\品牌.xlsx";
        // 调用封装好的方法获取数据
        Set<Object> set;
        try {
            set = ExcelUtils.getExcelDataByColumn(2, path);
            List<String> result = set.stream().map(e->String.valueOf(e)).collect(Collectors.toList());
            System.out.println(result.size());
            System.out.println(set.toString());// 打印出第三列的所有数据
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
