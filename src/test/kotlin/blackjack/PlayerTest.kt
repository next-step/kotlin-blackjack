package blackjack

import domain.Player
import domain.card.Card
import domain.card.Denomination
import domain.card.Diamond
import domain.card.Spade
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
        val cards = setOf(Spade.get(Denomination.ACE), Spade.get(Denomination.JACK))

        cards.forEach {
            player.dealCard(it)
        }

        assertThat(player.cards()).isEqualTo(cards)
    }

    @ParameterizedTest
    @MethodSource("카드 더 받을 수 있는지 확인 테스트 데이터")
    fun `카드 더 받을 수 있는지 확인 테스트`(cards: Set<Card>, condition: Boolean) {
        val player = Player("peter")

        cards.forEach {
            player.dealCard(it)
        }

        assertThat(player.canReceiveMoreCard()).isEqualTo(condition)
    }

    companion object {
        @JvmStatic
        fun `카드 더 받을 수 있는지 확인 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    setOf(Spade.get(Denomination.TWO), Spade.get(Denomination.THREE)), true
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.ACE), Diamond.get(Denomination.ACE)), true
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.ACE), Spade.get(Denomination.KING)), false
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.ACE), Spade.get(Denomination.NINE), Diamond.get(Denomination.ACE)),
                    false
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.TWO), Spade.get(Denomination.TEN), Spade.get(Denomination.KING)), false
                ),
            )
        }
    }
}
