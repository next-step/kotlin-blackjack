package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class PlayerTest {
    @Test
    fun `카드를 한장 더 받게다고 하면 draw`() {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.NINE, Suit.CLOVER))
        val deck = Deck(cards)
        val player = Player("van", deck)

        assertThat(player.status is Hit).isTrue()
        player.action(true, deck)
        assertThat(player.status is Bust).isTrue()
    }

    @Test
    fun `카드를 한장 더 받게다고 하면 draw2`() {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.TWO, Suit.CLOVER))
        cards.push(Card(Denomination.NINE, Suit.CLOVER))
        val deck = Deck(cards)
        val player = Player("van", deck)

        assertThat(player.status is Hit).isTrue()
        player.action(true, deck)
        assertThat(player.status is Hit).isTrue()
    }

    @Test
    fun `카드를 안받으면 stay`() {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.NINE, Suit.CLOVER))
        val deck = Deck(cards)
        val player = Player("van", deck)

        assertThat(player.status is Hit).isTrue()
        player.action(false, deck)
        assertThat(player.status is Stay).isTrue()

    }
}
