package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `Card 객체를 생성하고 toString() 메소드의 결과값을 테스트`() {
        val cardSymbols = CardSymbol.values().toList()
        val cardNumbers = CardNumber.values().toList()

        for (cardSymbol in cardSymbols) {
            for (cardNumber in cardNumbers) {
                val card = Card(cardSymbol, cardNumber)

                assertThat(card).isNotNull
                assertThat(card.toString()).isEqualTo(cardNumber.text + cardSymbol.koreanName)
            }
        }
    }

    @Test
    fun `CardNumber가 ACE인 Card 객체에 대해 isAceCard() 메소드를 호출하면 false를 리턴한다`() {
        val cardSymbols = CardSymbol.values().toList()
        val cardNumbers = CardNumber.values().filter { it != CardNumber.ACE }.toList()

        for (cardSymbol in cardSymbols) {
            for (cardNumber in cardNumbers) {
                val card = Card(cardSymbol, cardNumber)

                assertThat(card).isNotNull
                assertThat(card.isAceCard()).isEqualTo(false)
            }
        }
    }

    @Test
    fun `CardNumber가 ACE인 Card 객체에 대해 isAceCard() 메소드를 호출하면 true를 리턴한다`() {
        val cardSymbols = CardSymbol.values().toList()
        val cardNumbers = CardNumber.values().filter { it == CardNumber.ACE }.toList()

        for (cardSymbol in cardSymbols) {
            for (cardNumber in cardNumbers) {
                val card = Card(cardSymbol, cardNumber)

                assertThat(card).isNotNull
                assertThat(card.isAceCard()).isEqualTo(true)
            }
        }
    }
}
