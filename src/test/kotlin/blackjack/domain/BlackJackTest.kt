package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackJackTest {
    @Test
    internal fun `게임이 시작되면 플레이어에게 두장의 카드가 주어진다`() {
        val player = Player("pobi")
        player.cards.size shouldBe 0
        val game = BlackJack(listOf(player))
        game.start()
        player.cards.size shouldBe 2
    }
}