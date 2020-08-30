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

class StayTest {
    private lateinit var TWO_CLUB: Card
    private lateinit var FIVE_CLUB: Card
    private lateinit var TEN_CLUB: Card
    private lateinit var TEM_SPADE: Card
    private lateinit var stateWithScore15: State

    @BeforeEach
    fun setUp() {
        TWO_CLUB = Card(CardScore.TWO, Suit.CLUB)
        FIVE_CLUB = Card(CardScore.FIVE, Suit.CLUB)
        TEN_CLUB = Card(CardScore.TEN, Suit.CLUB)
        TEM_SPADE = Card(CardScore.TEN, Suit.SPADE)

        stateWithScore15 = Stay(Cards(TEN_CLUB, FIVE_CLUB))
    }

    @Test
    fun `딜러가 Bust이고, 플레이어가 Stay인 경우 profit`() {
        // when
        val profit = stateWithScore15.profit(Bust(Cards(TEN_CLUB, TEM_SPADE, TWO_CLUB)), 1000)

        // then
        Assertions.assertThat(profit).isEqualTo(1000.0)
    }

    @Test
    fun `플레이어 점수가 딜러보다 더 높을 경우 profit`() {
        // when
        val profit = stateWithScore15.profit(Hit(Cards(TEM_SPADE, TWO_CLUB)), 1000)

        // then
        Assertions.assertThat(profit).isEqualTo(1000.0)
    }

    @Test
    fun `플레이어와 딜러 점수가 같을 경우 profit`() {
        // when
        val profit = stateWithScore15.profit(stateWithScore15, 1000)

        // then
        Assertions.assertThat(profit).isEqualTo(0.0)
    }

    @Test
    fun `딜러 점수가 더 높을 경우 profit`() {
        // when
        val profit = stateWithScore15.profit(Hit(Cards(TEN_CLUB, TEM_SPADE)), 1000)

        // then
        Assertions.assertThat(profit).isEqualTo(-1000.0)
    }
}
