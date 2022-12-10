package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    lateinit var playerPobi: Player
    lateinit var playerCrong: Player

    beforeTest {
        playerPobi = Player.withName("pobi")
        playerCrong = Player.withName("crong")
    }

    "현재 차례의 플레이어가 반환됩니다" {
        val players = Players(listOf(playerPobi, playerCrong))

        players.currentPlayer() shouldBe playerPobi
        players.currentPlayer() shouldBe playerCrong
    }

    "플레이어가 게임을 그만둘 수 있습니다" {
        val players = Players(listOf(playerPobi, playerCrong))
        val currentPlayer = players.currentPlayer()

        players.quitGame(currentPlayer)

        players.currentPlayer() shouldBe playerCrong
        players.currentPlayer() shouldBe playerCrong
    }
})
