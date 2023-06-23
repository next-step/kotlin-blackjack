package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `게임에 참여할 Player와 CardDeck이 필요하다`() {
        val deck = CardDeck()
        val player1 = Player()
        val player2 = Player()
        val players = Players.from(player1, player2)
        val blackjackGame = BlackjackGame(players, deck)

        blackjackGame.players shouldBe players
        blackjackGame.deck shouldBe deck
    }

    @Test
    fun `Player에게 랜덤한 카드를 할당할 수 있다`() {
        val deck = CardDeck()
        val player = Player()
        val blackjackGame = BlackjackGame(Players.from(player), deck)

        blackjackGame.dealCards(player)

        blackjackGame.players.value[0].cards.value.shouldBeInstanceOf<List<Card>>()
    }

    @Test
    fun `최초 시작시 Player에게 2개의 랜덤한 카드를 할당한다`() {
        val deck = CardDeck()
        val player = Player()
        val blackjackGame = BlackjackGame(Players.from(player), deck)

        blackjackGame.initPlayers()

        blackjackGame.players.value[0].cards.value.size shouldBe 2
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
        cards.value.forEach { card -> player.receiveCard(card) }

        // 17, 27
        player.receiveCard(Card(Denomination.JACK, CardType.HEARTS))

        player.cards.getOptimizedScore() shouldBe 17
    }
}
