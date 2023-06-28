package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.CardDeck
import blackjack.domain.Cards
import blackjack.domain.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드를 받는다`() {
        val name = "홍길동"
        val deck = CardDeck()
        val card = Cards(deck.getRandomCard(1))
        val player = Player(name = name)
        card.card.forEach { player.receiveCard(it) }

        player.cards.card.size shouldBe 1
    }

    @Test
    fun `게임 시작 시 두장의 카드를 받는다`() {
        val name = "홍길동"
        val deck = CardDeck()
        val cards = Cards(deck.getRandomCard(2))
        val player = Player(name = name)
        val game = BlackjackGame(listOf(player), CardDeck())
        game.initPlayer()
        game.player[0].cards.card.size shouldBe 2
    }
}