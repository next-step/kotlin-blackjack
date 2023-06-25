package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackJackTest {
    @Test
    internal fun `게임이 시작되면 플레이어에게 두장의 카드가 주어진다`() {
        val player = Player("pobi")
        player.cards.cards.size shouldBe 0
        BlackJack.start(listOf(player))
        player.cards.cards.size shouldBe 2
    }

    @Test
    internal fun `21점이 초과하지 않은 사람은 게임을 진행할 수 있다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.A)
            )
        )
        val player = Player("pobi",cards)

        player.cards.score() shouldBe  21
        BlackJack.canPlay(player) shouldBe true
    }

    @Test
    internal fun `21점을 초과한 사람은 게임을 진행할 수 없다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.NINE),
                Card(Shape.CLOVER, Character.FIVE),
                Card(Shape.CLOVER, Character.TEN)
            )
        )
        val player = Player("pobi",cards)

        player.cards.score() shouldBe  24
        BlackJack.canPlay(player) shouldBe false
    }
}
