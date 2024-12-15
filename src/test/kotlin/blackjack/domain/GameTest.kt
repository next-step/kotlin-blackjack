package blackjack.domain

import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `check each players get two cards in first turn`() {
        val mockPlayers = listOf(Player("pobi"), Player("jason"))
        val game = Game.createGame(mockPlayers)

        var initCallbackCalled = 0
        var turnCallbackCalled = 0

        val initCallback: ((List<Participant>) -> Unit) = {
            initCallbackCalled++
        }

        val turnCallback: ((Participant) -> String) = {
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
    fun `check add dealer when create game`() {
        val mockPlayers = listOf(Player("pobi"), Player("jason"))
        val game = Game.createGame(mockPlayers)

        val playerName = listOf("pobi", "jason", "딜러")
        game.players.size shouldBe 2
        game.players.forEach { player ->
            player.name shouldBeIn playerName
        }
    }
}
