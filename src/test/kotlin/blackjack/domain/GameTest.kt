package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {

    object FakePlayers {
        val players: Players = Players(listOf(Player("이든"), Player("저스틴")))
    }

    @Test
    fun `게임은 참가자들과 덱을 가진다`() {
        val game = Game(FakePlayers.players)
        game.players.list.size shouldBe 2
        game.deck.count() shouldBe 52
    }
}
