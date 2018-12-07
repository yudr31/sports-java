package com.spring.boot.sports.shiro;

/**
 * @author yuderen
 * @version 2018/9/13 18:24
 */
public interface Hash extends ByteSource {
    String getAlgorithmName();

    ByteSource getSalt();

    int getIterations();
}