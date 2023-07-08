package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "카드가 2장이고 21인 경우 블랙잭이다" {
        val cards = Cards(
            mutableSetOf(
                Card(CardNumber.CARD_ACE, CardType.CLOVER),
                Card(CardNumber.CARD_QUEEN, CardType.CLOVER)
            )
        )
        cards.isBlackJack() shouldBe true
    }
    "카드가 3장이고 21인 경우 블랙잭이 아니다" {
        val cards = Cards(
            mutableSetOf(
                Card(CardNumber.CARD_SIX, CardType.CLOVER),
                Card(CardNumber.CARD_QUEEN, CardType.CLOVER),
                Card(CardNumber.CARD_FIVE, CardType.CLOVER)
            )
        )
        cards.isBlackJack() shouldBe false
    }

    "카드가 합이 21이 넘는 경우 베팅 금액을 모두 잃는다(Bust)" {
        val cards = Cards(
            mutableSetOf(
                Card(CardNumber.CARD_SIX, CardType.CLOVER),
                Card(CardNumber.CARD_QUEEN, CardType.CLOVER),
                Card(CardNumber.CARD_SIX, CardType.DIAMOND)
            )
        )
        cards.isBust() shouldBe true
    }
})
