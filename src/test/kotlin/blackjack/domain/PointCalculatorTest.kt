package blackjack.domain

import blackjack.domain.model.Card
import blackjack.domain.model.CardType
import blackjack.domain.model.CardValue
import blackjack.domain.model.Cards
import blackjack.domain.model.Game
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PointCalculatorTest {
    @Test
    fun `숫자 계산은 카드의 숫자를 기본으로 한다`() {
        val cards = mutableListOf(
            Card.from(CardType.SPADE, CardValue.TWO),
            Card.from(CardType.CLOVER, CardValue.THREE),
            Card.from(CardType.DIAMOND, CardValue.SIX),
            Card.from(CardType.HEART, CardValue.EIGHT),
        )
        PointCalculator.sum(Cards(cards, Game())) shouldBe 19
    }

    @Test
    fun `J, Q, K는 10으로 계산한다`() {
        val cards = mutableListOf(
            Card.from(CardType.SPADE, CardValue.TWO),
            Card.from(CardType.CLOVER, CardValue.THREE),
            Card.from(CardType.DIAMOND, CardValue.SIX),
            Card.from(CardType.SPADE, CardValue.KING)
        )
        PointCalculator.sum(Cards(cards, Game())) shouldBe 21
    }

    @Test
    fun `A를 11로 계산했을 때 카드 숫자의 총 합이 21를 초과하면 A는 1로 계산한다`() {
        val cards = mutableListOf(
            Card.from(CardType.SPADE, CardValue.TWO),
            Card.from(CardType.DIAMOND, CardValue.EIGHT),
            Card.from(CardType.SPADE, CardValue.ACE)
        )
        PointCalculator.sum(Cards(cards, Game())) shouldBe 21
    }

    @Test
    fun `A를 11로 계산했을 때 카드 숫자의 총 합이 21를 미만이면 A는 11로 계산한다`() {
        val cards = mutableListOf(
            Card.from(CardType.SPADE, CardValue.JACK),
            Card.from(CardType.DIAMOND, CardValue.KING),
            Card.from(CardType.SPADE, CardValue.ACE)
        )
        PointCalculator.sum(Cards(cards, Game())) shouldBe 21
    }
}
