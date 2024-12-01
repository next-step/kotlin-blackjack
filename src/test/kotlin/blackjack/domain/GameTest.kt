package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `check each players get two cards in first turn`() {
        val mockPlayers = listOf(Player("pobi"), Player("jason"))
        val game = Game.createGame(mockPlayers)

        var initCallbackCalled = 0
        var turnCallbackCalled = 0

        val initCallback: ((List<Player>) -> Unit) = {
            initCallbackCalled++
        }

        val turnCallback: ((Player) -> String) = {
            turnCallbackCalled++
            "n"
        }

        game.startGame(initCallback, turnCallback)

        mockPlayers[0].getAllCards().size shouldBe 2
        mockPlayers[1].getAllCards().size shouldBe 2
        initCallbackCalled shouldBe 1
        turnCallbackCalled shouldBe 2
    }

    @Test
    fun `check player can't draw card`() {
        val mockPlayers = listOf(Player("pobi"), Player("jason"))
        val game = Game.createGame(mockPlayers)

        val mockPlayer = Player("pobi")
        mockPlayer.drawCard(Card.createCard("6", "하트"))
        mockPlayer.drawCard(Card.createCard("6", "하트"))
        mockPlayer.drawCard(Card.createCard("10", "하트"))

        var turnCallbackCalled = 0
        val turnCallback: ((Player) -> String) = {
            turnCallbackCalled++
            "y"
        }

        var printCallbackCalled = 0
        val printCallback: ((List<Player>) -> Unit) = {
            printCallbackCalled++
        }

        game.startTurn(mockPlayer, turnCallback, printCallback)

        turnCallbackCalled shouldBe 0
        printCallbackCalled shouldBe 0
    }
}
