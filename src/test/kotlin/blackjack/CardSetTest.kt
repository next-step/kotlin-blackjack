package blackjack

import blackjack.domain.Card
import blackjack.domain.CardSet
import blackjack.domain.CardType
import blackjack.domain.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardSetTest {
    private val cardSet = CardSet.create()

    @Test
    fun `카드 생성 테스트`() {
        var count = 0
        CardType.values().forEach { cardType ->
            CardValue.values().forEach {
                assertThat(cardSet[count++] == Card(cardType, it))
            }
        }
    }

    @Test
    fun `카드 뽑고 중복 없애기 테스트`() {
        val (pickedCard, pickCardRemoveCardSet) = CardSet.pickCard(cardSet)
        assertThat(pickCardRemoveCardSet.contains(pickedCard)).isEqualTo(false)
        assertThat(pickCardRemoveCardSet.size).isEqualTo(cardSet.size - 1)
    }
}
