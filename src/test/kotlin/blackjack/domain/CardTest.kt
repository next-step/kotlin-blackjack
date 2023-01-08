package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

internal class CardTest : FunSpec({
    context("Denomination이 ACE 이면 isAce()가 true이다.") {
        withData(
            ts = listOf(
                CardSuit.HEART to Denomination.ACE,
                CardSuit.CLOVER to Denomination.ACE,
            )
        ) { (cardSuit, denomination) ->
            Card(cardSuit, denomination).isAce() shouldBe true
        }
    }
})
