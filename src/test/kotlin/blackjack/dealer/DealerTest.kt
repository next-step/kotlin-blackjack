package blackjack.dealer

import blackjack.card.CardFixture
import blackjack.card.Rank
import blackjack.player.Hand
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 객체 생성 테스트`() {
        val dealer = Dealer()

        dealer.name shouldBe "딜러"
        dealer.hand shouldBe Hand(cards = emptyList())
    }

    @Test
    fun `초기 패 상태를 전달하는 딜러 객체 생성 테스트`() {
        val initialCards =
            listOf(
                CardFixture.generateTestCard(rank = Rank.SIX),
                CardFixture.generateTestCard(rank = Rank.SEVEN),
            )

        val dealer =
            Dealer.ready(
                initialCards = initialCards,
            )

        dealer.hand shouldBe Hand(cards = initialCards)
    }

    @Test
    fun `딜러에 패 목록에 카드 추가 기능을 테스트한다`() {
        var dealer = Dealer()
        val drawnCard = CardFixture.generateTestCard(rank = Rank.TEN)
        dealer = dealer.hitCard(drawnCard)

        dealer.hand.cards shouldContainExactly listOf(drawnCard)
    }

    @Test
    fun `딜러의 패의 총 합이 21을 넘어가면 false를 반환한다`() {
        val dealer =
            Dealer(hand = Hand(
                cards = listOf(
                    CardFixture.generateTestCard(rank = Rank.SIX),
                    CardFixture.generateTestCard(rank = Rank.SEVEN),
                    CardFixture.generateTestCard(rank = Rank.TEN),
                    ),
                ),
        )

        dealer.isBust() shouldBe true
    }

    @Test
    fun `딜러의 초기 패의 총 합이 16이하면 카드를 더 받는다`() {
        val dealer =
            Dealer(hand = Hand(
                cards = listOf(
                    CardFixture.generateTestCard(rank = Rank.SIX),
                    CardFixture.generateTestCard(rank = Rank.SEVEN),
                    ),
                ),
        )

        dealer.shouldDraw() shouldBe true
    }
}
