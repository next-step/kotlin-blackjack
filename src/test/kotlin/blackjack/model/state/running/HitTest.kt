package blackjack.model.state.running

import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Suit
import blackjack.model.state.State
import blackjack.model.state.finished.Bust
import blackjack.model.state.finished.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HitTest {
    private lateinit var ACE_CLUB: Card
    private lateinit var TWO_CLUB: Card
    private lateinit var TEN_HEART: Card
    private lateinit var TEN_CLUB: Card
    private lateinit var TEN_SPADE: Card
    private lateinit var hitState: State

    @BeforeEach
    fun setUp() {
        ACE_CLUB = Card(CardScore.ACE, Suit.CLUB)
        TWO_CLUB = Card(CardScore.TWO, Suit.CLUB)
        TEN_CLUB = Card(CardScore.TEN, Suit.CLUB)
        TEN_SPADE = Card(CardScore.TEN, Suit.SPADE)
        TEN_HEART = Card(CardScore.TEN, Suit.HEART)

        hitState = Hit(Cards(ACE_CLUB, TEN_CLUB))
    }

    @Test
    fun `Bust인 경우`() {
        // when
        hitState = hitState.addCard(TWO_CLUB).addCard(TEN_SPADE)

        // then
        assertThat(hitState).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Stay인 경우`() {
        // when
        hitState = hitState.addCard(TEN_SPADE)

        // then
        assertThat(hitState).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `Hit인 경우`() {
        // when
        hitState = hitState.addCard(TWO_CLUB)

        // then
        assertThat(hitState).isInstanceOf(Hit::class.java)
    }
}
