package blackjack.model.state.finished

import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Suit
import blackjack.model.state.State
import blackjack.model.state.running.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackTest {
    private lateinit var ACE_CLUB: Card
    private lateinit var TEN_CLUB: Card
    private lateinit var TWO_CLUB: Card
    private lateinit var NINE_CLUB: Card
    private lateinit var blackJackState: State

    @BeforeEach
    fun setUp() {
        ACE_CLUB = Card(CardScore.ACE, Suit.CLUB)
        TWO_CLUB = Card(CardScore.TWO, Suit.HEART)
        NINE_CLUB = Card(CardScore.NINE, Suit.HEART)
        TEN_CLUB = Card(CardScore.TEN, Suit.SPADE)

        blackJackState = BlackJack(Cards(ACE_CLUB, TEN_CLUB))
    }

    @Test
    fun `딜러, 플레이어 모두 BlackJack인 경우 profit`() {
        // when
        val profit = blackJackState.profit(BlackJack(Cards(ACE_CLUB, TEN_CLUB)), 1000)

        // then
        assertThat(profit).isEqualTo(0.0)
    }

    @Test
    fun `플레이어만 BlackJack인 경우 profit`() {
        // when
        val profit = blackJackState.profit(Hit(Cards(TWO_CLUB, NINE_CLUB)), 1000)

        // then
        assertThat(profit).isEqualTo(1500.0)
    }
}
