package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.player.state.BustTest.Companion.testBust
import blackjack.domain.player.state.StayTest.Companion.testStay
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidDrawException
import blackjack.error.InvalidMapToPlayStateException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("BlackJack 상태(BlackJack)")
internal class BlackJackTest {

    @Test
    fun `BlackJack 상태는 스코어는 21이다`() {
        val blackJack = testBlackJack()

        assertThat(blackJack.score()).isEqualTo(Score.from(21))
    }

    @Test
    fun `BlackJack 상태는 실행이 종료된 상태이다`() {
        val blackJack = testBlackJack()

        assertThat(blackJack.isFinished()).isTrue
    }

    @Test
    fun `BlackJack 상태는 Stay 상태가 될 수 없다`() {
        val blackJack = testBlackJack()

        val exception = assertThrows<InvalidMapToPlayStateException> { blackJack.stay() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(blackJack::class.toString()))
    }

    @Test
    fun `BlackJack 상태는 카드를 추가할 수 없다`() {
        val blackJack = testBlackJack()
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { blackJack.draw(extraCard) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(blackJack::class.toString()))
    }

    @Test
    fun `BlackJack 상태는 BlackJack 이외의 상태와 매칭시 대해서 승리 결과를 얻는다`() {
        val blackJack = testBlackJack()
        val bustMatchResult = blackJack.match(testBust())
        val stayMatchResult = blackJack.match(testStay())

        assertAll(
            { assertThat(bustMatchResult).isEqualTo(MatchResult.WIN) },
            { assertThat(stayMatchResult).isEqualTo(MatchResult.WIN) },
        )
    }

    @Test
    fun `BlackJack 상태는 BlackJack 상태와 매칭시 대해서 무승부 결과를 얻는다`() {
        val blackJack = testBlackJack()
        val blackJackMatchResult = blackJack.match(testBlackJack())

        assertThat(blackJackMatchResult).isEqualTo(MatchResult.DRAW)
    }

    companion object {
        fun testBlackJack(): BlackJack = BlackJack(blackJackHands())

        private fun blackJackHands(): Hands = Hands.from(blackJackCards())

        private fun blackJackCards(): List<Card> = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.JACK)
        )
    }
}
