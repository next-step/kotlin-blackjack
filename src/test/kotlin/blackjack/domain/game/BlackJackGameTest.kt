package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `블랙잭 게임을 2인이 조인했을 때 인원 수 체크`() {
        val cardDeck = CardDeck()
        val dealer = Dealer(cardDeck)
        val players = Players(listOf("A", "B"), cardDeck)
        val blackJackGamer = players.players + dealer

        Assertions.assertThat(blackJackGamer).hasSize(3)
    }

    @Test
    fun `블랙잭 게임을 2인이 조인했을 때 초기 카드 갯수 체크`() {
        val cardDeck = CardDeck()
        val players = Players(listOf("A", "B"), cardDeck)

        Assertions.assertThat(players.players[0].receivedCards.count()).isEqualTo(2)
        Assertions.assertThat(players.players[1].receivedCards.count()).isEqualTo(2)
    }

    @Test
    fun `딜러가 21 초과이기 때문에, 참가자 모두 이기는 게임 체크`() {
        val cardDeck = CardDeck()
        val dealer = Dealer(cardDeck)
        val players = Players(listOf("A", "B"), cardDeck)
        val blackJackGamer = players.players + dealer

        while (dealer.score <= 21) {
            dealer.addCard(cardDeck.pickCard())
        }

        WinnerJudge(blackJackGamer)

        Assertions.assertThat(players.players[0].gamblingSummary.isWinner).isTrue
        Assertions.assertThat(players.players[1].gamblingSummary.isWinner).isTrue
    }
}
