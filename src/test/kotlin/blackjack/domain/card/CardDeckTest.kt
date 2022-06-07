package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 꾸러미(덱)의 사이즈가 52개로 초기화 되었는지 테스트`() {
        val cardDeck = CardDeck()

        assertThat(cardDeck.getCurrentCardSize()).isEqualTo(52)
    }

    @Test
    fun `카드 꾸러미(덱)에서 한개를 꺼냈을 때 사이즈가 줄어들고, 꾸러미(덱)에 꺼낸 것이 없는지 테스트`() {
        val cardDeck = CardDeck()
        val pickedCard = cardDeck.pickCard()

        assertThat(cardDeck.getCurrentCardSize()).isEqualTo(51)
        assertThat(cardDeck.isContains(pickedCard)).isFalse
    }
}
