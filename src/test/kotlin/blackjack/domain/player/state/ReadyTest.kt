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
    fun `Ready 상태는 스코어를 호출할 수 없다`() {
        val exception = assertThrows<InvalidCalculateScoreException> { ready.score() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 스코어를 계산할 수 없습니다".format(ready::class.toString()))
    }

    @Test
    fun `Ready 상태는 아직 실행중인 상태이다`() {
        assertThat(ready.isFinished()).isFalse
    }

    @Test
    fun `Ready 상태는 Stay 상태가 될 수 있다`() {
        assertThat(ready.stay()).isEqualTo(Stay(ready.hands))
    }

    @Test
    fun `Ready 상태는 카드를 하나만 추가할 경우 그대로 Ready 상태다`() {
        assertThat(ready).isEqualTo(Ready(ready.hands))
    }

    @Test
    fun `Ready 상태는 BlackJack 상태가 될 수 있다`() {
        val blackJack = ready
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.KING))

        assertThat(blackJack).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `Ready 상태는 Hit 상태가 될 수 있다`() {
        val hit = ready
            .draw(Card(Suit.CLUB, Denomination.TWO))
            .draw(Card(Suit.CLUB, Denomination.QUEEN))

        assertThat(hit).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `Ready 상태는 Bust 상태가 될 수 있다`() {
        val bust = ready
            .draw(Card(Suit.CLUB, Denomination.JACK))
            .draw(Card(Suit.CLUB, Denomination.QUEEN))
            .draw(Card(Suit.CLUB, Denomination.KING))

        assertThat(bust).isExactlyInstanceOf(Bust::class.java)
    }

    @Test
    fun `Ready 상태는 이윤을 구할 수 없다`() {
        val exception = assertThrows<InvalidProfitException> { ready.profit(Ready(), Money()) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 이윤을 구할 수 없다".format(ready::class.toString()))
    }
}
