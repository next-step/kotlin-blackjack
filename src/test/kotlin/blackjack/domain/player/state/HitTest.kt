package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("시작 상태(StartedState)")
internal class HitTest {

    @Test
    fun `Hit 상태는 비어있지 않다`() {
        val hit = testHit()
        assertThat(hit.isEmpty()).isFalse
    }

    @Test
    fun `Hit 상태는 스코어는 0이 아니다`() {
        val hit = testHit()
        val hitHands = hitHands()

        assertAll(
            { assertThat(hit.score()).isNotEqualTo(Score.ZERO) },
            { assertThat(hit.score()).isEqualTo(hitHands.score()) },
        )
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
        val hit = testHit()

        val extraCards = listOf(Card(Suit.CLUB, Denomination.THREE))
        val expectedHands = hitHands() + extraCards

        assertThat(hit.plus(extraCards)).isEqualTo(Hit(expectedHands))
    }

    @Test
    fun `Hit 상태는 Bust 상태가 될 수 있다`() {
        val hit = testHit()

        val extraCards = listOf(
            Card(Suit.CLUB, Denomination.JACK),
            Card(Suit.CLUB, Denomination.QUEEN)
        )
        val expectedHands = hitHands() + extraCards

        assertThat(hit.plus(extraCards)).isEqualTo(Bust(expectedHands))
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
