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

@DisplayName("BlackJack 상태(BlackJack)")
internal class BlackJackTest {

    private lateinit var blackJack: BlackJack

    @BeforeEach
    internal fun setUp() {
        blackJack = BlackJack(Hands.EMPTY
            .draw(Card(Suit.CLUB, Denomination.ACE))
            .draw(Card(Suit.CLUB, Denomination.JACK))
        )
    }

    @Test
    fun `BlackJack 상태는 스코어는 21이다`() {
        assertThat(blackJack.score()).isEqualTo(Score.from(21))
    }

    @Test
    fun `BlackJack 상태는 실행이 종료된 상태이다`() {
        assertThat(blackJack.isFinished()).isTrue
    }

    @Test
    fun `BlackJack 상태는 Stay 상태가 될 수 없다`() {
        val exception = assertThrows<InvalidMapToPlayStateException> { blackJack.stay() }
        assertThat(exception.message).isEqualTo("'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다".format(blackJack::class.toString()))
    }

    @Test
    fun `BlackJack 상태는 카드를 추가할 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.THREE)

        val exception = assertThrows<InvalidDrawException> { blackJack.draw(extraCard) }
        assertThat(exception.message).isEqualTo("'%s' 타입은 카드를 추가할 수 없습니다".format(blackJack::class.toString()))
    }

    @Test
    fun `BlackJack 상태는 BlackJack 이외의 상태와 매칭시 대해서 승리 결과를 얻는다`() {
        val bust = Bust(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.TWO))
                .draw(Card(Suit.CLUB, Denomination.JACK))
                .draw(Card(Suit.CLUB, Denomination.QUEEN))
        )
        val maximumStay = Stay(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.JACK))
                .draw(Card(Suit.CLUB, Denomination.QUEEN))
        )

        val bustMatchResult = blackJack.match(bust)
        val stayMatchResult = blackJack.match(maximumStay)

        assertAll(
            { assertThat(bustMatchResult).isEqualTo(MatchResult.WIN) },
            { assertThat(stayMatchResult).isEqualTo(MatchResult.WIN) },
        )
    }

    @Test
    fun `BlackJack 상태는 BlackJack 상태와 매칭시 대해서 무승부 결과를 얻는다`() {
        val other = BlackJack(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.JACK))
        )

        val blackJackMatchResult = blackJack.match(other)

        assertThat(blackJackMatchResult).isEqualTo(MatchResult.DRAW)
    }
}
