package blackjack.domain

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class PlayersTest {
    @Test
    fun `이름들로 플레이어들을 생성할 수 있다`() {
        val names = listOf("black", "jack", "game")

        val players = Players.from(names)

        players.roster.map { it.name } shouldContainExactlyInAnyOrder names
    }

    @Test
    fun `플레이어 목록이 비어 있으면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Players(emptyList()) }
    }

    @Test
    fun `중복된 이름이 있으면 예외를 던진다`() {
        val names = listOf("black", "jack", "black")
        assertThrows<IllegalArgumentException> { Players.from(names) }
    }
}
