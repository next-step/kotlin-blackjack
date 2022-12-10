package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({

    "현재 차례의 플레이어가 반환됩니다" {
        val players = Players(listOf(Player.withName("pobi"), Player.withName("crong")))

        players.currentPlayer() shouldBe Player.withName("pobi")
        players.currentPlayer() shouldBe Player.withName("crong")
    }

    "플레이어가 게임을 그만둘 수 있습니다" {
        val players = Players(listOf(Player.withName("pobi"), Player.withName("crong")))
        val currentPlayer = players.currentPlayer()

        players.quitGame(currentPlayer)

        players.currentPlayer() shouldBe Player.withName("crong")
        players.currentPlayer() shouldBe Player.withName("crong")
    }
})
