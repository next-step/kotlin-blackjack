package blackjack.domain

import blackjack.service.PlayerCardsHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Stack

internal class DealerTest {
    @Test
    fun `deliverBasicCards() 메소드로 Players들에게 카드를 나눠주면 각 Player들은 카드를 2장씩 가지게 된다`() {
        val players = Players(listOf(Player("aaa", PlayerCardsHandler()), Player("bbb", PlayerCardsHandler())))

        val cards = listOf(
            Card(CardSymbol.SPADE, CardNumber.ACE),
            Card(CardSymbol.HEART, CardNumber.KING),
            Card(CardSymbol.DIAMOND, CardNumber.JACK),
            Card(CardSymbol.CLUBS, CardNumber.QUEEN),
        )
        val dealerCardDeck = DealerCardDeck(Stack<Card>().also { it.addAll(cards) })
        val dealer = Dealer(dealerCardDeck)

        dealer.deliverBasicCards(players)

        players.players.forEach {
            assertThat(it.cardsHandler.getCards().size).isEqualTo(2)
        }
    }

    @Test
    fun `deliverBasicCards() 메소드로 Player에게 카드를 나눠주면 카드를 2장 가지게 된다`() {
        val player = Player("aaa", PlayerCardsHandler())

        val cards = listOf(
            Card(CardSymbol.HEART, CardNumber.KING),
            Card(CardSymbol.DIAMOND, CardNumber.JACK),
            Card(CardSymbol.CLUBS, CardNumber.QUEEN),
        )
        val dealerCardDeck = DealerCardDeck(Stack<Card>().also { it.addAll(cards) })
        val dealer = Dealer(dealerCardDeck)

        dealer.deliverBasicCards(player)

        assertThat(player.cardsHandler.getCards().size).isEqualTo(2)
    }

    @Test
    fun `deliverCard() 메소드로 Players들에게 카드를 나눠주면 각 Player들은 카드를 한장씩 더 가지게 된다`() {
        val players = Players(listOf(Player("aaa", PlayerCardsHandler()), Player("bbb", PlayerCardsHandler())))
        val dealer = Dealer(DealerCardDeck(DealerCardDeck.getShuffledCards()))

        dealer.deliverBasicCards(players)

        players.players.forEach {
            dealer.deliverCard(it)
            assertThat(it.cardsHandler.getCards().size).isEqualTo(3)
        }
    }
}
