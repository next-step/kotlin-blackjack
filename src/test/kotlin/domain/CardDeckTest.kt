package domain

import domain.CardDeck.pop
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드덱에서 주어진 개수만큼 뽑아야 한다`() {
        assertThat(pop(2).size).isEqualTo(2)
    }
}
