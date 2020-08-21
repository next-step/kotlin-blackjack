package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.SuitType
import blackjack.domain.ValueType
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDeckTest {

    @DisplayName("덱에 있는 카드 52 장을 모두 사용했을 경우 확인")
    @Test
    fun checkCardDeckCountOfCards() {
        val cardDeck = CardDeck()

        repeat(SuitType.values().size * ValueType.values().size) { cardDeck.pickCard() }

        assertThatThrownBy { cardDeck.pickCard() }
            .isInstanceOf(IndexOutOfBoundsException::class.java)
    }
}
