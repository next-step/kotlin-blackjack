package blackjack.application

import blackjack.SpadeAce
import blackjack.SpadeFour
import blackjack.SpadeThree
import blackjack.SpadeTwo
import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `카드 덱 - 카드 분배 테스트`() {
        // given
        val deck = Deck(PlayingCard(Suit.CLUBS, Denomination.ACE))

        // when
        val expected = PlayingCard(Suit.CLUBS, Denomination.ACE)

        // then
        assertThat(deck.getCard()).isEqualTo(expected)
    }

    @Test
    fun `카드 덱 - 카드 분배 예외처리 테스트, 카드가 없는 경우에 카드를 뽑는 경우`() {
        // given
        val deck = Deck(mutableListOf())

        // when
        val exception = assertThrows<NoSuchElementException> {
            deck.getCard()
        }

        // then
        assertThat(exception.message).isEqualTo("카드가 없습니다.")
    }

    @Test
    fun `카드 덱 - ShuffleStrategy 테스트`() {
        val cards = PlayingCards.shuffle { listOf(SpadeAce, SpadeTwo, SpadeThree, SpadeFour) }
        val deck = Deck(cards.toMutableList())

        assertThat(deck.getCard()).isEqualTo(SpadeAce)
        assertThat(deck.getCard()).isEqualTo(SpadeTwo)
        assertThat(deck.getCard()).isEqualTo(SpadeThree)
        assertThat(deck.getCard()).isEqualTo(SpadeFour)
    }
}
