package camp.nextstep.blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardDeckTest {

    @DisplayName("카드 뭉치를 생성하면 중복되지 않는 52장(4 * 13)의 카드로 구성된다.")
    @Test
    fun cardDeck() {
        val cardDeck = CardDeck.new()

        assertThat(cardDeck.cards.toSet().size).isEqualTo(CardSuit.CARD_SUIT_NUMBERS * CardNumber.CARD_NUMBERS)
    }

    @DisplayName("카드 뭉치에서 카드 한 장을 뽑을 수 있다.")
    @Test
    fun drawCard() {
        val cardDeck = CardDeck.new()

        val drewCard = cardDeck.draw()

        assertThat(drewCard).isNotNull
    }

    @DisplayName("카드 뭉치에서 한 번 뽑힌 카드는 다시 뽑힐 수 없다.")
    @Test
    fun shouldNotDuplicateDrewCard() {
        val cardDeck = CardDeck.new()

        val expectUniqueCard = cardDeck.draw()

        while (cardDeck.isNotEmpty) {
            val drewCard = cardDeck.draw()
            assertThat(drewCard).isNotEqualTo(expectUniqueCard)
        }
    }
}
