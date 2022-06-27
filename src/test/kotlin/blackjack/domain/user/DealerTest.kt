package blackjack.domain.user

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.FIVE
import blackjack.domain.Denomination.SIX
import blackjack.domain.Denomination.TEN
import blackjack.domain.Stat
import blackjack.domain.Suit.DIAMOND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DealerTest {

    @Test
    fun `딜러는 이름을 가진다`() {
        val name = "딜러"

        val dealer = Dealer()

        assertThat(dealer.name).isEqualTo(name)
    }

    @Test
    fun `딜러는 16점일 때 카드를 추가로 받을 수 있다`() {
        val dealer = Dealer()

        dealer.addCards(createCards(ACE, FIVE))

        dealer.addCard(Card(DIAMOND, ACE))

        assertThat(dealer.sizeOfCards()).isEqualTo(3)
    }

    @Test
    fun `딜러는 17점일 때 카드를 추가로 받을 수 있다`() {
        val dealer = Dealer()

        dealer.addCards(createCards(ACE, SIX))

        assertThrows<IllegalStateException> { dealer.addCard(Card(DIAMOND, ACE)) }
    }

    @ParameterizedTest
    @MethodSource("compareArguments")
    fun `딜러와 Player는 비교할 수 있다`(dealer: Dealer, player: Player, stat: Stat) {
        val expected = dealer.versus(player)

        assertThat(expected).isEqualTo(stat)
    }

    companion object {
        @JvmStatic
        fun compareArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(createDealer(), createPlayer(), Stat.DRAW),
                Arguments.of(createDealer(ACE, TEN), createPlayer(ACE, TEN), Stat.DRAW),
                Arguments.of(createDealer(ACE, TEN), createPlayer(ACE, ACE), Stat.WIN),
                Arguments.of(createDealer(ACE, TEN), createPlayer(TEN, TEN), Stat.WIN),
                Arguments.of(createDealer(ACE, ACE), createPlayer(TEN, TEN), Stat.LOSE),
                Arguments.of(createDealer(TEN, TEN), createPlayer(ACE, TEN), Stat.LOSE),
                Arguments.of(createDealer(TEN, ACE), createPlayer(ACE, ACE, TEN), Stat.WIN),
                Arguments.of(createDealer(TEN, FIVE, TEN), createPlayer(TEN, TEN, FIVE), Stat.WIN),
                Arguments.of(createDealer(TEN, FIVE, TEN), createPlayer(ACE, ACE), Stat.LOSE),
            )
        }

        private fun createDealer(vararg denominations: Denomination): Dealer {
            return Dealer().apply {
                addCards(createCards(*denominations))
            }
        }

        private fun createPlayer(vararg denominations: Denomination): Player {
            return Player("dummy").apply {
                addCards(createCards(*denominations))
            }
        }

        private fun createCards(vararg denominations: Denomination): List<Card> {
            return denominations.map { Card(DIAMOND, it) }
        }
    }
}
