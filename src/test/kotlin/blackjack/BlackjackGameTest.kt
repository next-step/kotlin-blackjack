package blackjack

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `게임에 참여할 Player와 CardDeck이 필요하다`() {
        val deck = CardDeck()
        val player1 = Player()
        val player2 = Player()
        val players = listOf(player1, player2)
        val blackjackGame = BlackjackGame(players, deck)

        blackjackGame.players shouldBe players
        blackjackGame.deck shouldBe deck
    }

    @Test
    fun `Player에게 랜덤한 카드를 할당할 수 있다`() {
        val deck = CardDeck()
        val player = Player()
        val blackjackGame = BlackjackGame(listOf(player), deck)

        blackjackGame.shareCards(player, 1)

        blackjackGame.players[0].cards.shouldBeInstanceOf<List<Card>>()
    }

    @Test
    fun `최초 시작시 Player에게 2개의 랜덤한 카드를 할당한다`() {
        val deck = CardDeck()
        val player = Player()
        val blackjackGame = BlackjackGame(listOf(player), deck)

        blackjackGame.initPlayers()

        blackjackGame.players[0].cards.size shouldBe 2
    }
}
