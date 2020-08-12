package blackjack

import blackjack.domain.Deck
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class DeckTest {

    private val deck = Suit.values().flatMap { Deck.cardPackOfSuit(it) }.toMutableSet()

    @DisplayName("카드 한 장을 반환하고, 덱에서 해당 카드를 제거한다")
    @Test
    fun `provide a card`() {
        // when
        val cards = listOf(Deck.provideCard(deck))

        // then
        assertThat(cards.size).isEqualTo(1)
        assertFalse(deck.contains(cards[0]))
    }

    @DisplayName("해당 카드 모양의 카드 팩을 반환한다")
    @Test
    fun `make card pack of suit`() {
        // when
        val cards = Deck.cardPackOfSuit(Suit.CLUB)

        // then
        assertThat(cards.size).isEqualTo(13)
    }

    @DisplayName("Deck이 비어있으면 IllegalStateException을 발생시킨다")
    @Test
    fun `maximum of deck`() {

        assertThatThrownBy {
            // when
            repeat(500) { Deck.provideCard(deck) }

            // then
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Deck has no card")
    }
}
