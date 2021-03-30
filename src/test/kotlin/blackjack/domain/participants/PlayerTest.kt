package blackjack.domain.participants

import blackjack.domain.CLUB_ACE
import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_TEN
import blackjack.domain.CLUB_TWO
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.FirstTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `블랙잭이 나온 상태에서는 드로우를 해도 무시해버림`() {
        val player = Player("John", arrayListOf(CLUB_ACE, CLUB_KING))
        val beforeDrawScore = player.getScore()
        player.drawCard()
        val afterDrawScore = player.getScore()
        assertThat(beforeDrawScore).isEqualTo(afterDrawScore)
    }

    @Test
    fun `Hit 상태에서는 드로우를 진행`() {
        val player = Player("John", arrayListOf(CLUB_ACE, CLUB_TWO))
        val beforeDrawScore = player.getScore()
        player.drawCard()
        val afterDrawScore = player.getScore()
        assertThat(beforeDrawScore).isNotEqualTo(afterDrawScore)
    }

    @Test
    fun `Bust 상태 확인`() {
        val player = Player("John", arrayListOf(CLUB_TEN, CLUB_KING))
        player.drawCard()
        assertThat(player.state).isInstanceOf(Bust::class.java)
    }
}