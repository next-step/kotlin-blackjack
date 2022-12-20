package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal class CardNumberTest : FunSpec({
    context("주어진 숫자를 기반으로 점수를 계산한다.") {
        withData(
            listOf(
                CardNumberFixture(0, CardNumber.ACE, 1),
                CardNumberFixture(0, CardNumber.NUM_2, 2),
                CardNumberFixture(0, CardNumber.NUM_3, 3),
                CardNumberFixture(0, CardNumber.NUM_4, 4),
                CardNumberFixture(0, CardNumber.NUM_5, 5),
                CardNumberFixture(0, CardNumber.NUM_6, 6),
                CardNumberFixture(0, CardNumber.NUM_7, 7),
                CardNumberFixture(0, CardNumber.NUM_8, 8),
                CardNumberFixture(0, CardNumber.NUM_9, 9),
                CardNumberFixture(0, CardNumber.NUM_10, 10),
                CardNumberFixture(0, CardNumber.KING, 10),
                CardNumberFixture(0, CardNumber.QUEEN, 10),
                CardNumberFixture(0, CardNumber.JACK, 10),
            )
        ) { cnf ->
            cnf.cardNumber.calc(cnf.initialNumber) shouldBe cnf.result
        }
    }
})

data class CardNumberFixture(
    val initialNumber: Int,
    val cardNumber: CardNumber,
    val result: Int,
)
