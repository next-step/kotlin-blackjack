package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {
    @Test
    internal fun `이름을 입력하면 Player가 생성된다`() {
        Player("pobi").name shouldBe "pobi"
    }

    @Test
    internal fun `빈값을 입력하면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Player("")
        }
    }

    @Test
    internal fun `게임이 시작되면 플레이어에게 두장의 카드가 주어진다`() {
        val player = Player("pobi")
        player.cards.cards.size shouldBe 0
        BlackJack(listOf(player)).start()
        player.cards.cards.size shouldBe 2
    }

    @Test
    internal fun `플레이어가 21점이 초과하지않으면 턴은 계속된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.A)
            )
        )
        val player = Player("pobi", cards)

        player.cards.score() shouldBe 21
    }
}
