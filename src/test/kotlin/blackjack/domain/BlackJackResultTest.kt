package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackResultTest {
    @Test
    fun `패가 Busted 되지 않은 Player 목록을 가지고 있다`() {
        val survivors = BlackJackResult(DEFAULT_PLAYERS).players
        assertThat(survivors).contains(PLAYER_WINNER, PLAYER_LOSER)
        assertThat(survivors).doesNotContain(PLAYER_BUSTED)
        assertThat(survivors).hasSize(2)
    }

    @Test
    fun `플레이어 각 패의 점수를 내림차순으로 정렬하여 가지고 있다`() {
        val survivors = BlackJackResult(DEFAULT_PLAYERS).players
        assertThat(survivors).isEqualTo(listOf(PLAYER_WINNER, PLAYER_LOSER))
    }

    companion object {
        private val PLAYER_WINNER = Player("winner").also { player ->
            player hit Card.CLUB_ACE
            player hit Card.CLUB_K
        }
        private val PLAYER_BUSTED = Player("busted").also { player ->
            player hit Card.CLUB_K
            player hit Card.CLUB_K
            player hit Card.CLUB_K
        }
        private val PLAYER_LOSER = Player("loser").also { player ->
            player hit Card.CLUB_K
            player hit Card.CLUB_K
        }
        private val DEFAULT_PLAYERS = listOf(PLAYER_LOSER, PLAYER_BUSTED, PLAYER_WINNER)
    }
}
