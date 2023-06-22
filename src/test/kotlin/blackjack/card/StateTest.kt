package blackjack.card

import domain.card.BlackjackCard
import domain.card.BlackjackCards
import domain.card.CardNumber
import domain.card.Suit
import domain.state.Blackjack
import domain.state.Burst
import domain.state.Hit
import domain.state.Stand
import domain.state.StartState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StateTest {

    @Test
    fun `각 상태는 카드들의 정보를 제공`() {
        val cards = BlackjackCards(
            listOf(
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.TWO),
            ),
        )
        val start = StartState.start(cards[0], cards[1])
        val hit = Hit(cards)
        val stand = Stand(cards)
        val burst = Burst(cards)
        val blackjack = Blackjack(cards)

        assertThat(start.getCards()).isEqualTo(cards)
        assertThat(hit.getCards()).isEqualTo(cards)
        assertThat(stand.getCards()).isEqualTo(cards)
        assertThat(burst.getCards()).isEqualTo(cards)
        assertThat(blackjack.getCards()).isEqualTo(cards)
    }
}
