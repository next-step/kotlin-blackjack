package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardsTest: StringSpec({
    "카드를 추가하면 리스트의 사이즈가 증가한다." {
        val cards = Cards()
        cards.values.size shouldBe 0

        val addedCards = cards.add(Card(Suite.SPADE, Denomination.SEVEN))
        addedCards.values.size shouldBe 1
    }

    "카드 리스트는 불변객체이다" {
        val cards = Cards()
        cards.add(Card(Suite.HEART, Denomination.ACE))
        cards.values.size shouldBe 0
    }
})