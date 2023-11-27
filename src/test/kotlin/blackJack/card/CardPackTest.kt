package blackJack.card

import card.CardPack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPackTest {
    @Test
    fun `카드팩은 52개의 카드로 구성되어있다`() {
        val cardPack = CardPack.getCardPack()

        val cardPackSize = cardPack.cardList.size

        assertThat(cardPackSize).isEqualTo(52)
    }

    @Test
    fun `카트팩은 중복된 카드가 없다`() {
        val cardPack = CardPack.getCardPack()
        val cardPackSize = cardPack.cardList.size

        val distinctCardPack = cardPack.cardList.distinct()
        val distinctCardPackSize = distinctCardPack.size

        assertThat(cardPackSize).isEqualTo(distinctCardPackSize)
    }

    @Test
    fun `카드팩을 생성한다, 카드 반환 요청이 들어올 때, 순차적으로 카드를 반환한다`() {
        // given : 카드팩을 생성한다.
        val cardPack = CardPack.getCardPack()

        // when : 카드 반환 요청이 들어온다.
        val actual_1 = cardPack.hit()
        val actual_2 = cardPack.hit()

        // 순차적으로 반환된다.
        assertThat(actual_1).isEqualTo(cardPack.cardList[0])
        assertThat(actual_2).isEqualTo(cardPack.cardList[1])
    }
}
