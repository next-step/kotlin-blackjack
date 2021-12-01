package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.util.PlayerStateTestFixture.createHands
import blackjack.error.InvalidCalculateScoreException
import blackjack.error.InvalidProfitException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Hit 상태(Hit)")
internal class HitTest {

    private lateinit var hit: Hit

    @BeforeEach
    internal fun setUp() {
        hit = Hit(createHands(Suit.CLUB, Denomination.ACE, Denomination.TWO))
    }

    @Test
    fun `점수를 계산할 수 없다`() {
        val exception = assertThrows<InvalidCalculateScoreException> { hit.score() }

        assertThat(exception.message).isEqualTo("'%s' 타입은 스코어를 계산할 수 없습니다".format(hit::class.toString()))
    }

    @Test
    fun `히트 상태는 실행중인 상태이다`() {
        val actual = hit.isFinished()

        assertThat(actual).isFalse
    }

    @Test
    fun `스테이 상태가 될 수 있다`() {
        val expected = Stay(hit.hands)

        val actual = hit.stay()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `히트 상태가 유지될 수 있다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)
        val nextState = hit.draw(extraCard)

        assertThat(nextState).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `버스트 상태가 될 수 있다`() {
        val nextState = hit
            .draw(Card(Suit.CLUB, Denomination.JACK))
            .draw(Card(Suit.CLUB, Denomination.QUEEN))

        assertThat(nextState).isExactlyInstanceOf(Bust::class.java)
    }

    @Test
    fun `배팅 금액을 받을 수 없다`() {
        val exception = assertThrows<InvalidProfitException> { hit.profit(Ready(), Money()) }

        assertThat(exception.message).isEqualTo("'%s' 타입은 이윤을 구할 수 없다".format(hit::class.toString()))
    }
}
