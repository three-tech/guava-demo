package com.xin.common;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Three
 * @since 18/6/6上午11:14
 */
public class ObjectTest {
    public static void main(String[] args) {

        System.out.println("********equal********");
        System.out.println(Objects.equal("a", "a"));//true
        System.out.println(Objects.equal(null, "a"));//false
        System.out.println(Objects.equal("a", null));//false
        System.out.println(Objects.equal(null, null));//true


        System.out.println("********hashCode********");
        String field = "test";
        Map<String, Object> map = Maps.newHashMap();
        System.out.println(Objects.hashCode(field, map));


        System.out.println("********compareTo********");
        Foo a = new Foo("a", 1, 1);
        System.out.println(a.compareTo(new Foo("b", 2, 1)));


        System.out.println("********ordering********");
//        natural() 对可排序类型做自然排序,如数字按大小,日期按先后排序
//        usingToString() 按对象的字符串形式做字典排序[lexicographical ordering]
//        from(Comparator) 把给定的 Comparator 转化为排序器
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, Integer>() {
            public Integer apply(Foo foo) {
                return foo.sortedBy;
            }
        });
        List<Foo> foos = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            foos.add(new Foo("string" + i, i, i));
        }
        System.out.println(ordering.isOrdered(foos));
        System.out.println(JSON.toJSONString(ordering.greatestOf(foos,10)));

    }
}

@AllArgsConstructor
@Data
class Foo {
    String aString;
    int anInt;
    int sortedBy;

    int compareTo(Foo that) {
        return ComparisonChain.start()
                .compare(this.aString, that.aString)
                .compare(this.anInt, that.anInt)
                .result();
    }

}