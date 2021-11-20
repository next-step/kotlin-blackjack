package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `Ace 가 2장일때 Card 의 합은 12`() {
        val player = Player(Participant("one"), 0.0).player

        player.addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        player.addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))

        val actual = player.getCardSum()

        assertEquals(12, actual)
    }

    @Test
    fun `Ace, King 일때 Card 의 합은 21`() {
        val player = Player(Participant("one"), 0.0).player

        player.addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        player.addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))

        val actual = player.getCardSum()

        assertEquals(21, actual)
    }

    @Test
    fun `9, ACE, ACE 일때 Card 의 합은 21`() {
        val player = Player(Participant("one"), 0.0).player

        player.addCard(Card(CardPattern.HEART, CardDenomination.NINE))
        player.addCard(Card(CardPattern.HEART, CardDenomination.ACE))
        player.addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))

        val actual = player.getCardSum()

        assertEquals(21, actual)
    }

    @Test
    fun `카드를 추가하면 카드가 1장 추가된다`() {
        val player = Player(Participant("one"), 0.0).player

        player.addCard(Card(CardPattern.HEART, CardDenomination.NINE))

        val actual = player.cards

        assertEquals(1, actual.size)
    }

    @Test
    fun `가진 카드의합이 21보다 작을때 카드를 받을 수 있다`() {
        val player = Player(Participant("one"), 0.0)

        val actual = player.isCardReceiveAble()

        assertTrue(actual)
    }

    @Test
    fun `가진 카드의합이 21이상일 때 카드를 받을 수 없다`() {
        val player = Player(Participant("one"), 0.0).apply {
            player.addCard(Card(CardPattern.DIAMOND, CardDenomination.TEN))
            player.addCard(Card(CardPattern.HEART, CardDenomination.TEN))
            player.addCard(Card(CardPattern.CLOVER, CardDenomination.TEN))
        }

        val actual = player.isCardReceiveAble()

        assertFalse(actual)
    }
}
