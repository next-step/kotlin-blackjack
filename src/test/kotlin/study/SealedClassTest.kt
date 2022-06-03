package study

import org.junit.jupiter.api.Test

class SealedClassTest {
    @Test
    internal fun `sealedClass는 컴파일 시점에 자식을 안다`(family: Family) {
        when (family) {
            is Father -> "father"
            is Mother -> "mother"
            is Son -> "Son"
        }
    }
}

sealed class Family

class Father(val age: Int) : Family()
class Mother(val age: Int) : Family()
class Son(val age: Int) : Family()
