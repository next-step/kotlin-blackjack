package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidCalculateScoreException
import blackjack.error.InvalidMatchException
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
        hit = Hit(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.TWO))
        )
    }

    @Test
    fun `Hit 상태는 스코어 계산을 할 수 없다`() {
        val exception = assertThrows<InvalidCalculateScoreException> { hit.score() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 스코어를 계산할 수 없습니다".format(hit::class.toString()))
    }

    @Test
    fun `Hit 상태는 아직 실행중인 상태이다`() {
        assertThat(hit.isFinished()).isFalse
    }

    @Test
    fun `Hit 상태는 Stay 상태가 될 수 있다`() {
        assertThat(hit.stay()).isEqualTo(Stay(hit.hands))
    }

    @Test
    fun `Hit 상태는 Hit 상태가 될 수 있다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)
        val nextState = hit.draw(extraCard)

        assertThat(nextState).isEqualTo(Hit(nextState.hands))
    }

    @Test
    fun `Hit 상태는 Bust 상태가 될 수 있다`() {
        val nextState = hit
            .draw(Card(Suit.CLUB, Denomination.JACK))
            .draw(Card(Suit.CLUB, Denomination.QUEEN))

        assertThat(nextState).isEqualTo(Bust(nextState.hands))
    }

    @Test
    fun `Hit 상태는 매칭을 할 수 없다`() {
        val otherState = Hit(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.TEN))
            .draw(Card(Suit.CLUB, Denomination.KING))
        )

        val exception = assertThrows<InvalidMatchException> { hit.match(otherState) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 매칭을 할 수 없다".format(hit::class.toString()))
    }
}
