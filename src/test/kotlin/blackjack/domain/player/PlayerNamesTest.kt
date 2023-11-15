package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerNamesTest : DescribeSpec({
    describe("플레이어 이름 목록 생성") {
        context("플레이어 이름이 문자열 리스트로 주어지면") {
            val names = listOf("홍길동", "백상어")

            val result = PlayerNames.of(names)

            it("주어진 문자열로 이름 목록이 생성") {
                result.value[0].value shouldBe "홍길동"
                result.value[1].value shouldBe "백상어"
            }
        }
    }

    describe("플레이어 이름 수 검증") {
        context("이름의 수가 2개가 아니면") {
            forAll(
                row(listOf(PlayerName("하나"), PlayerName("둘"), PlayerName("셋"))), row(listOf(PlayerName("하나")))
            ) { names ->
                it("생성 실패") {
                    shouldThrowExactly<IllegalArgumentException> {
                        PlayerNames(names)
                    }
                }
            }
        }
    }
})
