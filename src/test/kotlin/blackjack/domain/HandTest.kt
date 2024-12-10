package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "빈 카드 목록으로 Hand를 생성할 수 있다." {
        val hand = Hand()
        hand.getCards() shouldBe emptyList()
        hand.calculateScore() shouldBe 0
    }

    "카드를 추가하면 새로운 Hand 인스턴스를 반환한다." {
        val initialHand = Hand()
        val newCards = listOf(
            Card.of(CardNumber.TEN, CardShape.HEART),
            Card.of(CardNumber.FIVE, CardShape.CLUB)
        )

        val updatedHand = initialHand.addCards(newCards)
        updatedHand.getCards().size shouldBe 2
        updatedHand.getCards() shouldBe newCards
        initialHand.getCards() shouldBe emptyList()
    }

    "점수는 카드 점수를 정확히 계산한다 - 일반 카드 조합" {
        val hand = Hand().addCards(
            listOf(
                Card.of(CardNumber.TEN, CardShape.HEART),
                Card.of(CardNumber.SIX, CardShape.CLUB)
            )
        )

        hand.calculateScore() shouldBe 16
    }

    "점수는 ACE를 11로 계산한다." {
        val hand = Hand().addCards(
            listOf(
                Card.of(CardNumber.FIVE, CardShape.HEART),
                Card.of(CardNumber.ACE, CardShape.SPADE)
            )
        )

        hand.calculateScore() shouldBe 16
    }

    "점수는 ACE를 1로 계산한다 - 합이 21을 초과하는 경우" {
        val hand = Hand().addCards(
            listOf(
                Card.of(CardNumber.TEN, CardShape.HEART),
                Card.of(CardNumber.SIX, CardShape.CLUB),
                Card.of(CardNumber.ACE, CardShape.DIAMOND)
            )
        )

        hand.calculateScore() shouldBe 17
    }

    "점수는 여러 ACE를 적절히 계산한다." {
        val hand = Hand().addCards(
            listOf(
                Card.of(CardNumber.ACE, CardShape.HEART),
                Card.of(CardNumber.ACE, CardShape.CLUB),
                Card.of(CardNumber.NINE, CardShape.SPADE)
            )
        )

        hand.calculateScore() shouldBe 21
    }
})