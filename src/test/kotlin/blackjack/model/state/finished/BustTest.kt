package blackjack.model.state.finished

import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Suit
import blackjack.model.state.State
import blackjack.model.state.running.Hit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BustTest {
    private lateinit var ACE_CLUB: Card
    private lateinit var TWO_CLUB: Card
    private lateinit var TEN_CLUB: Card
    private lateinit var TEM_SPADE: Card
    private lateinit var bustState: State

    @BeforeEach
    fun setUp() {
        ACE_CLUB = Card(CardScore.ACE, Suit.CLUB)
        TWO_CLUB = Card(CardScore.TWO, Suit.HEART)
        TEN_CLUB = Card(CardScore.TEN, Suit.CLUB)
        TEM_SPADE = Card(CardScore.TEN, Suit.SPADE)

        bustState = Bust(Cards(TEN_CLUB, TEM_SPADE, ACE_CLUB, TWO_CLUB))
    }

    @Test
    fun `플레이어가 Bust인 경우 profit`() {
        // when
        val actual = bustState.profit(Hit(Cards(ACE_CLUB, TWO_CLUB)), 1000)

        // then
        Assertions.assertThat(actual).isEqualTo(-1000.0)
    }
}
