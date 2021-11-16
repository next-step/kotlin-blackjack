package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PlayerDeckTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 5, 13])
    fun `플레이어 덱에 카드를 추가할 수 있다`(cardCount: Int) {
        val playerDeck = PlayerDeck()
        val cards = (1..cardCount).map { Card(CardSymbol.CLOVER, CardNumber(it)) }

        playerDeck.addCards(cards)

        assertThat(playerDeck.cards.size).isEqualTo(cardCount)
    }

    @ParameterizedTest
    @ValueSource(strings = ["2,3", "4,5,6,7,8"])
    fun `플레이어 덱의 점수를 확인할 수 있다`(input: String) {
        val cardNumbers = input.split(",").map { it.toInt() }
        val playerDeck = PlayerDeck()
        val cards = cardNumbers.map { Card(CardSymbol.CLOVER, CardNumber(it)) }

        playerDeck.addCards(cards)

        assertThat(playerDeck.getTotalScore()).isEqualTo(cardNumbers.sum())
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 12, 13])
    fun `점수 계산 시 코트 카드는 10점으로 계산한다`(cardNumber: Int) {
        val playerDeck = PlayerDeck()
        playerDeck.addCards(listOf(Card(CardSymbol.CLOVER, CardNumber(cardNumber))))

        assertThat(playerDeck.getTotalScore()).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource("1,10|21", "1,10,10|21", "1|11", "1,1|12", delimiter = '|')
    fun `점수 계산 시 에이스 카드는 1점 또는 11점으로 계산한다`(cardNumbers: String, expectedScore: Int) {
        val playerDeck = PlayerDeck()
        playerDeck.addCards(
            cardNumbers
                .split(",")
                .map { Card(CardSymbol.CLOVER, CardNumber(it.toInt())) }
        )

        assertThat(playerDeck.getTotalScore()).isEqualTo(expectedScore)
    }
}
