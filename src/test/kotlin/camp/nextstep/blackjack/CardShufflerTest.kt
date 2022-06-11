package camp.nextstep.blackjack

import camp.nextstep.blackjack.card.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardShufflerTest {

    @DisplayName("카드를 섞고 난 이후 카드 개수는 같다.")
    @Test
    fun shouldSameCardNumberAfterShuffled() {
        val cardDeck = CardDeck.new()
        val beforeSize = cardDeck.size

        val shuffled = CardShuffler.shuffle(cardDeck)
        val afterSize = shuffled.size

        assertThat(afterSize).isEqualTo(beforeSize)
    }

    @DisplayName("카드를 섞으면서 카드가 바뀌지 않는다.")
    @Test
    fun shouldNotChangedCardsDuringShuffle() {
        val cardDeck = CardDeck.new()
        val beforeCards = cardDeck.cards

        val shuffled = CardShuffler.shuffle(cardDeck)
        val afterCards = shuffled.cards

        assertThat(afterCards).hasSameElementsAs(beforeCards)
    }

    @DisplayName("카드를 섞으면 카드 순서가 바뀐다.")
    @Test
    fun shuffle() {
        val cardDeck = CardDeck.new()
        val beforeCards = cardDeck.cards

        val shuffled = CardShuffler.shuffle(cardDeck)
        val afterCards = shuffled.cards

        assertThat(afterCards).isNotEqualTo(beforeCards)
    }
}
