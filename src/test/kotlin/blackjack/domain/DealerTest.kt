package blackjack.domain

import blackjack.service.DeckCardsBuilder
import blackjack.service.PlayerCardAdditionDecider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Stack

internal class DealerTest {
    @Test
    fun `deliverBasicCards() 메소드로 Players들에게 카드를 나눠주면 각 Player들은 카드를 2장씩 가지게 된다`() {
        val players = Players(
            listOf(
                Player("aaa", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider(), ResultCalculator())),
                Player("bbb", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider(), ResultCalculator()))
            )
        )

        val deckCardsBuilder = object : DeckCardsBuilder {
            override fun build(): Stack<Card> {
                val cards = Stack<Card>()
                cards.push(Card(CardSymbol.SPADE, CardNumber.ACE))
                cards.push(Card(CardSymbol.HEART, CardNumber.KING))
                cards.push(Card(CardSymbol.DIAMOND, CardNumber.JACK))
                cards.push(Card(CardSymbol.CLUBS, CardNumber.QUEEN))
                return cards
            }
        }

        val dealerCardDeck = DealerCardDeck(deckCardsBuilder.build())
        val dealer = Dealer(dealerCardDeck)

        dealer.deliverBasicCards(players)

        players.items.forEach {
            assertThat(it.cardsHandler.getCards().size).isEqualTo(2)
        }
    }

    @Test
    fun `deliverBasicCards() 메소드로 Player에게 카드를 나눠주면 카드를 2장 가지게 된다`() {
        val player = Player("aaa", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider(), ResultCalculator()))

        val deckCardsBuilder = object : DeckCardsBuilder {
            override fun build(): Stack<Card> {
                val cards = Stack<Card>()
                cards.push(Card(CardSymbol.HEART, CardNumber.KING))
                cards.push(Card(CardSymbol.DIAMOND, CardNumber.JACK))
                cards.push(Card(CardSymbol.CLUBS, CardNumber.QUEEN))
                return cards
            }
        }

        val dealerCardDeck = DealerCardDeck(deckCardsBuilder.build())
        val dealer = Dealer(dealerCardDeck)

        dealer.deliverBasicCards(player)

        assertThat(player.cardsHandler.getCards().size).isEqualTo(2)
    }

    @Test
    fun `deliverCard() 메소드로 Players들에게 카드를 나눠주면 각 Player들은 카드를 한장씩 더 가지게 된다`() {
        val players = Players(
            listOf(
                Player("aaa", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider(), ResultCalculator())),
                Player("bbb", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider(), ResultCalculator()))
            )
        )

        val deckCardsBuilder = object : DeckCardsBuilder {
            override fun build(): Stack<Card> {
                val cards = Stack<Card>()
                cards.push(Card(CardSymbol.HEART, CardNumber.KING))
                cards.push(Card(CardSymbol.DIAMOND, CardNumber.JACK))
                cards.push(Card(CardSymbol.CLUBS, CardNumber.QUEEN))
                cards.push(Card(CardSymbol.SPADE, CardNumber.ACE))
                cards.push(Card(CardSymbol.DIAMOND, CardNumber.TWO))
                cards.push(Card(CardSymbol.HEART, CardNumber.SEVEN))
                return cards
            }
        }

        val dealer = Dealer(DealerCardDeck(deckCardsBuilder.build()))

        dealer.deliverBasicCards(players)

        players.items.forEach {
            dealer.deliverCard(it)
            assertThat(it.cardsHandler.getCards().size).isEqualTo(3)
        }
    }
}
