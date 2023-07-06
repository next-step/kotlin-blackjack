package blackjack

import domain.Player
import domain.card.Card
import domain.card.Denomination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["peter", "승현"])
    fun `Person 객체에 name 지정 테스트`(name: String) {
        assertThat(Player(name).name).isEqualTo(name)
    }

    @Test
    fun `플레이어에게 카드 나눠주기`() {
        val player = Player("peter")
        val cards = setOf(Card.spade(Denomination.ACE), Card.spade(Denomination.JACK))

        cards.forEach {
            player.hit(it)
        }

        assertThat(player.cards()).isEqualTo(cards)
    }

    @ParameterizedTest
    @MethodSource("카드 더 받을 수 있는지 확인 테스트 데이터")
    fun `카드 더 받을 수 있는지 확인 테스트`(cards: Set<Card>, condition: Boolean) {
        val player = Player("peter")

        cards.forEach {
            player.hit(it)
        }

        assertThat(player.canReceiveMoreCard()).isEqualTo(condition)
    }

    companion object {
        @JvmStatic
        fun `카드 더 받을 수 있는지 확인 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    setOf(Card.spade(Denomination.TWO), Card.spade(Denomination.THREE)), true
                ),
                Arguments.of(
                    setOf(Card.spade(Denomination.ACE), Card.diamond(Denomination.ACE)), true
                ),
                Arguments.of(
                    setOf(Card.spade(Denomination.ACE), Card.spade(Denomination.KING)), false
                ),
                Arguments.of(
                    setOf(Card.spade(Denomination.ACE), Card.spade(Denomination.NINE), Card.diamond(Denomination.ACE)),
                    false
                ),
                Arguments.of(
                    setOf(Card.spade(Denomination.TWO), Card.spade(Denomination.TEN), Card.spade(Denomination.KING)),
                    false
                ),
            )
        }
    }
}
