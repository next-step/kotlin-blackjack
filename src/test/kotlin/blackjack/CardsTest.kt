package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : StringSpec({
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

    "랜덤으로 1개의 카드를 뽑을 수 있고, 카드 사이즈는 줄어든다." {
        val list = listOf(
            Card(Suite.SPADE, Denomination.ACE),
            Card(Suite.CLOVER, Denomination.EIGHT),
            Card(Suite.HEART, Denomination.JACK)
        )
        val cards = Cards(list)
        cards.values.size shouldBe 3
        cards.pick()
        cards.values.size shouldBe 2
    }
})
