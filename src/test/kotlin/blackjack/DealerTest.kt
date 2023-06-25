package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 이름은 딜러이다`() {
        val dealer = Dealer()
        dealer.info.name shouldBe "딜러"
    }

    @Test
    fun `딜러는 처음에 카드 2장을 가지고 있다`() {
        val dealer = Dealer()
        dealer.cards.items.size shouldBe 2
    }

    @Test
    fun `딜러가 가지고 있는 카드의 합을 구한다`() {
        val dealer = Dealer()
        val cards = dealer.cards
        val expectedSum = PointCalculator.sum(cards)

        dealer.cards.sum shouldBe expectedSum
    }

    @Test
    fun `딜러가 가지고 있는 카드의 합이 17이 되지 않으면 카드를 계속 뽑을 수 있다`() {
        val round = Round()
        val dealer = Dealer()

        while (dealer.cards.sum < 17) {
            dealer.canGetCard() shouldBe true
            dealer.addCard(round.getCard())
        }

        dealer.canGetCard() shouldBe false
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(PlayerInfo(name = "홍길동"))
        player.info.name shouldBe "홍길동"
    }
}
