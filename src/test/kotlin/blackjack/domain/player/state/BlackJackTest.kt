package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.util.PlayerStateTestFixture
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
    fun `BlackJack 상태는 스코어는 21이다`() {
        assertThat(blackJack.score()).isEqualTo(Score.from(21))
    }

    @Test
    fun `BlackJack 상태는 실행이 종료된 상태이다`() {
        assertThat(blackJack.isFinished()).isTrue
    }

    @Test
    fun `BlackJack 상태는 Stay 상태가 될 수 없다`() {
        val exception = assertThrows<InvalidMapToPlayStateException> { blackJack.stay() }

        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(blackJack::class.toString()))
    }

    @Test
    fun `BlackJack 상태는 카드를 추가할 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)
        val exception = assertThrows<InvalidDrawException> { blackJack.draw(extraCard) }

        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(blackJack::class.toString()))
    }

    @Test
    fun `BlackJack 상태는 상대가 BlakJack일 경우의 수익률을 반환할 수 있다`() {
        assertThat(blackJack.earningsRate(HEART_BLACKJACK)).isEqualTo(0.0)
    }

    @Test
    fun `BlackJack 상태는 상대가 Bust일 경우의 수익률을 반환할 수 있다`() {
        assertThat(blackJack.earningsRate(HEART_MINIMUM_BUST)).isEqualTo(1.5)
    }

    @Test
    fun `BlackJack 상태는 상대가 Stay일 경우의 수익률을 반환할 수 있다`() {
        assertThat(blackJack.earningsRate(HEART_MAXIMUM_STAY)).isEqualTo(1.5)
    }


    @Test
    fun `BlackJack 상태는 상대가 BlakJack일 경우의 이윤을 반환할 수 있다`() {
        assertThat(blackJack.profit(HEART_BLACKJACK, Money(10))).isEqualTo(0.0)
    }

    @Test
    fun `BlackJack 상태는 상대가 Bust일 경우의 이윤을 반환할 수 있다`() {
        assertThat(blackJack.profit(HEART_MINIMUM_BUST, Money(10))).isEqualTo(15.0)
    }

    @Test
    fun `BlackJack 상태는 상대가 Stay일 경우의 이윤을 반환할 수 있다`() {
        assertThat(blackJack.profit(HEART_MAXIMUM_STAY, Money(10))).isEqualTo(15.0)
    }
}
