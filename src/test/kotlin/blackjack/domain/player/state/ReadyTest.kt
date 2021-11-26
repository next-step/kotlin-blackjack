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

@DisplayName("준비 상태(Ready)")
internal class ReadyTest {

    @Test
    fun `Ready 상태는 스코어를 호출할 수 없다`() {
        val ready = Ready()

        val exception = assertThrows<InvalidCalculateScoreException> { ready.score() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 스코어를 계산할 수 없습니다".format(ready::class.toString()))
    }

    @Test
    fun `Ready 상태는 아직 실행중인 상태이다`() {
        val ready = Ready()

        assertThat(ready.isFinished()).isFalse
    }

    @Test
    fun `Ready 상태는 Stay 상태가 될 수 있다`() {
        val ready = Ready()

        assertThat(ready.stay()).isEqualTo(Stay(ready.hands))
    }

    @Test
    fun `Ready 상태는 카드를 하나만 추가할 경우 그대로 Ready 상태다`() {
        val ready = Ready().draw(Card(Suit.CLUB, Denomination.ACE))

        assertThat(ready).isEqualTo(Ready(ready.hands))
    }

    @Test
    fun `Ready 상태는 BlackJack 상태가 될 수 있다`() {
        val blackJackCards = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.KING)
        )
        val blackJack = Ready()
            .draw(blackJackCards[0])
            .draw(blackJackCards[1])

        assertThat(blackJack).isEqualTo(BlackJack(Hands.from(blackJackCards)))
    }

    @Test
    fun `Ready 상태는 Hit 상태가 될 수 있다`() {
        val hitCards = listOf(
            Card(Suit.CLUB, Denomination.TWO),
            Card(Suit.CLUB, Denomination.QUEEN),
        )
        val hit = Ready()
            .draw(hitCards[0])
            .draw(hitCards[1])

        assertThat(hit).isEqualTo(Hit(Hands.from(hitCards)))
    }

    @Test
    fun `Ready 상태는 Bust 상태가 될 수 있다`() {
        val bustCards = listOf(
            Card(Suit.CLUB, Denomination.JACK),
            Card(Suit.CLUB, Denomination.QUEEN),
            Card(Suit.CLUB, Denomination.KING)
        )
        val bust = Ready()
            .draw(bustCards[0])
            .draw(bustCards[1])
            .draw(bustCards[2])

        assertThat(bust).isEqualTo(Bust(Hands.from(bustCards)))
    }

    @Test
    fun `Ready 상태는 매칭을 할 수 없다`() {
        val ready = Ready()

        val exception = assertThrows<InvalidMatchException> { ready.match(Ready()) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 매칭을 할 수 없다".format(ready::class.toString()))
    }
}
