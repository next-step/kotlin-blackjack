package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.Player
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `게임에 참여할 Player와 CardDeck이 필요하다`() {
        val player1 = Player()
        val player2 = Player()
        val gamePlayers = GamePlayers.from(player1, player2)
        val blackjackGame = BlackjackGame(gamePlayers)

        blackjackGame.gamePlayers shouldBe gamePlayers
        blackjackGame.deck.shouldNotBeNull()
    }

    @Test
    fun `최초 카드분배시 Player에게 2장의 카드를 나눠준다`() {
        val player = Player()
        val blackjackGame = BlackjackGame(GamePlayers.from(player))

        blackjackGame.initPlayers()

        player.cards.getValue().size shouldBe 2
    }

    @Test
    fun `최초 카드분배 이후에는 Player에게 카드를 1장씩 나눠준다`() {
        val player = Player()
        val blackjackGame = BlackjackGame(GamePlayers.from(player))

        blackjackGame.dealCard(player)

        player.cards.getValue().size shouldBe 1
    }

    @Test
    fun `ACE는 1 또는 11 score로 계산될 수 있어서 21을 넘지 않는 가장 가까운 수로 계산된다`() {
        // 7, 17
        val player = Player()
        val cards = Cards(
            mutableListOf(
                Card(Denomination.ACE, CardType.CLUBS),
                Card(Denomination.SIX, CardType.DIAMONDS),
            ),
        )

        player.initCards(cards)

        // 17, 27
        player.receiveCard(Card(Denomination.JACK, CardType.HEARTS))

        player.cards.getOptimizedScore() shouldBe 17
    }
}
