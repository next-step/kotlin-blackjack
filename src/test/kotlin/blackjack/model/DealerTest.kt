package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        dealer = Dealer(Cards.empty())
    }

    @Test
    fun `딜러는 카드들을 가진다`() {
        assertThat(dealer.cards).isEqualTo(Cards.empty())
    }

    @Test
    fun `딜러는 카드를 받을 수 있다`() {
        assertThat(
            dealer
                .receive(Card(Denomination.ACE, Suit.HEART))
                .cards
        ).isEqualTo(Cards(listOf(Card(Denomination.ACE, Suit.HEART))))
    }

    @Test
    fun `딜러는 카드들의 합이 16이하면 카드를 더 받을 수 있다`() {
        assertThat(
            dealer
                .receive(Card(Denomination.TEN, Suit.HEART))
                .canReceive
        ).isTrue
    }
}
