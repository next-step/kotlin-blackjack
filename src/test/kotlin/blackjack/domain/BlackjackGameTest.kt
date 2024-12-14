package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.result.GameResultJudge
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `카드를 뽑으면 플레이어는 카드를 얻어야 한다`() {
        // given
        val player = Player(name = "jay", bettingAmount = 1)
        val blackjackGame = BlackjackGame(Deck(), Dealer(), listOf(player), GameResultJudge())

        // when
        blackjackGame.draw(player)

        // then
        assertThat(player.cards.cards.size).isEqualTo(1)
    }

    @Test
    fun `게임이 시작을 하면 플레이어는 카드를 2장 얻어야 한다`() {
        // given
        val blackjackGame = BlackjackGame(
            Deck(),
            Dealer(),
            listOf(Player(name = "jay", bettingAmount = 1)),
            GameResultJudge(),
        )

        // when
        blackjackGame.start()

        // then
        assertThat(blackjackGame.players[0].cards.cards.size).isEqualTo(2)
    }

}
