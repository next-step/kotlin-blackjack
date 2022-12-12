package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardsTest {

    object FakeCards {
        val cards = Cards(
            listOf(
                Card.of(CardNumber.of("K"), CardShape.HEART),
                Card.of(CardNumber.of("4"), CardShape.DIAMOND),
                Card.of(CardNumber.of("7"), CardShape.SPADE)
            )
        )
    }

    @Test
    fun `카드의 점수를 카운팅한다`() {
        val cards = FakeCards.cards
        cards.countingCard() shouldBe 21
    }

    @ParameterizedTest
    @CsvSource("8,DIAMOND,21", "9,HEART,12", "Q,SPADE,13")
    fun `Ace 카드는 카드의 점수가 21이 넘으면 1로 21이 넘지 않으면 11로 계산된다`(cardNumber: String, cardShape: String, score: Int) {
        val cards = Cards(
            listOf(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.TWO, CardShape.SPADE),
                Card.of(CardNumber.of(cardNumber), CardShape.valueOf(cardShape))
            )
        )
        cards.countingCard() shouldBe score
    }
}
