package study

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.StringBuilder

class Dsl {

    @Test
    fun `apply는 수신 객체를 반환하지만, with는 람다를 호출해 얻은 결과를 반환한다`() {
        val map = mutableMapOf(1 to "one")
        val applyResultThis = map.apply { this[2] = "two" }
        assertEquals(applyResultThis, mapOf(1 to "one", 2 to "two"))

        val withResultBlock = with(map) { this[3] = "three" }
        assertEquals(withResultBlock, Unit)

        assertEquals(map, mapOf(1 to "one", 2 to "two", 3 to "three"))
    }

    @Test
    fun `람다를 인자로 받는 buildString() 정의하기`() {
        val result = buildStringWithLambda {
            it.append("Hello, ")
            it.append("람다 인자")
        }
        assertEquals(result, "Hello, 람다 인자")
    }

    @Test
    fun `수신 객체 지정 람다 사용해 다시 정의한 buildString`() {
        val result = buildString {
            append("Hello, ")
            append("수신 객체 지정 람다")
        }
        assertEquals(result, "Hello, 수신 객체 지정 람다")
    }

    @Test
    fun `수신 객체 지정 람다를 변수에 지정하기`() {
        val appendExcel: StringBuilder.() -> Unit = { this.append("!") } // appendExcel은 확장 함수 타입의 값이다

        val stringBuilder = StringBuilder("HI")
        stringBuilder.appendExcel() // appendExcel을 확장함수처럼 호출할 수 있다.
        println(stringBuilder) // HI!
        println(buildString(appendExcel))
    }

    private fun buildString(buildAction: StringBuilder.() -> Unit): String {
        return StringBuilder().apply(buildAction).toString()
    }

    private fun buildStringWithLambda(builderAction: (StringBuilder) -> Unit): String {
        val sb = StringBuilder()
        builderAction(sb)
        return sb.toString()
    }
}
