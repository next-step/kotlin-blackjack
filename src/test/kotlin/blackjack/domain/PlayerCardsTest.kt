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
/*
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

        assertThat(cards.getCardsResultPoint()).isEqualTo(21)
    }

    @Test
    fun `CardNumber 로 JACK 한장, QUEEN 한장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 20을 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        cards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))

        assertThat(cards.getCardsResultPoint()).isEqualTo(20)
    }

    @Test
    fun `CardNumber 로 JACK 한장, QUEEN 한장, ACE를 한장 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 21을 리턴한다`() {
        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        cards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))
        cards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))

        assertThat(cards.getCardsResultPoint()).isEqualTo(21)
    }

    @Test
    fun `CardNumber 로 ACE 한장, TWO 한장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 13를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.TWO))

        assertThat(playerCards.getCardsResultPoint()).isEqualTo(13)
    }

    @Test
    fun `CardNumber 로 ACE 두장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 12를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))

        assertThat(playerCards.getCardsResultPoint()).isEqualTo(12)
    }

    @Test
    fun `CardNumber 로 ACE 세장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 13를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))

        assertThat(playerCards.getCardsResultPoint()).isEqualTo(13)
    }

    @Test
    fun `CardNumber 로 ACE 네장을 가지고 있는 cards에 대해 getResult() 메소드를 호출하면 14를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.ACE))

        assertThat(playerCards.getCardsResultPoint()).isEqualTo(14)
    }

    @Test
    fun `CardNumber로 ACE 를 한장 가지고 있는 cards에 대해 hasAnyAceCard() 메소드를 호출하면 true를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))

        assertThat(playerCards.hasAnyAceCard()).isEqualTo(true)
    }

    @Test
    fun `CardNumber로 TWO 를 한장 가지고 있는 cards에 대해 hasAnyAceCard() 메소드를 호출하면 false를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.TWO))

        assertThat(playerCards.hasAnyAceCard()).isEqualTo(false)
    }

    @Test
    fun `CardNumber로 ACE 를 한장 가지고 있는 cards에 대해 getSumOfMinimumCardValues() 메소드를 호출하면 1을 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))

        assertThat(playerCards.getSumOfMinimumCardValues()).isEqualTo(1)
    }

    @Test
    fun `CardNumber로 ACE 를 네장 가지고 있는 cards에 대해 getSumOfMinimumCardValues() 메소드를 호출하면 4를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.ACE))

        assertThat(playerCards.getSumOfMinimumCardValues()).isEqualTo(4)
    }

    @Test
    fun `CardNumber로 ACE 를 한장, KING 을 한장 가지고 있는 cards에 대해 getSumOfMinimumCardValues() 메소드를 호출하면 11을 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.KING))

        assertThat(playerCards.getSumOfMinimumCardValues()).isEqualTo(11)
    }

    @Test
    fun `CardNumber로 ACE 를 한장 가지고 있는 cards에 대해 getSumOfMaximumCardValues() 메소드를 호출하면 11을 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))

        assertThat(playerCards.getSumOfMaximumCardValues()).isEqualTo(11)
    }

    @Test
    fun `CardNumber로 ACE 를 한장, KING 을 한장 가지고 있는 cards에 대해 getSumOfMaximumCardValues() 메소드를 호출하면 21을 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.KING))

        assertThat(playerCards.getSumOfMaximumCardValues()).isEqualTo(21)
    }

    @Test
    fun `CardNumber로 JACK 을 한장, QUEEN 을 한장 가지고 있는 cards에 대해 getSumOfMaximumCardValues() 메소드를 호출하면 20을 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))

        assertThat(playerCards.getSumOfMaximumCardValues()).isEqualTo(20)
    }

    @Test
    fun `CardNumber로 ACE 를 한장, QUEEN 을 한장 가지고 있는 cards에 대해 getResultForHavingAnyAceCard() 메소드를 호출하면 21을 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))

        assertThat(playerCards.getResultForHavingAnyAceCard()).isEqualTo(21)
    }

    @Test
    fun `CardNumber로 ACE 를 두장, KING 을 한장 가지고 있는 cards에 대해 getResultForHavingAnyAceCard() 메소드를 호출하면 12를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.KING))

        assertThat(playerCards.getResultForHavingAnyAceCard()).isEqualTo(12)
    }

    @Test
    fun `CardNumber로 ACE 를 세장, QUEEN 을 한장 가지고 있는 cards에 대해 getResultForHavingAnyAceCard() 메소드를 호출하면 13를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.JACK))

        assertThat(playerCards.getResultForHavingAnyAceCard()).isEqualTo(13)
    }

    @Test
    fun `CardNumber로 ACE 를 네장, QUEEN 을 한장 가지고 있는 cards에 대해 getResultForHavingAnyAceCard() 메소드를 호출하면 14를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.ACE))
        playerCards.add(Card(CardSymbol.CLUBS, CardNumber.JACK))

        assertThat(playerCards.getResultForHavingAnyAceCard()).isEqualTo(14)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 21])
    fun `21이하의 CardValue 합계에 대해 exceededMaximumCardValues()를 호출하면 false 를 리턴한다`(sum: Int) {
        assertThat(PlayerCards().exceededMaximumCardValues(sum)).isFalse
    }

    @ParameterizedTest
    @ValueSource(ints = [22, 23, 24, 25, 26, 27, 28, 29, 30])
    fun `22이상의 CardValue 합계에 대해 exceededMaximumCardValues()를 호출하면 true 를 리턴한다`(sum: Int) {
        assertThat(PlayerCards().exceededMaximumCardValues(sum)).isTrue
    }

    @ParameterizedTest
    @CsvSource(
        "11, 21",
        "10, 20",
        "9, 19",
        "9, 19",
    )
    fun `sumOfMaximumCardValues 가 sumOfMinimumCardValues 보다 21에 가까운 경우 isMaximumCardValuesCloserToBlackJack() 를 호출하면 true를 리턴한다`(
        sumOfMinimumCardValues: Int, sumOfMaximumCardValues: Int) {
        assertThat(PlayerCards().isMaximumCardValuesCloserToBlackJack(sumOfMinimumCardValues, sumOfMaximumCardValues)).isTrue
    }*/
}
