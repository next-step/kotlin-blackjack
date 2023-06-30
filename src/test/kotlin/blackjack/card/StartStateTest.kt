package blackjack.card

import blackjack.card.helper.CardsTestFactory
import blackjack.card.helper.StartStateTestHelper
import domain.card.Card
import domain.card.Cards
import domain.state.Blackjack
import domain.state.Hit
import domain.state.Stand
import domain.state.StartState
import io.kotest.assertions.throwables.shouldThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StartStateTest {

    @ParameterizedTest
    @MethodSource("getStartStateTestData")
    fun `플레이어는 카드 두 장을 지급 받으면 시작(Start)이라는 상태가 된다`(cards: Cards) {
        val actual = StartState.start(cards)
        assertThat(actual is StartState).isTrue
    }

    @ParameterizedTest
    @MethodSource("getBlackjackStateTestData")
    fun `플레이어는 시작(Start) 상태에서 카드가 A 한 장과 10에 해당하는 카드(10, J, K, Q 중 하나)라면 블랙잭(Blackjack)이라는 종료 상태가 된다`(
        cards: Cards,
    ) {
        val actual = StartState.start(cards)
        assertThat(actual is Blackjack).isTrue
    }

    @ParameterizedTest
    @MethodSource("getHitStateTestData")
    fun `플레이어는 시작(Start) 상태에서 카드를 더 받으면 힛(Hit)이라는 진행 상태가 된다`(cards: Cards, newCard: Card) {
        val startState = StartState.start(cards)
        val hitState = startState.draw(newCard)
        assertThat(hitState is Hit).isTrue
    }

    @ParameterizedTest
    @MethodSource("getStandStateTestData")
    fun `플레이어는 시작(Start) 상태에서 카드를 받지 않으면 스탠드(Stand)라는 종료 상태가 된다`(cards: Cards) {
        val startState = StartState.start(cards)
        val actual = startState.stop()
        assertThat(actual is Stand).isTrue
    }

    @Test
    fun `시작(Start) 상태에서 승패를 조회하면 UnsupportedOperationException 이 발생`() {
        val cards = CardsTestFactory.makeStartCards()

        val state = StartState.start(cards)

        shouldThrow<UnsupportedOperationException> {
            state.getPlayerGameResult(state)
        }
    }

    @Test
    fun `시작(Start) 상태에서 수익을 조회하면 UnsupportedOperationException 이 발생`() {
        val cards = CardsTestFactory.makeStartCards()

        val state = StartState.start(cards)

        shouldThrow<UnsupportedOperationException> {
            state.getRevenueRate(state)
        }
    }

    companion object {
        @JvmStatic
        fun getStartStateTestData(): List<Arguments> = StartStateTestHelper.getStartStateTestData()

        @JvmStatic
        fun getStandStateTestData(): List<Arguments> = StartStateTestHelper.getStartStateTestData()

        @JvmStatic
        fun getBlackjackStateTestData(): List<Arguments> = StartStateTestHelper.getBlackjackStateTestData()

        @JvmStatic
        fun getHitStateTestData(): List<Arguments> = StartStateTestHelper.getHitStateTestData()
    }
}
