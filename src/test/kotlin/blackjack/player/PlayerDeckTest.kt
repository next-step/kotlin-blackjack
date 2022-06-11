package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardShape
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class PlayerDeckTest : AnnotationSpec() {

    @Test
    fun `CardPoint의 합이 반환된다`() {
        val sut = PlayerDeck.of(
            listOf(
                Card(CardNumber.TWO, CardShape.CLOVER),
                Card(CardNumber.EIGHT, CardShape.HEART),
            )
        )

        val result = sut.pointSum

        result shouldBe 10
    }

    @Test
    fun `CardPoint의 합이 21을 초과하지 않으면 Ace는 11으로 계산된다`() {
        val sut = PlayerDeck.of(
            listOf(
                Card(CardNumber.ACE, CardShape.CLOVER),
            )
        )

        val result = sut.pointSum

        result shouldBe 11
    }

    @Test
    fun `CardPoint의 합이 21을 초과하면 Ace는 1로 계산된다`() {
        val sut = PlayerDeck.of(20)
        sut.add(Card(CardNumber.ACE, CardShape.CLOVER))

        val result = sut.pointSum

        result shouldBe 21
    }
}
