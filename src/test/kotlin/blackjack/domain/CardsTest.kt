package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : StringSpec({
    "카드를 뽑을 수 있다." {
        val cards = Cards()
        val card = Card(CardSuit.HEART, Denomination.ACE)
        cards.add(card)
        cards.size shouldBe 1
    }

    "가장 위의 카드를 꺼낼 수 있다." {
        val cards = Cards()
        listOf(
            Card(CardSuit.HEART, Denomination.ACE),
            Card(CardSuit.DIAMOND, Denomination.FIVE)
        ).forEach {
            cards.add(it)
        }

        val card = cards.pop()
        card shouldBe Card(CardSuit.HEART, Denomination.ACE)
        cards.size shouldBe 1
    }

    "카드의 점수를 계산하면 정상적으로 점수를 계산할 수 있다." {
        val cards = Cards()
        cards.add(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.QUEEN))
        cards.add(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.EIGHT))
        cards.add(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.TWO))

        cards.getScore() shouldBe 20
    }

    "카드의 점수를 계산할 때, ACE 점수가 11로 사용되는 경우를 계산할 수 있다." {
        val cards = Cards()
        cards.add(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE))

        cards.getScore() shouldBe 11
    }

    "카드의 점수를 계산할 때, ACE 점수가 1로 사용되는 경우를 계산할 수 있다." {
        val cards = Cards()
        cards.add(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.KING))
        cards.add(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.FIVE))
        cards.add(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE))

        cards.getScore() shouldBe 16
    }
})
