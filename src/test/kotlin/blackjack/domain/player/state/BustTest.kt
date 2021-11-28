package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
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

@DisplayName("Bust 상태(Bust)")
internal class BustTest {

    private lateinit var bust: Bust

    @BeforeEach
    internal fun setUp() {
        bust = Bust(createHands(Suit.CLUB, Denomination.ACE, Denomination.TWO, Denomination.JACK, Denomination.QUEEN))
    }

    @Test
    fun `Bust 상태는 스코어는 21초과다`() {
        assertThat(bust.score().score).isGreaterThan(21)
    }

    @Test
    fun `Bust 상태는 실행이 종료된 상태이다`() {
        assertThat(bust.isFinished()).isTrue
    }

    @Test
    fun `Bust 상태는 Stay 상태가 될 수 없다`() {
        val exception = assertThrows<InvalidMapToPlayStateException> { bust.stay() }

        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(bust::class.toString()))
    }

    @Test
    fun `Bust 상태는 카드를 추가할 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { bust.draw(extraCard) }

        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(bust::class.toString()))
    }

    @Test
    fun `Bust 상태는 상대가 BlakJack일 경우의 수익률을 반환할 수 있다`() {
        assertThat(bust.earningsRate(HEART_BLACKJACK)).isEqualTo(-1.0)
    }

    @Test
    fun `Bust 상태는 상대가 Bust일 경우의 수익률을 반환할 수 있다`() {
        assertThat(bust.earningsRate(HEART_MINIMUM_BUST)).isEqualTo(0.0)
    }

    @Test
    fun `Bust 상태는 상대가 Stay일 경우의 수익률을 반환할 수 있다`() {
        assertThat(bust.earningsRate(HEART_MAXIMUM_STAY)).isEqualTo(-1.0)
    }

    @Test
    fun `Bust 상태는 상대가 BlakJack일 경우의 이윤을 반환할 수 있다`() {
        assertThat(bust.profit(HEART_BLACKJACK, Money(10))).isEqualTo(-10.0)
    }

    @Test
    fun `Bust 상태는 상대가 Bust일 경우의 이윤을 반환할 수 있다`() {
        assertThat(bust.profit(HEART_MINIMUM_BUST, Money(10))).isEqualTo(0.0)
    }

    @Test
    fun `Bust 상태는 상대가 Stay일 경우의 이윤을 반환할 수 있다`() {
        assertThat(bust.profit(HEART_MAXIMUM_STAY, Money(10))).isEqualTo(-10.0)
    }
}
