package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `���� ������ �÷��̾ ��ȯ�˴ϴ�`() {
        val players = Players(listOf(Player.withName("pobi"), Player.withName("crong")))

        players.currentPlayer() shouldBe Player.withName("pobi")
        players.currentPlayer() shouldBe Player.withName("crong")
    }

    @Test
    fun `�÷��̾ ������ �׸��� �� �ֽ��ϴ�`() {
        val players = Players(listOf(Player.withName("pobi"), Player.withName("crong")))
        val currentPlayer = players.currentPlayer()

        players.quitGame(currentPlayer)

        players.currentPlayer() shouldBe Player.withName("crong")
        players.currentPlayer() shouldBe Player.withName("crong")
    }
}
