package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({

    "카드를 추가하면 카드의 리스트의 수가 증가한다." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.ACE))
        cards.cards.size shouldBe 1
    }

    "카드들의 스코어를 계산한다." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.TEN))
        cards.add(Card(CardType.CLOVER, Denomination.KING))
        cards.score() shouldBe 20
    }

    "카드들의 스코어를 계산한다(ACE)." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.ACE))
        cards.add(Card(CardType.CLOVER, Denomination.KING))
        cards.score() shouldBe 21
    }

    "카드들의 스코어를 계산한다(ACE 2장)." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.ACE))
        cards.add(Card(CardType.HEART, Denomination.ACE))
        cards.add(Card(CardType.CLOVER, Denomination.KING))
        cards.score() shouldBe 12
    }
})
