package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
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

@DisplayName("Bust 상태(Bust)")
internal class BustTest {

    private lateinit var bust: Bust

    @BeforeEach
    internal fun setUp() {
        bust = Bust(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.TWO))
                .draw(Card(Suit.CLUB, Denomination.JACK))
                .draw(Card(Suit.CLUB, Denomination.QUEEN))
        )
    }

    @Test
    fun `Bust 상태는 스코어는 21초과다`() {
        assertThat(bust.score().score).isGreaterThan(21)
    }

    @Test
    fun `Bust 상태는 실행이 종료된 상태이다`() {
        assertThat(bust.isFinished()).isTrue
    }

    @Test
    fun `Bust 상태는 Stay 상태가 될 수 없다`() {
        val exception = assertThrows<InvalidMapToPlayStateException> { bust.stay() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(bust::class.toString()))
    }

    @Test
    fun `Bust 상태는 카드를 추가할 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { bust.draw(extraCard) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(bust::class.toString()))
    }

    @Test
    fun `Bust 상태는 Bust 이외의 상태와 매칭시 대해서 패 결과를 얻는다`() {
        val blackJack = BlackJack(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val minimumStay = Stay(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.TWO))
                .draw(Card(Suit.CLUB, Denomination.THREE))
        )

        val stayMatchResult = bust.match(minimumStay)
        val blackJackMatchResult = bust.match(blackJack)

        assertAll(
            { assertThat(stayMatchResult).isEqualTo(MatchResult.LOSE) },
            { assertThat(blackJackMatchResult).isEqualTo(MatchResult.LOSE) },
        )
    }

    @Test
    fun `Bust 상태는 Bust 상태와 매칭시 대해서 무승부 결과를 얻는다`() {
        val other = Bust(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.TWO))
                .draw(Card(Suit.CLUB, Denomination.JACK))
                .draw(Card(Suit.CLUB, Denomination.QUEEN))
        )

        val bustMatchResult = bust.match(other)

        assertThat(bustMatchResult).isEqualTo(MatchResult.DRAW)
    }
}
