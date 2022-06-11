package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardShape
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class PlayerCardDeckTest : AnnotationSpec() {

    @Test
    fun `CardPoint의 합이 반환된다`() {
        val sut = PlayerCardDeck.of(
            mutableListOf(
                Card(CardNumber.TWO, CardShape.CLOVER),
                Card(CardNumber.EIGHT, CardShape.HEART),
            )
        )

        val result = sut.pointSum

        result shouldBe 10
    }
}
