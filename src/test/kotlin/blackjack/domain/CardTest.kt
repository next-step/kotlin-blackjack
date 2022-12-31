package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal class CardTest : FunSpec({
    context("Denomination이 ACE_1 또는 ACE_10 이면 ace 카드이다.") {
        data class CardTestData(
            val cardSuit: CardSuit,
            val denomination: Denomination,
        )

        withData(
            ts = listOf(
                CardTestData(cardSuit = CardSuit.HEART, denomination = Denomination.ACE_1),
                CardTestData(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE_10),
            )
        ) { (cardSuit, denomination) ->
            Card(cardSuit, denomination).isAce() shouldBe true
        }
    }

    context("Denomination에 따라 카드 점수를 계산할 수 있다.") {
        withData(
            ts = listOf(
                Denomination.ACE_1 to 1,
                Denomination.ACE_10 to 10,
                Denomination.TWO to 2,
                Denomination.THREE to 3,
                Denomination.FOUR to 4,
                Denomination.FIVE to 5,
                Denomination.SIX to 6,
                Denomination.SEVEN to 7,
                Denomination.EIGHT to 8,
                Denomination.NINE to 9,
                Denomination.TEN to 10,
                Denomination.JACK to 10,
                Denomination.QUEEN to 10,
                Denomination.KING to 10,
            )
        ) { (denomination, score) ->
            denomination.calc(1) shouldBe score + 1
        }
    }
})
