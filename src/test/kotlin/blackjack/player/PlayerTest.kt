package blackjack.player

import blackjack.card.Card
import blackjack.card.Rank
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어의 카드의 합이 21 미만이면 카드를 더 받을 수 있다`() {
        val player = Player.of(name = NAME, cards = List(size = 2) { Card(rank = Rank.SIX) })
        player.isHitCard() shouldBe true
    }

    @Test
    fun `플레이어의 카드의 합이 21 이상이면 카드를 더 받을 수 없다`() {
        val player =
            Player.of(
                name = NAME,
                cards =
                    listOf(
                        Card(rank = Rank.FIVE),
                        Card(rank = Rank.SIX),
                        Card(rank = Rank.TEN),
                    ),
            )
        player.isHitCard() shouldBe false
    }

    companion object {
        private const val NAME = "nooblette"
    }
}
