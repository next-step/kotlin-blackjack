package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Score
import blackjack.domain.card.Suit
import blackjack.domain.player.state.hands.Hands
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

    private lateinit var dealerStay: Stay

    @BeforeEach
    internal fun setUp() {
        minimumStay = Stay(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.TWO))
            .draw(Card(Suit.CLUB, Denomination.THREE))
        )

        maximumStay = Stay(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.JACK))
            .draw(Card(Suit.CLUB, Denomination.QUEEN))
        )

        dealerStay = Stay(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.TEN))
            .draw(Card(Suit.CLUB, Denomination.SEVEN))
        )
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
        val stay = maximumStay
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { stay.draw(extraCard) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(stay::class.toString()))
    }

    @Test
    fun `Stay 상태는 BlackJack 상태와 매칭시 대해서 패 결과를 얻는다`() {
        val blackJack = BlackJack(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val blackJackMatchResult = maximumStay.match(blackJack)

        assertThat(blackJackMatchResult).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `Stay 상태는 Bust 상태와 매칭시 대해서 승 결과를 얻는다`() {
        val bust = Bust(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.TWO))
                .draw(Card(Suit.CLUB, Denomination.JACK))
                .draw(Card(Suit.CLUB, Denomination.QUEEN))
        )
        val bustMatchResult = minimumStay.match(bust)

        assertThat(bustMatchResult).isEqualTo(MatchResult.WIN)
    }

    @Test
    fun `Stay 상태는 동일한 스코어의 Stay 상태와 매칭시 대해서 무승부 결과를 얻는다`() {
        val other = Stay(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.TWO))
            .draw(Card(Suit.CLUB, Denomination.THREE))
        )
        val sameStayMatchResult = minimumStay.match(other)

        assertThat(sameStayMatchResult).isEqualTo(MatchResult.DRAW)
    }

    @Test
    fun `Stay 상태는 기존보다 스코어가 높은 Stay 상태와 매칭시 대해서 패 결과를 얻는다`() {
        val sameStayMatchResult = minimumStay.match(maximumStay)

        assertThat(sameStayMatchResult).isEqualTo(MatchResult.LOSE)
    }

    @Test
    fun `Stay 상태는 기존보다 스코어가 낮은 Stay 상태와 매칭시 대해서 패 결과를 얻는다`() {
        val sameStayMatchResult = maximumStay.match(dealerStay)

        assertThat(sameStayMatchResult).isEqualTo(MatchResult.WIN)
    }
}
