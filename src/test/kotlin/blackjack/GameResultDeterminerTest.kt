package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultDeterminerTest {
    private val bustedPlayer: Player = Player("busted").apply {
        betAmount = BetAmount(10000)
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.SPADE, CardNumber.KING))
    }

    private val blackjackPlayer: Player = Player("blackjack").apply {
        betAmount = BetAmount(10000)
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val score21Player: Player = Player("21").apply {
        betAmount = BetAmount(10000)
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val score20Player: Player = Player("20").apply {
        betAmount = BetAmount(10000)
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
    }

    private val score19Player: Player = Player("19").apply {
        betAmount = BetAmount(10000)
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.NINE))
    }

    private val score20Dealer: Dealer = Dealer().apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
    }

    @Test
    fun `플레이어가 패배 시, 수익은 베팅금액의 -1배이다`() {
        GameResultDeterminer.setProfit(
            listOf(bustedPlayer, score19Player),
            score20Dealer
        )
        assertThat(bustedPlayer.profit).isEqualTo(-10000)
        assertThat(score19Player.profit).isEqualTo(-10000)
    }

    @Test
    fun `플레이어가 무승부일 시, 수익은 0이다`() {
        GameResultDeterminer.setProfit(
            listOf(score20Player),
            score20Dealer
        )
        assertThat(score20Player.profit).isEqualTo(0)
    }

    @Test
    fun `플레이어가 승리 시, 수익은 1배이다`() {
        GameResultDeterminer.setProfit(
            listOf(score21Player),
            score20Dealer
        )
        assertThat(score21Player.profit).isEqualTo(10000)
    }

    @Test
    fun `플레이어가 블랙잭 승리일 시, 수익은 기존 승리보다 반을 더 받는다`() {
        GameResultDeterminer.setProfit(
            listOf(blackjackPlayer),
            score20Dealer
        )
        assertThat(blackjackPlayer.profit).isEqualTo(15000)
    }

    @Test
    fun `딜러의 수익은 모든 플레이어 수익의 합의 반대다`() {
        GameResultDeterminer.setProfit(
            listOf(bustedPlayer, blackjackPlayer, score21Player, score20Player, score19Player),
            score20Dealer
        )
        assertThat(score20Dealer.profit).isEqualTo(-5000)
    }
}
