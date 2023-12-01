package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerGameResultTest {
    private val bustedPlayer: Player = Player("").apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.SPADE, CardNumber.KING))
    }

    private val bustedDealer: Dealer = Dealer().apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.SPADE, CardNumber.KING))
    }

    private val blackjackPlayer: Player = Player("").apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val blackjackDealer: Dealer = Dealer().apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val score21Player: Player = Player("").apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val score21Dealer: Dealer = Dealer().apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val score20Dealer: Dealer = Dealer().apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
    }

    private val score2Player: Player = Player("").apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.TWO))
    }

    @Test
    fun `버스트된 플레이어는 무조건 패배이다`() {
        assertThat(bustedPlayer vs bustedDealer).isEqualTo(MatchResult.LOSE)
        assertThat(bustedPlayer vs blackjackDealer).isEqualTo(MatchResult.LOSE)
        assertThat(bustedPlayer vs score21Dealer).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `플레이어가 블랙잭이면 블랙잭이 아닌 딜러에게 무조건 블랙잭 승리를 거둔다`() {
        assertThat(blackjackPlayer vs bustedDealer).isEqualTo(MatchResult.BLACKJACK_WIN)
        assertThat(blackjackPlayer vs score21Dealer).isEqualTo(MatchResult.BLACKJACK_WIN)
    }

    @Test
    fun `같은 블랙잭이면 무승부다`() {
        assertThat(blackjackPlayer vs blackjackDealer).isEqualTo(MatchResult.DRAW)
    }

    @Test
    fun `딜러가 버스트되면 버스트 안된, 블랙잭이 아닌 플레이어는 승리한다`() {
        assertThat(score21Player vs bustedDealer).isEqualTo(MatchResult.WIN)
        assertThat(score2Player vs bustedDealer).isEqualTo(MatchResult.WIN)
    }

    @Test
    fun `같은 21점이여도 블랙잭을 완성한 게이머가 승리한다`() {
        assertThat(blackjackPlayer vs score21Dealer).isEqualTo(MatchResult.BLACKJACK_WIN)
        assertThat(score21Player vs blackjackDealer).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `둘다 버스트 또는 블랙잭이 아니라면, 만든 점수에 따라 승무패가 결정된다`() {
        assertThat(score21Player vs score21Dealer).isEqualTo(MatchResult.DRAW)
        assertThat(score2Player vs score21Dealer).isEqualTo(MatchResult.LOSE)
        assertThat(score21Player vs score20Dealer).isEqualTo(MatchResult.WIN)
        assertThat(score2Player vs score20Dealer).isEqualTo(MatchResult.LOSE)
    }
}
