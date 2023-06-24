package blackjack.card

import blackjack.card.helper.BlackjackCardsTestHelper
import domain.card.Cards
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardsTest {

    @ParameterizedTest
    @MethodSource("getCardNumberSumLessThanEquals21")
    fun `카드들의 숫자 합이 21 미만이라면 카드를 뽑을 수 있다`(cards: Cards) {
        cards.isDrawable() shouldBe true
    }

    @ParameterizedTest
    @MethodSource("getCardNumberSum")
    fun `카드들의 숫자 합은 21 또는 21에 가장 가까운 숫자를 가진다`(cards: Cards, expected: Int) {
        cards.sum shouldBe expected
    }

    companion object {
        @JvmStatic
        fun getCardNumberSumLessThanEquals21(): List<Arguments> =
            BlackjackCardsTestHelper.getCardNumberSumLessThanEquals21()

        @JvmStatic
        fun getCardNumberSum(): List<Arguments> = BlackjackCardsTestHelper.getCardNumberSum()
    }
}
