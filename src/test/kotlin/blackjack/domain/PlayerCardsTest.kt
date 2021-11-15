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
}
