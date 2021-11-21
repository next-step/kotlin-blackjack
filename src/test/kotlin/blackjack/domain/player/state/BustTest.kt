package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.error.InvalidAddCardsException
import blackjack.error.InvalidMapToStayException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("Bust 상태(Bust)")
internal class BustTest {

    @Test
    fun `Bust 상태는 비어있지 않다`() {
        val bust = testBust()
        assertThat(bust.isEmpty()).isFalse
    }

    @Test
    fun `Bust 상태는 스코어는 0이 아니다`() {
        val bust = testBust()
        val bustHands = bustHands()

        assertAll(
            { assertThat(bust.score()).isNotEqualTo(Score.ZERO) },
            { assertThat(bust.score()).isEqualTo(bustHands.score()) },
            { assertThat(bust.score().isBust()).isTrue },
        )
    }

    @Test
    fun `Bust 상태는 실행이 종료된 상태이다`() {
        val bust = testBust()

        assertThat(bust.isFinished()).isTrue
    }

    @Test
    fun `Bust 상태는 Stay 상태가 될 수 없다`() {
        val bust = testBust()

        val exception = assertThrows<InvalidMapToStayException> { bust.stay() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 Stay 타입으로 전환이 불가능합니다".format(bust::class.toString()))
    }

    @Test
    fun `Bust 상태는 카드를 추가할 수 없다`() {
        val bust = testBust()
        val extraCards = listOf(Card(Suit.CLUB, Denomination.THREE))

        val exception = assertThrows<InvalidAddCardsException> { bust.plus(extraCards) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(bust::class.toString()))
    }

    companion object {
        private fun testBust(): Bust = Bust(bustHands())

        private fun bustHands(): Hands = Hands.from(bustCards())

        private fun bustCards(): List<Card> = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.TWO),
            Card(Suit.CLUB, Denomination.JACK),
            Card(Suit.CLUB, Denomination.QUEEN),
        )
    }
}
