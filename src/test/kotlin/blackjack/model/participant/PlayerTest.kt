package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Suit
import blackjack.model.profit.Profit
import blackjack.model.state.finished.BlackJack
import blackjack.model.state.finished.Bust
import blackjack.model.state.finished.Stay
import blackjack.model.state.running.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var ACE_SPADE: Card
    private lateinit var TWO_SPADE: Card
    private lateinit var TWO_DIAMOND: Card
    private lateinit var TEN_DIAMOND: Card
    private lateinit var hitPlayer: Player
    private lateinit var stayPlayer: Player
    private lateinit var bustPlayer: Player
    private lateinit var blackJackPlayer: Player

    @BeforeEach
    fun setUp() {
        ACE_SPADE = Card(CardScore.ACE, Suit.SPADE)
        TWO_SPADE = Card(CardScore.TWO, Suit.SPADE)
        TWO_DIAMOND = Card(CardScore.TWO, Suit.DIAMOND)
        TEN_DIAMOND = Card(CardScore.TEN, Suit.DIAMOND)

        hitPlayer = Player(PlayerInfo("mark", 1000), Hit(Cards(ACE_SPADE, TWO_SPADE)))
        stayPlayer = hitPlayer.copy(state = Stay(Cards(ACE_SPADE, TWO_SPADE)))
        blackJackPlayer = hitPlayer.copy(state = BlackJack(Cards(ACE_SPADE, TEN_DIAMOND)))
        bustPlayer = hitPlayer.copy(state = Bust(Cards(ACE_SPADE, TWO_SPADE, TWO_DIAMOND, TEN_DIAMOND)))
    }

    @Test
    fun `기본 카드 2장 뽑기`() {
        // when
        val actual = hitPlayer.takeDefaultCards { Cards(ACE_SPADE, TWO_SPADE) }

        // then
        assertThat(actual.hasTwoCards()).isTrue()
        assertThat(actual.state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드 한 장을 뽑는다`() {
        // when
        val actual = hitPlayer.draw(TWO_DIAMOND)

        // then
        assertThat(actual.state.cards).isEqualTo(Cards(ACE_SPADE, TWO_SPADE, TWO_DIAMOND))
        assertThat(actual.state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드를 뽑을 수 있는지 확인`() {

        assertThat(hitPlayer.canDraw()).isTrue()
        assertThat(stayPlayer.canDraw()).isFalse()
        assertThat(blackJackPlayer.canDraw()).isFalse()
        assertThat(bustPlayer.canDraw()).isFalse()
    }

    @Test
    fun `Stay 하기 (Hit인 경우에만 Stay로 변한다)`() {
        // when
        val hitPlayer = hitPlayer.stay()
        val stayPlayer = stayPlayer.stay()
        val blackJackPlayer = blackJackPlayer.stay()
        val bustPlayer = bustPlayer.stay()

        // when, then
        assertThat(hitPlayer.state).isInstanceOf(Stay::class.java)
        assertThat(stayPlayer.state).isInstanceOf(Stay::class.java)
        assertThat(blackJackPlayer.state).isInstanceOf(BlackJack::class.java)
        assertThat(bustPlayer.state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `수익 확인 (딜러와 플레이어 모두 블랙잭인 경우 수익 0)`() {
        // when
        val profit: Profit = blackJackPlayer.profit(dealerState = BlackJack(Cards(ACE_SPADE, TEN_DIAMOND)))

        // then
        assertThat(profit.participantName).isEqualTo("mark")
        assertThat(profit.profit).isEqualTo(0)
    }
}
