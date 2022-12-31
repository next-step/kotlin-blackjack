package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : StringSpec({
    "카드를 뽑을 수 있다." {
        val cards = Cards()
        val card = Card(CardSuit.HEART, Denomination.ACE_1)
        cards.add(card)
        cards.cards.size shouldBe 1
    }
})
