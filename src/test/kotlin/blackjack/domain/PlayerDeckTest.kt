package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerDeckTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 5, 13])
    fun `플레이어 덱에 카드를 추가할 수 있다`(cardCount: Int) {
        val playerDeck = PlayerDeck()
        val cards = (1..cardCount).map { Card(CardSymbol.CLOVER, CardNumber(it)) }

        playerDeck.addCards(*cards.toTypedArray())

        assertThat(playerDeck.cards.size).isEqualTo(cardCount)
    }

    @ParameterizedTest
    @ValueSource(strings = ["2,3", "4,5,6,7,8"])
    fun `플레이어 덱의 점수를 확인할 수 있다`(input: String) {
        val cardNumbers = input.split(",").map { it.toInt() }
        val playerDeck = PlayerDeck()
        val cards = cardNumbers.map { Card(CardSymbol.CLOVER, CardNumber(it)) }

        playerDeck.addCards(*cards.toTypedArray())

        assertThat(playerDeck.getTotalScore()).isEqualTo(cardNumbers.sum())
    }
}
