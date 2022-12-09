package blackjack.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class GameTest {

    object FakePlayers {
        val players: Players = Players(listOf(Player("이든"), Player("저스틴")))
    }

    @Test
    fun `게임은 참가자들과 딜러를 가진다`() {
        val game = Game(FakePlayers.players)
        game.players.list.size shouldBe 2
        game.dealer shouldNotBe null
    }

    @Test
    fun `게임 시작시 참가자들은 최초 카드를 가진다`() {
        val game = Game(FakePlayers.players)
        game.players.list.forEach {
            it.cards.count() shouldBe INITIAL_CARD_COUNT
        }
    }
}
