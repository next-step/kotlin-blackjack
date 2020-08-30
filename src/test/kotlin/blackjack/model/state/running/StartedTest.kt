package blackjack.model.state.running

import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Suit
import blackjack.model.state.finished.BlackJack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StartedTest {
    private lateinit var ACE_CLUB: Card
    private lateinit var ACE_SPADE: Card
    private lateinit var TEN_SPADE: Card
    private lateinit var hitCards: Cards
    private lateinit var blackJackCards: Cards

    @BeforeEach
    fun setUp() {
        ACE_CLUB = Card(CardScore.ACE, Suit.CLUB)
        ACE_SPADE = Card(CardScore.ACE, Suit.SPADE)
        TEN_SPADE = Card(CardScore.TEN, Suit.SPADE)

        hitCards = Cards(ACE_CLUB, ACE_SPADE)
        blackJackCards = Cards(ACE_SPADE, TEN_SPADE)
    }

    @Test
    fun `BlackJack인 경우`() {
        // when
        val actual = Started(blackJackCards).blackJackChecked()

        // then
        assertThat(actual).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `Hit인 경우`() {
        // when
        val actual = Started(hitCards).blackJackChecked()

        // then
        assertThat(actual).isInstanceOf(Hit::class.java)
    }
}
