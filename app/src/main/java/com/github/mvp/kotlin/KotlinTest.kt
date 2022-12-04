package com.github.mvp.kotlin

/**
 * Created by chengshengyang on 2019/7/1.
 * @author chengshengyang
 */
class KotlinTest {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    interface Foo<out T : Any> : Bar {
        fun foo(a: Int): Int
    }

    open class Human(id: Int, name: String)

    class Person(
            id: Int,
            name: String,
            surname: String
    ) : Human(id, name),
        Foo<String> {
        override fun foo(a: Int): Int {
            return a
        }
    }
}