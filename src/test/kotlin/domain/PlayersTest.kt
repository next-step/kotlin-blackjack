package domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayersTest{


    @Test
    fun `현재 차례의 플레이어가 반환됩니다`(){
        val players=  Players(listOf(Player.withName("pobi"),Player.withName("crong")))

        players.currentPlayer() shouldBe Player.withName("pobi")
        players.currentPlayer() shouldBe Player.withName("crong")
    }
    @Test
    fun `플레이어가 게임을 그만둘 수 있습니다`(){
        val players=  Players(listOf(Player.withName("pobi"),Player.withName("crong")))
        val currentPlayer = players.currentPlayer()

        players.quitGame(currentPlayer)

        players.currentPlayer() shouldBe Player.withName("crong")
        players.currentPlayer() shouldBe Player.withName("crong")
    }
}
