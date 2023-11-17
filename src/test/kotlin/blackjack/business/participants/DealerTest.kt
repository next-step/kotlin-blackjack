package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_FIVE
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.CardFixture.SPACE_TWO
import blackjack.business.card.CardDesk
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 카드를 한장씩 추가 한다`() {
        // given
        val dealer = Dealer()
        val cardDesk = CardDesk()
        val card = cardDesk.draw()

        // when
        dealer.addCard(card)

        // then
        dealer.cards.size shouldBe 1
        dealer.cards.contains(card) shouldBe true
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 카드를 추가할 수 있다`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_ACE)
        dealer.addCard(SPACE_FIVE)

        // when,then
        dealer.canDrawCard() shouldBe true
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 17이상이면 카드를 추가할 수 없다`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_NINE)
        dealer.addCard(SPACE_EIGHT)

        // when,then
        dealer.canDrawCard() shouldBe false
    }

    @Test
    fun `딜러의 카드의 합이 블랙잭 초과하면 true`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_TEN)
        dealer.addCard(SPACE_TWO)
        dealer.addCard(SPACE_TEN)

        // when,then
        dealer.isBust() shouldBe true
    }

    @Test
    fun `딜러의 카드의 합이 블랙잭 이하이면 false`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_TEN)
        dealer.addCard(SPACE_ACE)

        // when,then
        dealer.isBust() shouldBe false
    }
}
