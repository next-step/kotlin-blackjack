package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultDeterminerTest {
    private val bustedPlayer: Player = Player("busted").apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.SPADE, CardNumber.KING))
    }

    private val blackjackPlayer: Player = Player("blackjack").apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val score21Player: Player = Player("normal").apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    private val score21Dealer: Dealer = Dealer().apply {
        hit(PlayingCard(Suits.DIAMOND, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.KING))
        hit(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    @Test
    fun `GameResultDeterminer는 플레이어와 딜러의 값을 받아 승패값을 반환한다`() {
        val result = GameResultDeterminer.getResult(
            listOf(bustedPlayer, blackjackPlayer, score21Player),
            score21Dealer
        )
        assertThat(result.dealerResult[MatchResult.WIN]).isEqualTo(1)
        assertThat(result.dealerResult[MatchResult.LOSE]).isEqualTo(1)
        assertThat(result.dealerResult[MatchResult.DRAW]).isEqualTo(1)

        assertThat(result.playerResult[0].matchResult).isEqualTo(MatchResult.LOSE)
        assertThat(result.playerResult[1].matchResult).isEqualTo(MatchResult.WIN)
        assertThat(result.playerResult[2].matchResult).isEqualTo(MatchResult.DRAW)
    }
}
