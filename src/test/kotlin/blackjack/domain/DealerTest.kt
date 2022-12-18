package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun `딜러는 카드 총합이 16이하면 카드를 더 뽑아야 한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(Shape.DIAMOND, Denomination.TEN))
        dealer.addCard(Card(Shape.DIAMOND, Denomination.SIX))

        dealer.canDraw() shouldBe true

        dealer.addCard(Card(Shape.DIAMOND, Denomination.ACE))

        dealer.canDraw() shouldBe false
    }

    @Test
    fun `딜러와 플레이어들 사이에서의 승패를 구분할 수 있다`() {
        val dealer = Dealer()
        dealer.addCard(Card(Shape.DIAMOND, Denomination.TEN))
        dealer.addCard(Card(Shape.DIAMOND, Denomination.SEVEN))

        val player1 = Player("Player1")
        player1.addCard(Card(Shape.DIAMOND, Denomination.TEN))
        player1.addCard(Card(Shape.DIAMOND, Denomination.EIGHT))

        val player2 = Player("Player2")
        player2.addCard(Card(Shape.DIAMOND, Denomination.TEN))
        player2.addCard(Card(Shape.DIAMOND, Denomination.SIX))

        val result = dealer.result(listOf(player1, player2))

        result.count { it == Result.WIN } shouldBe 1
        result.count { it == Result.LOSE } shouldBe 1
    }
}
