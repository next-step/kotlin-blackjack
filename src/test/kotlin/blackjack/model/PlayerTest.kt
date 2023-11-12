package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Number
import blackjack.dto.Suit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
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
    }
}
