package blackjack.model

import blackjack.dto.Card
import blackjack.dto.GameResult
import blackjack.dto.Money
import blackjack.dto.Number
import blackjack.dto.PlayerResultStatus
import blackjack.dto.PlayerStatus
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
        assertThat(player.status).isEqualTo(PlayerStatus.HIT)
    }

    @Test
    fun `stay 상태로 변화시키면 변하고 그 뒤로 다른 상태로 변하지 않는다`() {
        val player = Player("test")
        player.stay()
        assertThat(player.status).isEqualTo(PlayerStatus.STAY)
        player.bust()
        assertThat(player.status).isEqualTo(PlayerStatus.STAY)
    }

    @Test
    fun `bust 상태로 변화시키면 변하고 그 뒤로 다른 상태로 변하지 않는다`() {
        val player = Player("test")
        player.bust()
        assertThat(player.status).isEqualTo(PlayerStatus.BUST)
        player.stay()
        assertThat(player.status).isEqualTo(PlayerStatus.BUST)
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
    fun `21이 넘으면 자동으로 bust가 된다`() {
        val player = Player("test")
        assertThat(player.status).isEqualTo(PlayerStatus.HIT)
        player.addCards(listOf(Card(Suit.SPADE, Number.QUEEN), Card(Suit.DIAMOND, Number.JACK)))
        player.addCard(Card(Suit.HEART, Number.KING))
        assertThat(player.status).isEqualTo(PlayerStatus.BUST)
    }

    @Test
    fun `결과를 생성하지 않고 호출하면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            val player = Player("test")
            player.gameResult
        }
    }

    @ParameterizedTest
    @MethodSource("compareTestInput")
    fun `플래이어와 결과를 비교한다`(
        aCards: List<Card>,
        bCards: List<Card>,
        aResult: PlayerResultStatus,
        bResult: PlayerResultStatus
    ) {
        // given
        val playerA = Player("a")
        val playerB = Player("b")
        playerA.addCards(aCards).apply { if (aCards.size > 2) playerA.bust() }
        playerB.addCards(bCards).apply { if (bCards.size > 2) playerB.bust() }
        playerA.stay()
        playerB.stay()

        // when
        playerA.compare(playerB)
        playerB.compare(playerA)

        // then
        assertAll(
            { assertThat(playerA.gameResult?.playerResultStatus).isEqualTo(aResult) },
            { assertThat(playerB.gameResult?.playerResultStatus).isEqualTo(bResult) },
        )
    }

    @ParameterizedTest
    @MethodSource("getPriceTestInput")
    fun `결과에 따른 상금이 다르다`(
        resultStatus: PlayerResultStatus,
        expected: Double
    ) {
        val money = Money(1000)
        val player = Player("test").apply {
            setGameResult(GameResult(0, resultStatus))
            setBettingMoney(money)
        }

        assertThat(player.getPrice()).isEqualTo(money * expected)
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
                    PlayerResultStatus.LOSE,
                    PlayerResultStatus.WIN
                ),
                // a < 21 < b
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.KING)),
                    listOf(Card(Suit.SPADE, Number.KING), Card(Suit.SPADE, Number.TEN), Card(Suit.SPADE, Number.THREE)),
                    PlayerResultStatus.WIN,
                    PlayerResultStatus.LOSE
                ),
                // 21 < a, b
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.KING), Card(Suit.DIAMOND, Number.JACK)),
                    listOf(Card(Suit.SPADE, Number.KING), Card(Suit.SPADE, Number.TEN), Card(Suit.SPADE, Number.THREE)),
                    PlayerResultStatus.LOSE,
                    PlayerResultStatus.LOSE
                ),
                // a = b < 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.SEVEN), Card(Suit.SPADE, Number.EIGHT)),
                    PlayerResultStatus.TIE,
                    PlayerResultStatus.TIE
                ),
                // a < b = 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.ACE), Card(Suit.SPADE, Number.TEN)),
                    PlayerResultStatus.LOSE,
                    PlayerResultStatus.BLACKJACK
                ),
            )
        }

        @JvmStatic
        fun getPriceTestInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    PlayerResultStatus.BLACKJACK,
                    1.5
                ),
                Arguments.of(
                    PlayerResultStatus.WIN,
                    1.0
                ),
                Arguments.of(
                    PlayerResultStatus.TIE,
                    0.0
                ),
                Arguments.of(
                    PlayerResultStatus.LOSE,
                    -1.0
                ),
            )
        }
    }
}
