package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource

class CardDeckTest {
    @Test
    fun `덱은 총 52장의 카드를 가진다`() {
        val cardDeck = CardDeck()

        assertThat(cardDeck.cards.size).isEqualTo(52)
    }

    @ParameterizedTest
    @EnumSource(CardSymbol::class)
    fun `덱은 문양별로 1에서 13번 까지의 카드를 가진다`(symbol: CardSymbol) {
        val cardDeck = CardDeck()

        assertThat(cardDeck.cards).containsAll(CardNumber.values().map { Card(symbol, it) })
    }

    @Test
    fun `덱에서 카드 한장을 뽑을 수 있다`() {
        val cardDeck = CardDeck()
        cardDeck.drawOne()

        assertThat(cardDeck.cards.size).isEqualTo(51)
    }

    @ParameterizedTest
    @CsvSource("1,51", "10,42", "52,0")
    fun `덱에서 카드 여러장을 뽑을 수 있다`(drawCount: Int, expectedRemainingCount: Int) {
        val cardDeck = CardDeck()
        val cards = cardDeck.drawMany(drawCount = drawCount)

        assertThat(cards.size).isEqualTo(drawCount)
        assertThat(cardDeck.cards.size).isEqualTo(expectedRemainingCount)
    }
}
