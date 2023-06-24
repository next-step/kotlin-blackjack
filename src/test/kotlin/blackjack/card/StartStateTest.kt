package blackjack.card

import blackjack.card.helper.StartStateTestHelper
import domain.card.BlackjackCard
import domain.state.Blackjack
import domain.state.Hit
import domain.state.Stand
import domain.state.StartState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StartStateTest {

    @ParameterizedTest
    @MethodSource("getStartStateTestData")
    fun `플레이어는 카드 두 장을 지급 받으면 시작(Start)이라는 상태가 된다`(card1: BlackjackCard, card2: BlackjackCard) {
        val actual = StartState.start(card1, card2)
        assertThat(actual is StartState).isTrue
    }

    @ParameterizedTest
    @MethodSource("getBlackjackStateTestData")
    fun `플레이어는 시작(Start) 상태에서 카드가 A 한 장과 10에 해당하는 카드(10, J, K, Q 중 하나)라면 블랙잭(Blackjack)이라는 종료 상태가 된다`(
        card1: BlackjackCard,
        card2: BlackjackCard,
    ) {
        val actual = StartState.start(card1, card2)
        assertThat(actual is Blackjack).isTrue
    }

    @ParameterizedTest
    @MethodSource("getHitStateTestData")
    fun `플레이어는 시작(Start) 상태에서 카드를 더 받으면 힛(Hit)이라는 진행 상태가 된다`(
        card1: BlackjackCard,
        card2: BlackjackCard,
        newCard: BlackjackCard,
    ) {
        val startState = StartState.start(card1, card2)
        val hitState = startState.draw(newCard)
        assertThat(hitState is Hit).isTrue
    }

    @ParameterizedTest
    @MethodSource("getStartStateTestData")
    fun `플레이어는 시작(Start) 상태에서 카드를 받지 않으면 스탠드(Stand)라는 종료 상태가 된다`(
        card1: BlackjackCard,
        card2: BlackjackCard,
    ) {
        val startState = StartState.start(card1, card2)
        val actual = startState.stop()
        assertThat(actual is Stand).isTrue
    }

    companion object {
        @JvmStatic
        fun getStartStateTestData(): List<Arguments> = StartStateTestHelper.getStartStateTestData()

        @JvmStatic
        fun getBlackjackStateTestData(): List<Arguments> = StartStateTestHelper.getBlackjackStateTestData()
        @JvmStatic
        fun getHitStateTestData(): List<Arguments> = StartStateTestHelper.getHitStateTestData()
    }
}
