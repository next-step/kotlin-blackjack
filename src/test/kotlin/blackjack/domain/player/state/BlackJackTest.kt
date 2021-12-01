package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.util.PlayerStateTestFixture.BlackJackFixture.HEART_BLACKJACK
import blackjack.domain.util.PlayerStateTestFixture.BustFixture.HEART_MINIMUM_BUST
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.HEART_MAXIMUM_STAY
import blackjack.domain.util.PlayerStateTestFixture.createHands
import blackjack.error.InvalidDrawException
import blackjack.error.InvalidMapToPlayStateException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("BlackJack 상태(BlackJack)")
internal class BlackJackTest {

    private lateinit var blackJack: BlackJack

    @BeforeEach
    internal fun setUp() {
        blackJack = BlackJack(createHands(Suit.CLUB, Denomination.ACE, Denomination.JACK))
    }

    @Test
    fun `블랙잭일 때의 점수는 21이다`() {
        val actual = blackJack.score()

        assertThat(actual).isEqualTo(Score.from(21))
    }

    @Test
    fun `블랙잭일 때는 실행이 종료된 상태이다`() {
        val actual = blackJack.isFinished()

        assertThat(actual).isTrue
    }

    @Test
    fun `블랙잭일 때는 스테이 상태가 될 수 없다`() {
        val exception = assertThrows<InvalidMapToPlayStateException> { blackJack.stay() }

        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(blackJack::class.toString()))
    }

    @Test
    fun `블랙잭일 때는 카드를 추가할 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)
        val exception = assertThrows<InvalidDrawException> { blackJack.draw(extraCard) }

        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(blackJack::class.toString()))
    }

    @Test
    fun `상대도 블랙잭일 때 수익률은 0이다`() {
        val otherState = HEART_BLACKJACK

        val actual = blackJack.earningsRate(otherState)

        assertThat(actual).isEqualTo(0.0)
    }

    @Test
    fun `상대가 버스트일 때 수익률은 1배 반이다`() {
        val otherState = HEART_MINIMUM_BUST

        val actual = blackJack.earningsRate(otherState)

        assertThat(actual).isEqualTo(1.5)
    }

    @Test
    fun `상대가 스테이일 때 수익률은 1배 반이다`() {
        val otherState = HEART_MAXIMUM_STAY

        val actual = blackJack.earningsRate(otherState)

        assertThat(actual).isEqualTo(1.5)
    }

    @Test
    fun `상대도 블랙잭일 때는 배팅 금액을 모두 돌려받는다`() {
        val otherState = HEART_BLACKJACK
        val betMoney = Money(10)

        val actual = blackJack.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(0.0)
    }

    @Test
    fun `상대가 버스트일 때는 배팅 금액과 1배 반을 받는다`() {
        val otherState = HEART_MINIMUM_BUST
        val betMoney = Money(10)

        val actual = blackJack.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(15.0)
    }

    @Test
    fun `상대가 스테이일 때는 배팅 금액과 1배 반을 받는다`() {
        val otherState = HEART_MAXIMUM_STAY
        val betMoney = Money(10)

        val actual = blackJack.profit(otherState, betMoney)

        assertThat(actual).isEqualTo(15.0)
    }
}
