package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class WinnerJudgeTest {

    @Test
    fun `딜러가 21 초과이기 때문에, 참가자 모두 이기는 게임 체크`() {
        val cardDeck = CardDeck()
        val dealer = Dealer(cardDeck)
        val players = Players(listOf("A", "B"), cardDeck)

        while (dealer.score <= 21) {
            dealer.addCard(cardDeck.pickCard())
        }

        WinnerJudge(players.players, dealer)

        Assertions.assertThat(players.players[0].gambleSummary.isWinner).isTrue
        Assertions.assertThat(players.players[1].gambleSummary.isWinner).isTrue
    }
}
