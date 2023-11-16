package blackjack.view

import blackjack.view.input.InputParser
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class InputParserTest : StringSpec({
    "플레이어 이름 입력을 받으면 쉽표를 구분자로 플레이어 이름이 구분" {
        val input = "홍길동,백상어"

        val result = InputParser.intoPlayerNames(input)

        result.size shouldBe 2
        result[0] shouldBe "홍길동"
        result[1] shouldBe "백상어"
    }
})
