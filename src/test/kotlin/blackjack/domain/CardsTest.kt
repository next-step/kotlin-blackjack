package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({

    "카드를 추가하면 카드의 리스트의 수가 증가한다." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.ACE))
        cards.cards.size shouldBe 1
    }
})
