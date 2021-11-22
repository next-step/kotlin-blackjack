package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidDrawException
import blackjack.error.InvalidMapToPlayStateException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("Stay 상태(Stay)")
internal class StayTest {

    @Test
    fun `Stay 상태는 스코어는 0이 아니다`() {
        val stay = testStay()
        val stayHands = stayHands()

        assertAll(
            { assertThat(stay.score()).isNotEqualTo(Score.ZERO) },
            { assertThat(stay.score()).isEqualTo(stayHands.score()) },
            { assertThat(stay.score().isBust()).isFalse },
        )
    }

    @Test
    fun `Stay 상태는 실행이 종료된 상태이다`() {
        val blackJack = testStay()

        assertThat(blackJack.isFinished()).isTrue
    }

    @Test
    fun `Stay 상태는 Stay 상태가 될 수 없다`() {
        val blackJack = testStay()

        val exception = assertThrows<InvalidMapToPlayStateException> { blackJack.stay() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(blackJack::class.toString()))
    }

    @Test
    fun `Stay 상태는 카드를 추가할 수 없다`() {
        val blackJack = testStay()
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { blackJack.draw(extraCard) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(blackJack::class.toString()))
    }

    companion object {
        private fun testStay(): Stay = Stay(stayHands())

        private fun stayHands(): Hands = Hands.from(stayCards())

        private fun stayCards(): List<Card> = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.JACK),
            Card(Suit.CLUB, Denomination.QUEEN)
        )
    }
}
