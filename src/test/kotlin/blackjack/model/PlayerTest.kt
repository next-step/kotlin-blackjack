package blackjack.model

import blackjack.dto.BlackJackResult
import blackjack.dto.Card
import blackjack.dto.Number
import blackjack.dto.Suit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PlayerTest {

    @ParameterizedTest
    @EmptySource
    fun `플레이어 이름은 빈 값이 될 수 없다`(name: String) {
        assertThatIllegalArgumentException().isThrownBy {
            Player(name)
        }
    }

    @Test
    fun `초기 값은 카드가 한장도 없고 hit 상태이다`() {
        val player = Player("test")
        assertThat(player.cards).isEmpty()
        assertThat(player.hit).isTrue()
    }

    @Test
    fun `hit 상태를 변화시키면 hit 상태가 변한다`() {
        val player = Player("test")
        player.noMoreHit()
        assertThat(player.hit).isFalse()
    }

    @ParameterizedTest
    @MethodSource("cardPointsCandidate")
    fun `카드 점수를 구한다`(cardList: List<Card>, expected: Int) {
        val player = Player("test")
        player.addCards(cardList)
        assertThat(player.getPoints()).isEqualTo(expected)
    }

    @Test
    fun `카드를 추가한다`() {
        val player = Player("test")
        assertThat(player.cards).isEmpty()
        player.addCard(Card(Suit.SPADE, Number.ACE))
        assertThat(player.cards).hasSize(1)
        player.addCards(listOf(Card(Suit.DIAMOND, Number.JACK), Card(Suit.HEART, Number.TWO)))
        assertThat(player.cards).hasSize(3)
    }

    @Test
    fun `21이 넘으면 자동으로 hit 상태가 변한다`() {
        val player = Player("test")
        assertThat(player.hit).isTrue()
        player.addCards(listOf(Card(Suit.SPADE, Number.QUEEN), Card(Suit.DIAMOND, Number.JACK)))
        player.addCard(Card(Suit.HEART, Number.KING))
        assertThat(player.hit).isFalse()
    }

    @Test
    fun `결과를 생성하지 않고 호출하면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            val player = Player("test")
            player.blackJackResult
        }
    }

    @ParameterizedTest
    @MethodSource("compareTestInput")
    fun `플래이어와 결과를 비교한다`(
        aCards: List<Card>,
        bCards: List<Card>,
        aResult: BlackJackResult,
        bResult: BlackJackResult
    ) {
        // given
        val playerA = Player("a")
        val playerB = Player("b")
        playerA.addCards(aCards)
        playerB.addCards(bCards)

        // when
        playerA.compare(playerB)
        playerB.compare(playerA)

        // then
        assertAll(
            { assertThat(playerA.blackJackResult).isEqualTo(aResult) },
            { assertThat(playerB.blackJackResult).isEqualTo(bResult) },
        )
    }

    companion object {
        @JvmStatic
        fun cardPointsCandidate(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Number.ACE),
                        Card(Suit.DIAMOND, Number.JACK)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Number.ACE),
                        Card(Suit.DIAMOND, Number.ACE)
                    ),
                    12
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Number.TWO),
                        Card(Suit.DIAMOND, Number.THREE)
                    ),
                    5
                ),
            )
        }

        @JvmStatic
        fun compareTestInput(): Stream<Arguments> {
            return Stream.of(
                // a < b < 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.ACE), Card(Suit.SPADE, Number.SIX)),
                    BlackJackResult(15, 0, 1),
                    BlackJackResult(17, 1, 0)
                ),
                // a < 21 < b
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.KING)),
                    listOf(Card(Suit.SPADE, Number.KING), Card(Suit.SPADE, Number.TEN), Card(Suit.SPADE, Number.THREE)),
                    BlackJackResult(20, 1, 0),
                    BlackJackResult(23, 0, 1)
                ),
                // 21 < a, b
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.KING), Card(Suit.DIAMOND, Number.JACK)),
                    listOf(Card(Suit.SPADE, Number.KING), Card(Suit.SPADE, Number.TEN), Card(Suit.SPADE, Number.THREE)),
                    BlackJackResult(30, 0, 1),
                    BlackJackResult(23, 0, 1)
                ),
                // a = b < 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.SEVEN), Card(Suit.SPADE, Number.EIGHT)),
                    BlackJackResult(15, 0, 1),
                    BlackJackResult(15, 0, 1)
                ),
            )
        }
    }
}
