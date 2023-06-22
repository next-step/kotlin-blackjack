package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 카드 이름과 2장의카드를 소지한다`() {
        val player = Player("pobi")
        player.name shouldBe "pobi"
        player.cards.size shouldBe 2
    }

    @Test
    fun `플레이어는 카드를 더 받을 수 있다`() {
        val player = Player("pobi")
        BlackJackTable.giveCardsToPlayer(player)
        player.cards.size shouldBe 3
    }
}
