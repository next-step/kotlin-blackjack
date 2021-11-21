package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("시작 상태(Started)")
internal class StartedTest {

    @Test
    fun `스타트 상태는 비어있다`() {
        val started = Started()

        assertThat(started.isEmpty()).isTrue
    }

    @Test
    fun `스타트 스코어는 0이다`() {
        val started = Started()

        assertThat(started.score()).isEqualTo(Score.ZERO)
    }

    @Test
    fun `스타트는 아직 실행중인 상태이다`() {
        val started = Started()

        assertThat(started.isFinished()).isFalse
    }

    @Test
    fun `스타트는 Stay 상태가 될 수 있다`() {
        val started = Started()

        assertThat(started.stay()).isEqualTo(Stay(started.hands))
    }

    @Test
    fun `스타트는 BlackJack 상태가 될 수 있다`() {
        val started = Started()

        val blackJackCards = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.KING)
        )
        val blackJackHands = Hands.from(blackJackCards)

        assertThat(started.plus(blackJackCards)).isEqualTo(BlackJack(blackJackHands))
    }

    @Test
    fun `스타트는 Hit 상태가 될 수 있다`() {
        val started = Started()

        val hitCards = listOf(
            Card(Suit.CLUB, Denomination.TWO),
            Card(Suit.CLUB, Denomination.QUEEN),
        )
        val hitHands = Hands.from(hitCards)
        assertThat(started.plus(hitCards)).isEqualTo(Hit(hitHands))
    }

    @Test
    fun `스타트는 Bust 상태가 될 수 있다`() {
        val started = Started()

        val bustCards = listOf(
            Card(Suit.CLUB, Denomination.JACK),
            Card(Suit.CLUB, Denomination.QUEEN),
            Card(Suit.CLUB, Denomination.KING)
        )
        val bustHands = Hands.from(bustCards)

        assertThat(started.plus(bustCards)).isEqualTo(Bust(bustHands))
    }
}
