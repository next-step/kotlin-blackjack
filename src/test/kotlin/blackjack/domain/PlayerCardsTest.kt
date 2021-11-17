package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerCardsTest {

    @Test
    fun `add() 메소드를 한번 호출하면 cards에 카드 한장이 들어있다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))

        assertThat(cards).isNotNull
        assertThat(cards.cards.size).isEqualTo(1)
    }

    @Test
    fun `CLUBS ACE 카드 한장을 추가한 후 getCardsString()를 호출하면 'A클로버' 를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.ACE))

        val cardString = playerCards.cards[0].number.rank + playerCards.cards[0].symbol.koreanName
        assertThat(playerCards.getCardsString()).isEqualTo(cardString)
    }

    @Test
    fun `SPADE KING 카드 한장과 DIAMOND TEN 한장을 추가한 후 getCardsString()를 호출하면 'K스페이드, 10다이아몬드' 를 리턴한다`() {

        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.TEN))

        val firstCardString = playerCards.cards[0].number.rank + playerCards.cards[0].symbol.koreanName
        val secondCardString = playerCards.cards[1].number.rank + playerCards.cards[1].symbol.koreanName

        assertThat(playerCards.getCardsString()).isEqualTo("$firstCardString, $secondCardString")
    }
}
