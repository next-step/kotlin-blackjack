package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidCalculateScoreException
import blackjack.error.InvalidMatchException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Hit 상태(Hit)")
internal class HitTest {

    @Test
    fun `Hit 상태는 스코어 계산을 할 수 없다`() {
        val hit = testHit()

        val exception = assertThrows<InvalidCalculateScoreException> { hit.score() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 스코어를 계산할 수 없습니다".format(hit::class.toString()))
    }

    @Test
    fun `Hit 상태는 아직 실행중인 상태이다`() {
        val hit = testHit()

        assertThat(hit.isFinished()).isFalse
    }

    @Test
    fun `Hit 상태는 Stay 상태가 될 수 있다`() {
        val hit = testHit()

        assertThat(hit.stay()).isEqualTo(Stay(hit.hands))
    }

    @Test
    fun `Hit 상태는 Hit 상태가 될 수 있다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)
        val hit = testHit()
            .draw(extraCard)

        assertThat(hit).isEqualTo(Hit(hit.hands))
    }

    @Test
    fun `Hit 상태는 Bust 상태가 될 수 있다`() {
        val extraCards = listOf(
            Card(Suit.CLUB, Denomination.JACK),
            Card(Suit.CLUB, Denomination.QUEEN)
        )
        val hit = testHit()
            .draw(extraCards[0])
            .draw(extraCards[1])

        assertThat(hit).isEqualTo(Bust(hit.hands))
    }

    @Test
    fun `Hit 상태는 매칭을 할 수 없다`() {
        val hit = testHit()

        val exception = assertThrows<InvalidMatchException> { hit.match(testHit()) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 매칭을 할 수 없다".format(hit::class.toString()))
    }

    companion object {
        private fun testHit(): Hit = Hit(hitHands())

        private fun hitHands() = Hands.from(hitCards())

        private fun hitCards() = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.TWO)
        )
    }
}
