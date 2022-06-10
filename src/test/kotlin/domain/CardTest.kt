package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `랜덤하게 카드덱을 만들어야 하고, 패턴과 숫자간의 중복이 없다`() {
        val cards = Card.issueAllCards()

        Card.CardSuit.values().forEach { p ->
            Card.CardDenomination.values().forEach { v ->
                assertThat(cards).containsOnlyOnce(Card(p, v))
            }
        }
    }
}
