package com.functionalInterface;

@FunctionalInterface
public interface CheckedExceptionConsumerHandler<Target, ExObj extends Exception> {

    public void accept(Target t) throws Exception;
}
