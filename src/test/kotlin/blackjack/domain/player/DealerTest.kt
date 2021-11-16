package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.card.CardsDeck
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `현재 가지고 있는 카드의 총합이 16이하면 카드를 한장 더 받는다`() {
        val dealer = Dealer()

        val card = dealer.addCardWhenLessThanStandard(CardsDeck())

        assertNotNull(card)
    }

    @Test
    fun `현재 가지고 있는 카드의 총합이 17이상이면 카드를 받지 않는다`() {
        val dealer = Dealer()

        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.ACE,
            )
        )
        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.KING,
            )
        )

        val card = dealer.addCardWhenLessThanStandard(CardsDeck())

        assertNull(card)
    }

    @Test
    fun `현재 가지고 있는 카드가 21을 초과할때 모두 패배 한다`() {
        val dealer = Dealer()

        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.TEN,
            )
        )
        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.KING,
            )
        )

        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.HEART,
                denomination = CardDenomination.KING,
            )
        )

        val players = listOf(
            Player("one"),
            Player("two")
        )

        dealer.match(players)

        assertEquals(ResultStatus.LOSE, dealer.resultStatuses[0])
        assertEquals(ResultStatus.LOSE, dealer.resultStatuses[1])
    }

    @Test
    fun `현재 가지고 있는 카드가 플레이어의 카드보다 클경우 승리한다`() {
        val dealer = Dealer()

        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.ACE,
            )
        )
        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.KING,
            )
        )

        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.HEART,
                denomination = CardDenomination.KING,
            )
        )

        val players = listOf(Player("one"))

        dealer.match(players)

        assertEquals(ResultStatus.WIN, dealer.resultStatuses[0])
    }

    @Test
    fun `현재 가지고 있는 카드가 플레이어의 카드보다 작을경우 패배한다`() {
        val dealer = Dealer()

        dealer.dealer.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.TWO,
            )
        )

        val player = Player("one")
        player.player.addCard(
            Card(
                pattern = CardPattern.DIAMOND,
                denomination = CardDenomination.THREE,
            )
        )
        val players = listOf(player)

        dealer.match(players)

        assertEquals(ResultStatus.LOSE, dealer.resultStatuses[0])
    }
}
