package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음에 카드 2장을 가지고 있다`() {
        val player = Player()
        player.cards.size shouldBe 2
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 합을 구한다`() {
        val player = Player()
        val cards = player.cards
        val expectedSum = Round.calculateSum(cards)

        player.sum shouldBe expectedSum
    }

    @Test
    fun `가지고 있는 카드의 합이 21이 되지 않으면 카드를 계속 뽑을 수 있다`() {
        val round = Round()
        val player = Player()

        while (player.sum < 21) {
            player.canGetCard() shouldBe true
            player.addCard(round.getCard())
        }

        player.canGetCard() shouldBe false
    }
}
