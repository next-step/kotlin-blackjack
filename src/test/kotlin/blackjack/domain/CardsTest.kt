package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : StringSpec({
    "카드를 뽑을 수 있다." {
        val cards = Cards(
            mutableListOf(Card(CardSuit.HEART, Denomination.ACE))
        )

        cards.size shouldBe 1
    }

    "가장 위의 카드를 꺼낼 수 있다." {
        val cards = Cards(
            mutableListOf(
                Card(CardSuit.HEART, Denomination.ACE),
                Card(CardSuit.DIAMOND, Denomination.FIVE)
            )
        )

        val card = cards.pop()
        card shouldBe Card(CardSuit.HEART, Denomination.ACE)
        cards.size shouldBe 1
    }

    "카드의 점수를 계산하면 정상적으로 점수를 계산할 수 있다." {
        val cards = Cards(
            mutableListOf(
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.QUEEN),
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.EIGHT),
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.TWO),
            )
        )

        cards.getScore() shouldBe 20
    }

    "카드의 점수를 계산할 때, ACE 점수가 11로 사용되는 경우를 계산할 수 있다." {
        val cards = Cards(
            mutableListOf(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE))
        )

        cards.getScore() shouldBe 11
    }

    "카드의 점수를 계산할 때, ACE 점수가 1로 사용되는 경우를 계산할 수 있다." {
        val cards = Cards(
            mutableListOf(
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.KING),
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.FIVE),
                Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE)
            )
        )

        cards.getScore() shouldBe 16
    }
})
