package blackjack.domain

import blackjack.service.PlayerCardsHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerCardsHandlerTest {
    @Test
    fun `addCard() 메소드를 호출하면 카드를 추가할 수 있다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.TEN)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.KING)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.getCards()[0]).isEqualTo(firstCard)
        assertThat(cardsHandler.getCards()[1]).isEqualTo(secondCard)
    }

    @Test
    fun `ACE 카드 한장과 9 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 true를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.ACE)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.NINE)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(true)
    }

    @Test
    fun `JACK 카드 한장과 QUEEN 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 true를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.JACK)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.QUEEN)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(true)
    }

    @Test
    fun `ACE 카드 한장과 10 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 true를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.ACE)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.TEN)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(true)
    }

    @Test
    fun `KING 카드 한장과 10 한장, TWO 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 false를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.KING)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.TEN)
        val thirdCard = Card(CardSymbol.CLUBS, CardNumber.TWO)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)
        cardsHandler.addCard(thirdCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(false)
    }

    @Test
    fun `CLUBS ACE 카드 한장을 추가한 후 getCardsString()를 호출하면 'A클로버' 를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.CLUBS, CardNumber.ACE)
        cardsHandler.addCard(firstCard)

        val firstCardString = firstCard.number.rank + firstCard.symbol.koreanName
        assertThat(cardsHandler.getCardsString()).isEqualTo(firstCardString)
    }

    @Test
    fun `SPADE KING 카드 한장과 DIAMOND TEN 한장을 추가한 후 getCardsString()를 호출하면 'K스페이드, 10다이아몬드' 를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.KING)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.TEN)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        val firstCardString = firstCard.number.rank + firstCard.symbol.koreanName
        val secondCardString = secondCard.number.rank + secondCard.symbol.koreanName

        assertThat(cardsHandler.getCardsString()).isEqualTo("$firstCardString, $secondCardString")
    }

    @Test
    fun `ACE 카드 한장과 5카드 한장을 추가한 후 getCardsResultPoint()를 호출하면 16을 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.ACE)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.FIVE)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.getCardsResultPoint()).isEqualTo(16)
    }

    @Test
    fun `KING 카드 한장과 9카드 한장을 추가한 후 getCardsResultPoint()를 호출하면 19을 리턴한다`() {
        val cardsHandler = PlayerCardsHandler()
        val firstCard = Card(CardSymbol.SPADE, CardNumber.KING)
        val secondCard = Card(CardSymbol.DIAMOND, CardNumber.NINE)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.getCardsResultPoint()).isEqualTo(19)
    }
}
