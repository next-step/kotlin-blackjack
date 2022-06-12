package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

internal class GameTest : FreeSpec({

    "카드를 더 받을 수 없는 경우 추가하지 않는다" {
        val blackjackPlayer = createPlayer(
            "blackjack",
            Card(Suite.CLUBS, Denomination.QUEEN),
            Card(Suite.CLUBS, Denomination.ACE),
        )
        val bustPlayer = createPlayer(
            "bust",
            Card(Suite.CLUBS, Denomination.NINE),
            Card(Suite.CLUBS, Denomination.THREE),
            Card(Suite.CLUBS, Denomination.KING),
        )
        val game = Game.byPlayers(blackjackPlayer, bustPlayer)

        game.processPlayers(StubPlayerInteraction(true))

        game.status.players.map { it.hand.count } shouldBe listOf(2, 3)
    }

    "카드를 더 받을 수 있지만 거절하면 손패가 그대로 유지된다" {
        val player = createPlayer(
            "player",
            Card(Suite.CLUBS, Denomination.NINE),
            Card(Suite.CLUBS, Denomination.ACE),
        )
        val game = Game.byPlayers(player)

        game.processPlayers(StubPlayerInteraction(false))

        game.status.players.first().hand.count shouldBe 2
    }

    "카드를 받을 수 있고 수락하는 경우 손패에 카드가 추가된다" {
        val player = createPlayer(
            "player",
            Card(Suite.HEARTS, Denomination.TWO),
            Card(Suite.SPADES, Denomination.EIGHT),
        )
        val game = Game.byPlayers(player)

        game.processPlayers(StubPlayerInteraction(true))

        game.status.players.first().hand.count shouldBeGreaterThan 2
    }

    "딜러는 17점 이상일 때까지 카드를 추가해야 한다" {
        val game = Game.byDealer(
            Card(Suite.DIAMONDS, Denomination.JACK),
            Card(Suite.CLUBS, Denomination.TWO),
            Card(Suite.DIAMONDS, Denomination.FOUR), // 16
            Card(Suite.HEARTS, Denomination.ACE), // 19
        )

        game.processDealer()

        game.status.dealer.hand.count shouldBe 4
    }
})

class StubPlayerInteraction(private val choice: Boolean) : PlayerInteraction {
    override fun getDrawChoice(player: Player): Boolean = choice

    override fun printStatus(player: Player) {}
}

fun Game.Companion.byPlayers(vararg players: Player): Game = Game(Dealer(Deck.shuffled()), players.toList())

fun Game.Companion.byDealer(vararg card: Card): Game = Game(Dealer(Deck(card.toList())), emptyList())
