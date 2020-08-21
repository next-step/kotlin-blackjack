package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var deck: Deck
    private lateinit var dealer: Dealer
    private lateinit var markCards: Set<Card>
    private lateinit var harryCards: Set<Card>
    private lateinit var players: Players

    @BeforeEach
    fun setUp() {
        deck = Deck(setOf(Card(CardScore.ACE, Suit.SPADE)))
        dealer = Dealer(deck)

        markCards = setOf(Card(CardScore.TWO, Suit.HEART), Card(CardScore.SEVEN, Suit.DIAMOND))
        harryCards = setOf(Card(CardScore.ACE, Suit.SPADE), Card(CardScore.KING, Suit.CLUB))

        players = Players(
            listOf(
                Player("mark", Cards(markCards)),
                Player("harry", Cards(harryCards))
            )
        )
    }

    @Test
    fun `카드 한 장을 반환한다`() {
        // when
        val card = dealer.pickCard()

        // then
        assertThat(card).isEqualTo(Card(Pair(CardScore.ACE, Suit.SPADE)))
    }

    @Test
    fun `카드 점수 합계가 17 미만인지 확인하는 기능이 있다`() {
        // when
        val actual = dealer.hasScoreBelow17(14)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `딜러가 BUST일 경우 그 시점까지 살아있는 플레이어는 베팅금액만큼 돈을 번다`() {
        // given
        players.setStake(1000)

        // when
        val dealerResult = dealer.checkWin(State.BUST, players)
        val playerProfit = players.findPlayer(0).profit

        // then
        assertThat(dealerResult).isEqualTo(Profit(-1000))
        assertThat(playerProfit).isEqualTo(Profit(1000))
    }
}
