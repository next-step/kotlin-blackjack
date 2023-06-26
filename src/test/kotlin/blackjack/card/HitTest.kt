package blackjack.card

import blackjack.card.helper.CardsTestFactory
import blackjack.card.helper.HitTestHelper
import domain.card.Card
import domain.card.Cards
import domain.state.Burst
import domain.state.Hit
import domain.state.Stand
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HitTest {

    @ParameterizedTest
    @MethodSource("getHitStateTestData")
    fun `플레이어는 Hit 상태에서 카드를 더 받았을 때 합이 21 이하라면 Hit 이라는 진행 상태가 된다`(
        cards: Cards,
        newCard: Card,
    ) {
        val hit = Hit(cards)

        val newState = hit.draw(newCard)

        (newState is Hit) shouldBe true
    }

    @ParameterizedTest
    @MethodSource("getBurstStateTestData")
    fun `플레이어는 Hit 상태에서 카드를 더 받았을 때 합이 21 초과라면 Burst 라는 종료 상태`(
        cards: Cards,
        newCard: Card,
    ) {
        val hit = Hit(cards)

        val newState = hit.draw(newCard)

        (newState is Burst) shouldBe true
    }

    @ParameterizedTest
    @MethodSource("getStandStateTestData")
    fun `플레이어는 힛(Hit) 상태에서 더 이상 카드를 받지 않는다고 하면 스탠드(Stand)라는 종료 상태`(cards: Cards) {
        val hit = Hit(cards)

        val newState = hit.stop()

        (newState is Stand) shouldBe true
    }

    @Test
    fun `힛(Hit) 상태에서 승패를 조회하면 UnsupportedOperationException 이 발생`() {
        val cards = CardsTestFactory.makeHitCarts()

        val hitState = Hit(cards)

        shouldThrow<UnsupportedOperationException> {
            hitState.getPlayerGameResult(hitState)
        }
    }

    @Test
    fun `힛(Hit) 상태에서 수익을를 조회하면 UnsupportedOperationException 이 발생`() {
        val cards = CardsTestFactory.makeHitCarts()

        val hitState = Hit(cards)

        shouldThrow<UnsupportedOperationException> {
            hitState.getRevenue(hitState)
        }
    }

    companion object {
        @JvmStatic
        fun getHitStateTestData(): List<Arguments> = HitTestHelper.getHitStateTestData()

        @JvmStatic
        fun getBurstStateTestData(): List<Arguments> = HitTestHelper.getBurstStateTestData()

        @JvmStatic
        fun getStandStateTestData(): List<Arguments> = HitTestHelper.getStandStateTestData()
    }
}
