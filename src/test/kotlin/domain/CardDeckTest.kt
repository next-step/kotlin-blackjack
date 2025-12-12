package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    @DisplayName("카드덱 초기화 시 중복없는 52장 카드를 뽑을수있고 그 이후는 오류가 발생한다.")
    fun cardDeckTest() {
        // given
        val cardDeck = CardDeck()
        val drawnCards = mutableSetOf<Card>()

        // when
        repeat(52) {
            drawnCards.add(cardDeck.drawCard())
        }

        // then
        assertThat(drawnCards.size).isEqualTo(52)
        assertThrows<IllegalStateException> {
            cardDeck.drawCard()
        }
    }
}
