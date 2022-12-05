package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `참가자 리스트를 가진다`() {
        val players = listOf(Player("name"), Player("add"), Player("play"))
        Players(players).list.size shouldBe 3
    }
}
