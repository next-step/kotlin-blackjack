package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.error.InvalidCalculateScoreException
import blackjack.error.InvalidProfitException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("준비 상태(Ready)")
internal class ReadyTest {

    private lateinit var ready: Ready

    @BeforeEach
    internal fun setUp() {
        ready = Ready()
    }

    @Test
    fun `점수를 계산할 수 없다`() {
        val exception = assertThrows<InvalidCalculateScoreException> { ready.score() }

        assertThat(exception.message).isEqualTo("'%s' 타입은 스코어를 계산할 수 없습니다".format(ready::class.toString()))
    }

    @Test
    fun `준비 상태는 실행중인 상태이다`() {
        val actual = ready.isFinished()

        assertThat(actual).isFalse
    }

    @Test
    fun `스테이 상태가 될 수 있다`() {
        val actual = ready.stay()

        assertThat(actual).isExactlyInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 하나만 추가할 경우 그대로 레디 상태다`() {
        assertThat(ready).isExactlyInstanceOf(Ready::class.java)
    }

    @Test
    fun `2개의 카드 점수 합이 21일 경우 블랙잭 상태다`() {
        val actual = ready
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.KING))

        assertThat(actual).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `2개의 카드 점수 합이 21 미민인 경우 히트 상태다`() {
        val actual = ready
            .draw(Card(Suit.CLUB, Denomination.TWO))
            .draw(Card(Suit.CLUB, Denomination.QUEEN))

        assertThat(actual).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `2개의 카드 점수 합이 21 초과인 경우 버스트 상태다`() {
        val actual = ready
            .draw(Card(Suit.CLUB, Denomination.JACK))
            .draw(Card(Suit.CLUB, Denomination.QUEEN))
            .draw(Card(Suit.CLUB, Denomination.KING))

        assertThat(actual).isExactlyInstanceOf(Bust::class.java)
    }

    @Test
    fun `배팅 금액을 받을 수 없다`() {
        val exception = assertThrows<InvalidProfitException> { ready.profit(Ready(), Money()) }

        assertThat(exception.message).isEqualTo("'%s' 타입은 이윤을 구할 수 없다".format(ready::class.toString()))
    }
}
