package blackjack.domain.model

import blackjack.domain.PointCalculator
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 이름의 기본 값은 딜러이다`() {
        val dealer = Dealer(Trump())
        dealer.info.name shouldBe "딜러"
    }

    @Test
    fun `딜러는 처음에 카드 2장을 가지고 있다`() {
        val dealer = Dealer(Trump())
        dealer.cards.items.size shouldBe 2
    }

    @Test
    fun `딜러가 가지고 있는 카드의 합을 구한다`() {
        val dealer = Dealer(Trump())
        val cards = dealer.cards
        val expectedSum = PointCalculator.sum(cards)

        dealer.cards.sum shouldBe expectedSum
    }

    @Test
    fun `딜러의 초기 카드의 합이 17이 되지 않으면 카드를 한 장 뽑는다`() {
        val trump = Trump()

        val cards = Cards(
            cards = mutableListOf(
                Card.from(CardType.SPADE, CardValue.SEVEN),
                Card.from(CardType.SPADE, CardValue.FIVE),
            ),
            trump
        )
        val dealer = Dealer(trump, cards)

        dealer.cards.sum shouldBeLessThan 17
        dealer.canDrawCard() shouldBe true
        dealer.drawCard(trump)

        dealer.canDrawCard() shouldBe false
    }
}
