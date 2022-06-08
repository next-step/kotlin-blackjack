package blackjack

import blackjack.domain.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드덱이 만들때마다 랜덤한 순서를 반환하는지 테스트`() {
        val randomCardDeck1 = CardDeck.makeShuffledCard()
        val randomCardDeck2 = CardDeck.makeShuffledCard()

        assertThat(randomCardDeck1).isNotEqualTo(randomCardDeck2)
    }
}
