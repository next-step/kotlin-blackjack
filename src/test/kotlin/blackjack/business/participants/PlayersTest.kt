package blackjack.business.participants

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어들")
class PlayersTest {

    @Test
    fun `플레이어가 2명이하이면 예외를 던진다`() {
        // given
        val playerNames = listOf("pobi")

        // when,then
        shouldThrow<IllegalArgumentException> { Players.from(playerNames) }.message shouldBe "플레이어는 2명 이상이여야 가능합니다."
    }

    @Test
    fun `플레이어가 2명 이상이여야 가능합니다`() {
        // given
        val playerNames = listOf("pobi", "wonyong")

        // when,then
        Players.from(playerNames)
    }

    @Test
    fun `각 플레이어를 순환하면서 action를 실행한다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val players = Players.from(playerNames)
        val target = mutableListOf<Player>()

        // when
        players.forEachPlayer { player ->
            target.add(player)
        }

        // then
        target shouldBe players.allPlayers
    }
}
