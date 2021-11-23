package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        dealer = Dealer.ready(name = Name.valueOf("딜러"))
    }

    @Test
    fun `딜러는 카드들을 가진다`() {
        assertThat(dealer.cards).isEqualTo(Cards.empty())
    }

    @Test
    fun `딜러는 카드를 받을 수 있다`() {
        assertThat(
            dealer
                .draw(Card(Denomination.ACE, Suit.HEART))
                .cards
        ).isEqualTo(Cards(listOf(Card(Denomination.ACE, Suit.HEART))))
    }
}
