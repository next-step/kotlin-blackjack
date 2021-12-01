package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.util.PlayerStateTestFixture.BlackJackFixture.HEART_BLACKJACK
import blackjack.domain.util.PlayerStateTestFixture.BustFixture.HEART_MINIMUM_BUST
import blackjack.domain.util.PlayerStateTestFixture.createHands
import blackjack.error.InvalidDrawException
import blackjack.error.InvalidMapToPlayStateException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("Stay 상태(Stay)")
internal class StayTest {

    private lateinit var minimumStay: Stay

    private lateinit var maximumStay: Stay

    @BeforeEach
    internal fun setUp() {
        minimumStay = Stay(createHands(Suit.CLUB, Denomination.TWO, Denomination.THREE))
        maximumStay = Stay(createHands(Suit.CLUB, Denomination.ACE, Denomination.JACK, Denomination.QUEEN))
    }

    @Test
    fun `스테이 상태의 점수는 0이 아니다`() {
        assertAll(
            { assertThat(minimumStay.score()).isNotEqualTo(Score.ZERO) },
            { assertThat(minimumStay.score().isBust()).isFalse },
        )
    }

    @Test
    fun `실행이 종료된 상태이다`() {
        val actual = minimumStay.isFinished()

        assertThat(actual).isTrue
    }

    @Test
    fun `다른 스테이 상태가 될 수 없다`() {
        val exception = assertThrows<InvalidMapToPlayStateException> { minimumStay.stay() }

        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(minimumStay::class.toString()))
    }

    @Test
    fun `카드를 추가할 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { minimumStay.draw(extraCard) }

        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(minimumStay::class.toString()))
    }

    @Test
    fun `상대가 블랙잭일 때 수익률은 1배 손실이다`() {
        val otherState = HEART_BLACKJACK

        val actual = maximumStay.earningsRate(otherState)

        assertThat(actual).isEqualTo(-1.0)
    }

    @Test
    fun `상대가 버스트일 때 수익률은 1배다`() {
        val otherState = HEART_MINIMUM_BUST

        val actual = maximumStay.earningsRate(otherState)

        assertThat(actual).isEqualTo(1.0)
    }

    @Test
    fun `상대가 자신보다 점수가 큰 스테이일 때 수익률은 1배 손실이다`() {
        val otherState = maximumStay

        val actual = minimumStay.earningsRate(otherState)

        assertThat(actual).isEqualTo(-1.0)
    }

    @Test
    fun `상대가 자신보다 작은 스테이일 때 수익률은 1배다`() {
        val otherState = minimumStay

        val actual = maximumStay.earningsRate(otherState)

        assertThat(actual).isEqualTo(1.0)
    }

    @Test
    fun `상대가 자신과 동일한 스테이일 때 수익률은 0이다`() {
        val otherState = maximumStay

        val actual = maximumStay.earningsRate(otherState)

        assertThat(actual).isEqualTo(0.0)
    }

    @Test
    fun `상대가 블랙잭일 때는 배팅 금액을 모두 잃는다`() {
        val otherState = HEART_BLACKJACK
        val betMoney = Money(10)

        val actual = maximumStay.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(-10.0)
    }

    @Test
    fun `상대가 버스트일 때 배팅 금액과 1배를 받는다`() {
        val otherState = HEART_MINIMUM_BUST
        val betMoney = Money(10)

        val actual = minimumStay.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(10.0)
    }

    @Test
    fun `상대가 자신보다 큰 스테이일 때 배팅 금액을 모두 잃는다`() {
        val otherState = maximumStay
        val betMoney = Money(10)

        val actual = minimumStay.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(-10.0)
    }

    @Test
    fun `상대가 자신보다 작은 스테이일 때 배팅 금액과 1배를 받는다`() {
        val otherState = minimumStay
        val betMoney = Money(10)

        val actual = maximumStay.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(10.0)
    }

    @Test
    fun `상대가 자신과 동일한 스테이일 때 배팅 금액을 모두 돌려받는다`() {
        val otherState = maximumStay
        val betMoney = Money(10)

        val actual = maximumStay.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(0.0)
    }
}
