package blackjack.card

import domain.card.Card
import domain.card.Cards
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
        val cards = Cards(
            listOf(
                Card(suit = Suit.SPADE, number = CardNumber.ACE),
                Card(suit = Suit.SPADE, number = CardNumber.TWO),
            ),
        )
        val start = StartState.start(cards[0], cards[1])
        val hit = Hit(cards)
        val stand = Stand(cards)
        val burst = Burst(cards)
        val blackjack = Blackjack(cards)

        assertThat(start.getCards()).containsAll(cards)
        assertThat(hit.getCards()).containsAll(cards)
        assertThat(stand.getCards()).containsAll(cards)
        assertThat(burst.getCards()).containsAll(cards)
        assertThat(blackjack.getCards()).containsAll(cards)
    }
}
