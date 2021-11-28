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
    fun `Stay 상태는 스코어는 0이 아니다`() {
        assertAll(
            { assertThat(minimumStay.score()).isNotEqualTo(Score.ZERO) },
            { assertThat(minimumStay.score().isBust()).isFalse },
        )
    }

    @Test
    fun `Stay 상태는 실행이 종료된 상태이다`() {
        assertThat(minimumStay.isFinished()).isTrue
    }

    @Test
    fun `Stay 상태는 Stay 상태가 될 수 없다`() {
        val exception = assertThrows<InvalidMapToPlayStateException> { minimumStay.stay() }

        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(minimumStay::class.toString()))
    }

    @Test
    fun `Stay 상태는 카드를 추가할 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { minimumStay.draw(extraCard) }

        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(minimumStay::class.toString()))
    }

    @Test
    fun `Stay 상태는 상대가 BlakJack일 경우의 수익률을 반환할 수 있다`() {
        assertThat(maximumStay.earningsRate(HEART_BLACKJACK)).isEqualTo(-1.0)
    }

    @Test
    fun `Stay 상태는 상대가 Bust일 경우의 수익률을 반환할 수 있다`() {
        assertThat(minimumStay.earningsRate(HEART_MINIMUM_BUST)).isEqualTo(1.0)
    }

    @Test
    fun `Stay 상태는 상대가 자신보다 큰 Stay일 경우의 수익률을 반환할 수 있다`() {
        assertThat(minimumStay.earningsRate(maximumStay)).isEqualTo(-1.0)
    }

    @Test
    fun `Stay 상태는 상대가 자신보다 작은 Stay일 경우의 수익률을 반환할 수 있다`() {
        assertThat(maximumStay.earningsRate(minimumStay)).isEqualTo(1.0)
    }

    @Test
    fun `Stay 상태는 상대가 자신과 동일한 Stay일 경우의 수익률을 반환할 수 있다`() {
        assertThat(maximumStay.earningsRate(maximumStay)).isEqualTo(0.0)
    }

    @Test
    fun `Stay 상태는 상대가 BlakJack일 경우의 이윤을 반환할 수 있다`() {
        assertThat(maximumStay.profit(HEART_BLACKJACK, Money(10))).isEqualTo(-10.0)
    }

    @Test
    fun `Stay 상태는 상대가 Bust일 경우의 이윤을 반환할 수 있다`() {
        assertThat(minimumStay.profit(HEART_MINIMUM_BUST, Money(10))).isEqualTo(10.0)
    }

    @Test
    fun `Stay 상태는 상대가 자신보다 큰 Stay일 경우의 이윤을 반환할 수 있다`() {
        assertThat(minimumStay.profit(maximumStay, Money(10))).isEqualTo(-10.0)
    }

    @Test
    fun `Stay 상태는 상대가 자신보다 작은 Stay일 경우의 이윤을 반환할 수 있다`() {
        assertThat(maximumStay.profit(minimumStay, Money(10))).isEqualTo(10.0)
    }

    @Test
    fun `Stay 상태는 상대가 자신과 동일한 Stay일 경우의 이윤을 반환할 수 있다`() {
        assertThat(maximumStay.profit(maximumStay, Money(10))).isEqualTo(0.0)
    }
}
