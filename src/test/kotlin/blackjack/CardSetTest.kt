package blackjack

import blackjack.domain.Card
import blackjack.domain.CardSet
import blackjack.domain.CardType
import blackjack.domain.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardSetTest {
    @Test
    fun `카드 생성 테스트`() {
        val cardSet = CardSet.create()
        var count = 0
        CardType.values().forEach { cardType ->
            CardValue.values().forEach {
                assertThat(cardSet[count++] == Card(cardType, it))
            }
        }
    }
}
