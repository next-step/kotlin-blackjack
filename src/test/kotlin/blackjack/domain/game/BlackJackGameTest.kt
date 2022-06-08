package blackjack.domain.game

import blackjack.domain.FixtureBuilder.Companion.TakeMoreDealerFixture
import blackjack.domain.FixtureBuilder.Companion.TakeMorePlayerFixture
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `블랙잭 게임을 2인이 조인했을 때 인원 수 체크`() {
        val cardDeck = CardDeck()
        val dealer = Dealer(TakeMoreDealerFixture(15))
        val takeMorePlayer = TakeMorePlayerFixture(false)
        val players = Players(listOf("A", "B"), takeMorePlayer, dealer)
        val blackJackGame = BlackJackGame(cardDeck, players)

        Assertions.assertThat(blackJackGame.players).hasSize(3)
    }

    @Test
    fun `블랙잭 게임을 2인이 조인했을 때 초기 카드 갯수 체크`() {
        val cardDeck = CardDeck()
        val dealer = Dealer(TakeMoreDealerFixture(15))
        val takeMorePlayer = TakeMorePlayerFixture(false)
        val players = Players(listOf("A", "B"), takeMorePlayer, dealer)
        val blackJackGame = BlackJackGame(cardDeck, players)

        Assertions.assertThat(blackJackGame.players[0].receivedCards).hasSize(2)
        Assertions.assertThat(blackJackGame.players[1].receivedCards).hasSize(2)
    }
}
