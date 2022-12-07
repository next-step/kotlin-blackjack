package blackjack.application

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `카드 덱 - 카드 생성 테스트`() {
        // given
        val deck = Deck {
            PlayingCards(Suit.values().flatMap { suit -> Denomination.values().map { denomination -> PlayingCard(suit, denomination) } })
        }

        // when
        val playingCards = PlayingCards(listOf())
        repeat(NUMBER_OF_PLAYING_CARDS) {
            playingCards.add(deck.getCard())
        }

        // then
        assertThat(playingCards.list).isEqualTo(Suit.values().flatMap { suit -> Denomination.values().map { denomination -> PlayingCard(suit, denomination) } })
    }

    @Test
    fun `카드 덱 - 카드 분배 예외처리 테스트, 카드가 없는 경우에 카드를 뽑는 경우`() {
        // given
        val deck = Deck {
            PlayingCards(listOf())
        }

        // when
        val exception = assertThrows<NoSuchElementException> {
            deck.getCard()
        }

        // then
        assertThat(exception.message).isEqualTo("카드가 없습니다.")
    }

    companion object {
        private const val NUMBER_OF_PLAYING_CARDS = 52
    }
}
