package blackjack

import blackjack.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드덱에 있는 카드를 내놓는지 테스트`() {
        val allShuffledCard = CardDeck.makeShuffledCard()

        val served = Dealer.popOneCard()

        assertThat(allShuffledCard.contains(served)).isTrue
    }
}
