package blackJack

import card.CardPack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPackTest {
    @Test
    fun `카드팩은 52개의 카드로 구성되어있다`() {
        val cardPack = CardPack.getCradPack()

        val cardNumberSize = cardPack.size

        assertThat(cardNumberSize).isEqualTo(52)
    }

    @Test
    fun `카트팩은 중복된 카드가 없다`() {
        val cardPack = CardPack.getCradPack()
        val cardPackSize = cardPack.size

        val distinctCardPack =  cardPack.distinct()
        val distinctCardPackSize = distinctCardPack.size

        assertThat(cardPackSize).isEqualTo(distinctCardPackSize)
    }
}
