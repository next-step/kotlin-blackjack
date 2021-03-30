package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameTest {
    private val HIT = 1
    private val BLACK_JACK = 3

    @Test
    fun `블랙잭은 처음에 카드를 2장을 가져가면 현재 상태를 리턴한다`() {
        val game = Game(Card(CardSuite.HEART, CardNumber.ACE), Card(CardSuite.SPADE, CardNumber.TWO))
        val state = game.state

        assertThat(state).isEqualTo(HIT)
    }

    @ParameterizedTest
    @CsvSource(
        "HEART,QUEEN,SPADE,KING",
        "HEART,ACE,SPADE,ACE",
        "HEART,TWO,SPADE,TEN",
        "HEART,FOUR,SPADE,FIVE",
        "HEART,SEVEN,SPADE,ACE",
        "HEART,NINE,SPADE,NINE"
    )
    fun `카드의 숫자의 합이 21미만이면 Hit 이다`(firstCardSuite: CardSuite, firstCardNumber: CardNumber, secondCardSuite: CardSuite, secondCardNumber: CardNumber) {
        val game = Game(Card(firstCardSuite, firstCardNumber), Card(secondCardSuite, secondCardNumber))
        val state = game.state

        assertThat(state).isEqualTo(HIT)
    }

    @Test
    fun `처음 받은 2개의 두 카드의 숫자 합이 21이면 Blackjack 이다`() {
        val game = Game(Card(CardSuite.HEART, CardNumber.ACE), Card(CardSuite.SPADE, CardNumber.JACK))
        val state = game.state

        assertThat(state).isEqualTo(BLACK_JACK)
    }
}
