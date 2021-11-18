package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        dealer = Dealer.from(name = Name.valueOf("딜러"), Cards.empty())
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
    fun `딜러는 카드들의 합이 16이하까지 카드를 계속해서 받는다`() {
        val cards = listOf(
            Card(Denomination.TEN, Suit.HEART),
            Card(Denomination.TEN, Suit.SPADE),
            Card(Denomination.TEN, Suit.DIAMOND),
            Card(Denomination.TEN, Suit.CLOVER),
        )

        // when
        var count = 0
        val actual = dealer
            .receiveWhile(next = { cards.getOrNull(count++) })
            .cards

        // then
        val expected = Cards(
            listOf(
                Card(Denomination.TEN, Suit.HEART),
                Card(Denomination.TEN, Suit.SPADE)
            )
        )
        assertThat(actual).isEqualTo(expected)
    }
}
