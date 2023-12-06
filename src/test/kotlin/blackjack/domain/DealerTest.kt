package blackjack.domain

import fixtures.createCard
import fixtures.createOverDealerStandCards
import fixtures.createStartedDealer
import fixtures.createUnderDealerStandCards
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    private lateinit var dealer: Dealer
    @BeforeEach
    fun setUp() {
        dealer = createStartedDealer()
    }

    @Test
    fun `딜러는 카드를 받을 수 있다`() {
        // given
        // when
        dealer.receiveCard(createCard())
        // then
        dealer.state.cards.size shouldBe 1
    }

    @Test
    fun `처음 받은 카드 합계가 DEALER_STAND_THRESHOLD 미만이면 카드 한장을 추가로 받아야 한다`() {
        // given
        val cards = createUnderDealerStandCards()
        // when
        dealer.receiveInitialCards(cards)
        // then
        dealer.canReceiveOneMoreCard() shouldBe true
    }

    @Test
    fun `처음 받은 카드의 합계가 17점 이상이면 카드를 추가로 받을 수 없다`() {
        // given
        val cards = createOverDealerStandCards()
        // when
        dealer.receiveInitialCards(cards)
        // then
        dealer.canReceiveOneMoreCard() shouldBe false
    }
}
