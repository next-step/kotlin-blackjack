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
    fun `CardNumber 로 JACK 한장, ACE 한장을 가지고 있는 cards에 대해 canReceiveAdditionalCard() 메소드를 호출하면 true를 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        cards.add(Card(CardSymbol.HEART, CardNumber.ACE))

        assertThat(cards.canReceiveAdditionalCard()).isTrue
    }

    @Test
    fun `CardNumber 로 TEN 두장을 가지고 있는 cards에 대해 canReceiveAdditionalCard() 메소드를 호출하면 true를 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.TEN))
        cards.add(Card(CardSymbol.HEART, CardNumber.TEN))

        assertThat(cards.canReceiveAdditionalCard()).isTrue
    }

    @Test
    fun `CardNumber 로 ACE 한장, 9 한장을 가지고 있는 cards에 대해 canReceiveAdditionalCard() 메소드를 호출하면 true를 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        cards.add(Card(CardSymbol.HEART, CardNumber.NINE))

        assertThat(cards.canReceiveAdditionalCard()).isTrue
    }

    @Test
    fun `CardNumber 로 QUEEN 한장, KING 한장, TWO 한장을 가지고 있는 cards에 대해 canReceiveAdditionalCard() 메소드를 호출하면 false를 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.QUEEN))
        cards.add(Card(CardSymbol.HEART, CardNumber.KING))
        cards.add(Card(CardSymbol.DIAMOND, CardNumber.TWO))

        assertThat(cards.canReceiveAdditionalCard()).isFalse
    }

    @Test
    fun `CardNumber 로 ACE 한장, KING 한장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 21을 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        cards.add(Card(CardSymbol.HEART, CardNumber.KING))

        assertThat(cards.getResult()).isEqualTo(21)
    }

    @Test
    fun `CardNumber 로 JACK 한장, QUEEN 한장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 20을 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        cards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))

        assertThat(cards.getResult()).isEqualTo(20)
    }

    @Test
    fun `CardNumber 로 JACK 한장, QUEEN 한장, ACE를 한장 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 21을 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        cards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))
        cards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))

        assertThat(cards.getResult()).isEqualTo(21)
    }

    @Test
    fun `CardNumber 로 ACE 한장, TWO 한장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 13를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.TWO))

        assertThat(playerCards.getResult()).isEqualTo(13)
    }

    @Test
    fun `CardNumber 로 ACE 두장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 12를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))

        assertThat(playerCards.getResult()).isEqualTo(12)
    }

    @Test
    fun `CardNumber 로 ACE 세장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 13를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))

        assertThat(playerCards.getResult()).isEqualTo(13)
    }

    @Test
    fun `CardNumber 로 ACE 네장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 14를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.ACE))

        assertThat(playerCards.getResult()).isEqualTo(14)
    }
}
