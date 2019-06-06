package com.cccts.pojos;

/**
 * 自定义的一个泛型元祖类，在一个方法返回两个参数时使用
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A, B> {
    private final A first;
    private final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }
}
