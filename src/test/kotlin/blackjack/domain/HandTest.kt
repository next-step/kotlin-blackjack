package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class HandTest : FreeSpec({

    "주어진 카드를 통해 인스턴스를 생성한다" {
        val cards = listOf(
            Card(Suite.CLUBS, Denomination.JACK),
            Card(Suite.DIAMONDS, Denomination.KING)
        )

        val hand = Hand(cards)

        hand.count shouldBe cards.size
    }
})
