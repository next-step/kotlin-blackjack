package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.FIVE
import blackjack.domain.Denomination.SIX
import blackjack.domain.Suit.DIAMOND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

        assertThat(dealer.cards).hasSize(3)
    }

    @Test
    fun `딜러는 17점일 때 카드를 추가로 받을 수 있다`() {
        val dealer = Dealer()

        dealer.addCards(createCards(ACE, SIX))

        assertThrows<IllegalStateException> { dealer.addCard(Card(DIAMOND, ACE)) }
    }

    private fun createCards(vararg denominations: Denomination): List<Card> {
        val counter = denominations.groupingBy { it }.eachCount()

        return counter.map {
            Suit.values().take(it.value).map { suit -> Card(suit, it.key) }
        }.flatten()
    }
}
