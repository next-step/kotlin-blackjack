package blackjack

import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱은 52장의 카드를 가진다`() {
        val deck = Deck()
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `덱에서 한장의 카드를 뽑을 수 있다`() {
        val deck = Deck()
        val card = deck.drawCard()
        assertThat(card).isNotNull
        assertThat(deck.cards.size).isEqualTo(51)
    }

    @Test
    fun `덱에서 카드를 모두 뽑으면 예외가 발생한다`() {
        val deck = Deck()
        repeat(52) { deck.drawCard() }
        assertThatThrownBy { deck.drawCard() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("더 이상 카드가 없습니다.")
    }
}
