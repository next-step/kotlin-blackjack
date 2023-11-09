package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.math.pow

class InfixLearn : StringSpec({
    "중위 표현법(infix-notation)을 학습한다" {
        println("참고자료 : https://kotlinlang.org/docs/functions.html#infix-notation")
        val description = """
        - 키워드 로 표시된 함수는 infix중위 표기법(호출 시 점과 괄호 생략)을 사용하여 호출할 수도 있습니다. 
        - 중위 함수는 다음 요구 사항을 충족해야 합니다.
          - 멤버 함수 또는 확장 함수 여야 합니다 .
          - 단일 매개변수가 있어야 합니다.
          - 매개변수는 가변 개수의 인수를 허용해서는 안 되며 기본값이 없어야 합니다 .
        """.trimIndent()
        println(description)
    }

    "중위표현법 예시" {
        val actual1 = 2 거듭제곱 3
        val mirror1 = 2.거듭제곱(3)
        val actual2 = 3 거듭제곱 2 더하기 7
        val actual3 = 7 더하기 3 거듭제곱 3 더하기 24
        actual1 shouldBe 8
        mirror1 shouldBe 8
        actual2 shouldBe 16
        actual3 shouldBe 1024
    }
})

infix fun Int.거듭제곱(x: Int): Int {
    return this.toDouble().pow(x).toInt()
}

infix fun Int.더하기(x: Int): Int {
    return this + x
}
