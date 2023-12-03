package blackjack

import blackjack.domain.Card
import blackjack.domain.Card.Companion.CARDS
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.Participant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParticipantTest {
    private val name = "참가자"

    @Test
    fun `카드 조회`() {
        // Given
        val deck = Deck(CARDS)

        // When
        val card = deck.getCard()
        val participant = Participant(name, listOf(card))

        // Then
        assertThat(participant.cards.first()).isEqualTo(card)
    }

    @Test
    fun `카드 추가`() {
        // Given
        val deck = Deck(CARDS)
        val participant = Participant(name, deck.getCards(2))

        // When
        val card = deck.getCard()
        participant.addCard(card)

        // Then
        assertThat(participant.cards.last()).isEqualTo(card)
    }

    @Test
    fun `스코어 조회`() {
        // Given
        val deck = Deck(CARDS)
        val card = deck.getCard()
        val participant = Participant(name, listOf(card))

        // When
        val score = if (card.number == CardNumber.ACE) {
            card.number.getAceNumber(0)
        } else {
            card.number.score
        }

        // Then
        assertThat(participant.getScore()).isEqualTo(score)
    }
}
