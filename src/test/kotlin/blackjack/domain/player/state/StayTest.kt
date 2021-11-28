package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
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
}
