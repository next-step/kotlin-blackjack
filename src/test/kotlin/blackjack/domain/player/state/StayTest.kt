package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.player.state.BlackJackTest.Companion.TEST_BLACKJACK
import blackjack.domain.player.state.BustTest.Companion.TEST_BUST
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
        val stay = MAXIMUM_STAY
        val stayHands = MAXIMUM_STAY

        assertAll(
            { assertThat(stay.score()).isNotEqualTo(Score.ZERO) },
            { assertThat(stay.score()).isEqualTo(stayHands.score()) },
            { assertThat(stay.score().isBust()).isFalse },
        )
    }

    @Test
    fun `Stay 상태는 실행이 종료된 상태이다`() {
        val stay = MAXIMUM_STAY

        assertThat(stay.isFinished()).isTrue
    }

    @Test
    fun `Stay 상태는 Stay 상태가 될 수 없다`() {
        val stay = MAXIMUM_STAY

        val exception = assertThrows<InvalidMapToPlayStateException> { stay.stay() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(stay::class.toString()))
    }

    @Test
    fun `Stay 상태는 카드를 추가할 수 없다`() {
        val stay = MAXIMUM_STAY
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { stay.draw(extraCard) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(stay::class.toString()))
    }

    @Test
    fun `Stay 상태는 BlackJack 상태와 매칭시 대해서 패 결과를 얻는다`() {
        val stay = MAXIMUM_STAY
        val blackJackMatchResult = stay.match(TEST_BLACKJACK)

        assertThat(blackJackMatchResult).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `Stay 상태는 Bust 상태와 매칭시 대해서 승 결과를 얻는다`() {
        val stay = MINIMUM_STAY
        val bustMatchResult = stay.match(TEST_BUST)

        assertThat(bustMatchResult).isEqualTo(MatchResult.WIN)
    }

    @Test
    fun `Stay 상태는 동일한 스코어의 Stay 상태와 매칭시 대해서 무승부 결과를 얻는다`() {
        val stay = MINIMUM_STAY
        val sameStayMatchResult = stay.match(MINIMUM_STAY)

        assertThat(sameStayMatchResult).isEqualTo(MatchResult.DRAW)
    }

    @Test
    fun `Stay 상태는 기존보다 스코어가 높은 Stay 상태와 매칭시 대해서 패 결과를 얻는다`() {
        val stay = MINIMUM_STAY
        val sameStayMatchResult = stay.match(MAXIMUM_STAY)

        assertThat(sameStayMatchResult).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `Stay 상태는 기존보다 스코어가 낮은 Stay 상태와 매칭시 대해서 패 결과를 얻는다`() {
        val stay = MAXIMUM_STAY
        val sameStayMatchResult = stay.match(DEALER_STAY)

        assertThat(sameStayMatchResult).isEqualTo(MatchResult.WIN)
    }

    companion object {
        val MINIMUM_STAY: Stay = Stay(Hands.from(minimumStayCards()))
        val DEALER_STAY: Stay = Stay(Hands.from(dealerStayCards()))
        val MAXIMUM_STAY: Stay = Stay(Hands.from(stayCards()))

        fun minimumStayCards() = listOf(
            Card(Suit.CLUB, Denomination.TWO),
            Card(Suit.CLUB, Denomination.THREE),
        )

        fun dealerStayCards() = listOf(
            Card(Suit.CLUB, Denomination.TEN),
            Card(Suit.CLUB, Denomination.SEVEN),
        )

        fun stayCards(): List<Card> = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.JACK),
            Card(Suit.CLUB, Denomination.QUEEN)
        )
    }
}
