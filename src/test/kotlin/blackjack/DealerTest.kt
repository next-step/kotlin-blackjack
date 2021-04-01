package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.LinkedList

class DealerTest {
    private val dealer = Dealer()

    @Test
    fun `딜러의 최종 수익을 올바르게 계산한다`() {
        val price1 = 3000
        val price2 = 4000
        val player1 = Player("kasha", price1)
        player1.cardDeck.add(Card(CardType.SPADE, CardNumber.TWO))
        player1.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        val player2 = Player("nextstep", price2)
        player2.cardDeck.add(Card(CardType.SPADE, CardNumber.SEVEN))
        player2.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        val dealer = Dealer()
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.FIVE))
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.TEN))
        val users = Users(listOf(dealer, player1, player2))

        assertThat(dealer.getEvaluate(users)).isEqualTo(price1 - price2)
    }

    @Test
    fun `Dealer의 이름은 딜러로 고정된다`() {
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러는 점수가 16점 이하면 반드시 추가로 카드를 뽑는다`() {
        dealer.firstDeal(LowCardExtractor())
        assertThat(dealer.cardDeck.cards.size).isEqualTo(3)
    }

    class LowCardExtractor : CardExtractor(LinkedList(allCards.shuffled())) {

        override fun addAll() {
            cards.addAll(allCards.shuffled())
        }

        companion object {
            private val allCards: List<Card> = CardType.values().flatMap { cardType ->
                CardNumber.values().filter { it.score <= 8 }.map { cardNumber ->
                    Card(cardType, cardNumber)
                }
            }
        }
    }
}
