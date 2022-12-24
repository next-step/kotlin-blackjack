package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class CardValueTest : FunSpec({
    context("카드 값 더하기") {
        test("카드의 값을 더한 결과를 반환한다.") {
            table(
                headers("values", "expectedResult"),
                row(listOf(CardValue.JACK, CardValue.QUEEN), 20),
                row(listOf(CardValue.QUEEN, CardValue.KING), 20),
                row(listOf(CardValue.KING, CardValue.TEN), 20),
                row(listOf(CardValue.TWO, CardValue.TEN), 12),
            ).forAll { values, expectedResult ->
                CardValue.sum(values) shouldBe expectedResult
            }
        }
        test("ACE 제외 카드의 합이 10 이하일 경우 ACE = 11," +
                "10 초과일 경우 ACE = 1로 계산하여 카드의 값을 더한 결과를 반환한다.") {
            table(
                headers("values", "expectedResult"),
                row(listOf(CardValue.JACK, CardValue.ACE), 21),
                row(listOf(CardValue.TEN, CardValue.ACE), 21),
                row(listOf(CardValue.QUEEN, CardValue.KING, CardValue.ACE), 21),
                row(listOf(CardValue.EIGHT, CardValue.TWO, CardValue.ACE), 21),
            ).forAll { values, expectedResult ->
                CardValue.sum(values) shouldBe expectedResult
            }
        }
    }
})
