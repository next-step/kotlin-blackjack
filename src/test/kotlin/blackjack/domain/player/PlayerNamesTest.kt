package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerNamesTest : DescribeSpec({
    describe("PlayerNames.from()") {
        context("플레이어 이름이 문자열 리스트로 주어지면") {
            val names = listOf("홍길동", "백상어")

            val result = PlayerNames.from(names)

            it("주어진 문자열로 이름 목록이 생성된다") {
                result.value[0].value shouldBe "홍길동"
                result.value[1].value shouldBe "백상어"
            }
        }

        context("두 플레이어 이름이 동일하면") {
            val names = listOf("홍길동", "홍길동")

            it("플레이어 이름 생성에 실패한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    PlayerNames.from(names)
                }
            }
        }
    }
})
