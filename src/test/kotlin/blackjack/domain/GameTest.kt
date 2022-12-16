package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {

    object FakePlayers {
        val players: Players = Players(listOf(GamePlayer("이든"), GamePlayer("저스틴")))
    }

    @Test
    fun `게임은 참가자들과 딜러와 덱을 가진다`() {
        val game = Game(FakePlayers.players)
        game.players.list.size shouldBe 2
        game.getDealer().name.value shouldBe "딜러"
    }

    @Test
    fun `최초 2장씩 카드를 참가자와 딜러에게 나눈다`() {
        val game = Game(FakePlayers.players)
        val players = game.initialCard()
        val list = players.list + game.getDealer()
        list.map { it.cards.count() shouldBe 2 }
        game.deck.count() shouldBe 46 // 52-(3명*2)
    }
}
