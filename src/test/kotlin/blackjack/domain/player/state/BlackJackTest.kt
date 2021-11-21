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

@DisplayName("시작 상태(StartedState)")
internal class BlackJackTest {

    @Test
    fun `BlackJack 상태는 비어있지 않다`() {
        val blackJack = testBlackJack()
        assertThat(blackJack.isEmpty()).isFalse
    }

    @Test
    fun `BlackJack 상태는 스코어는 0이 아니다`() {
        val blackJack = testBlackJack()
        val blackJackHands = blackJackHands()

        assertAll(
            { assertThat(blackJack.score()).isNotEqualTo(Score.ZERO) },
            { assertThat(blackJack.score()).isEqualTo(blackJackHands.score()) },
            { assertThat(blackJack.score().isMaxiMum()).isTrue },
        )
    }

    @Test
    fun `BlackJack 상태는 실행이 종료된 상태이다`() {
        val blackJack = testBlackJack()

        assertThat(blackJack.isFinished()).isTrue
    }

    @Test
    fun `BlackJack 상태는 Stay 상태가 될 수 없다`() {
        val blackJack = testBlackJack()

        val exception = assertThrows<InvalidMapToStayException> { blackJack.stay() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 Stay 타입으로 전환이 불가능합니다".format(blackJack::class.toString()))
    }

    @Test
    fun `BlackJack 상태는 카드를 추가할 수 없다`() {
        val blackJack = testBlackJack()
        val extraCards = listOf(Card(Suit.CLUB, Denomination.THREE))

        val exception = assertThrows<InvalidAddCardsException> { blackJack.plus(extraCards) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(blackJack::class.toString()))
    }

    companion object {
        private fun testBlackJack(): BlackJack = BlackJack(blackJackHands())

        private fun blackJackHands(): Hands = Hands.from(blackJackCards())

        private fun blackJackCards(): List<Card> = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.JACK)
        )
    }
}
