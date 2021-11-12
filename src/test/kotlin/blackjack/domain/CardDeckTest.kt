package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Stack

internal class CardDeckTest {

    @Test
    fun `비어있는 CardDeck에 getNextCard() 메소드를 호출하면 IllegalStateException이 발생한다`() {
        val cardDeck = CardDeck(Stack<Card>())

        assertThrows<IllegalStateException> {
            cardDeck.getNextCard()
        }
    }

    @Test
    fun `카드가 한 장 들어있는 CardDeck에 getNextCard() 메소드를 호출하면 Deck에 들어가 있는 카드를 얻을 수 있다`() {
        val cards = listOf(
            Card(CardSymbol.SPADE, CardNumber.ACE),
        )
        val cardDeck = CardDeck(Stack<Card>().also { it.addAll(cards) })

        assertThat(cardDeck.getNextCard()).isEqualTo(Card(CardSymbol.SPADE, CardNumber.ACE))
    }

    @Test
    fun `카드가 두 장 들어있는 CardDeck에 getNextCard() 메소드를 호출하면 두번째로 Deck에 들어간 카드를 얻을 수 있다`() {
        val cards = listOf(
            Card(CardSymbol.SPADE, CardNumber.ACE),
            Card(CardSymbol.CLUBS, CardNumber.KING),
        )
        val cardDeck = CardDeck(Stack<Card>().also { it.addAll(cards) })

        assertThat(cardDeck.getNextCard()).isEqualTo(Card(CardSymbol.CLUBS, CardNumber.KING))
    }

    @Test
    fun `getShuffledCards() 메소드를 호출하면 52장의 카드가 들어있는 Stack을 얻을 수 있다`() {
        val cardDeck = CardDeck(CardDeck.getShuffledCards())

        assertThat(cardDeck).isNotNull
        assertThat(cardDeck.cards.size).isEqualTo(52)
    }
}
