package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `딜러가 21보다 크고 플레이어가 21보다 작으면 승리한다`() {
        // given
        val gamers = gamers(
            playerCards = listOf(
                Card(Denomination.EIGHT, Suit.SPADE),
                Card(Denomination.EIGHT, Suit.HEART)
            ),
            dealerCards = listOf(
                Card(Denomination.TEN, Suit.HEART),
                Card(Denomination.FIVE, Suit.HEART),
                Card(Denomination.SEVEN, Suit.HEART)
            )
        )

        // when
        val results = gamers.results()
        val actual = results.count { it is GameResult.Win }

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `플레이어가 딜러보다 21점에 가까우면 승리한다`() {
        // given
        val gamers = gamers(
            playerCards = listOf(
                Card(Denomination.TEN, Suit.SPADE),
                Card(Denomination.TEN, Suit.HEART)
            ),
            dealerCards = listOf(
                Card(Denomination.TEN, Suit.HEART),
                Card(Denomination.FIVE, Suit.HEART),
                Card(Denomination.FOUR, Suit.CLOVER)
            )
        )

        // when
        val results = gamers.results()
        val actual = results.count { it is GameResult.Win }

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `플레이어가 딜러보다 점수가 낮으면 패배한다`() {
        // given
        val gamers = gamers(
            playerCards = listOf(
                Card(Denomination.TEN, Suit.SPADE),
                Card(Denomination.FIVE, Suit.HEART)
            ),
            dealerCards = listOf(
                Card(Denomination.TEN, Suit.HEART),
                Card(Denomination.FIVE, Suit.HEART),
                Card(Denomination.FOUR, Suit.CLOVER)
            )
        )

        // when
        val results = gamers.results()
        val actual = results.count { it is GameResult.Lose }

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `플레이어가 21점보다 높으면 패배한다`() {
        // given
        val gamers = gamers(
            playerCards = listOf(
                Card(Denomination.TEN, Suit.SPADE),
                Card(Denomination.TEN, Suit.HEART),
                Card(Denomination.TEN, Suit.CLOVER),
            ),
            dealerCards = listOf(Card(Denomination.TEN, Suit.HEART))
        )

        // when
        val results = gamers.results()
        val actual = results.count { it is GameResult.Lose }

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `플레이어와 딜러 점수가 같으면 무승부다`() {
        // given
        val gamers = gamers(
            playerCards = listOf(
                Card(Denomination.TEN, Suit.SPADE),
                Card(Denomination.FIVE, Suit.HEART)
            ),
            dealerCards = listOf(
                Card(Denomination.TEN, Suit.CLOVER),
                Card(Denomination.FIVE, Suit.DIAMOND),
            )
        )

        // when
        val results = gamers.results()
        val actual = results.count { it is GameResult.Push }

        // then
        assertThat(actual).isEqualTo(1)
    }

    private fun gamers(
        playerCards: List<Card>,
        dealerCards: List<Card>
    ): Gamers {
        val players = listOf(
            Player.from(
                name = Name.valueOf("laco"),
                cards = Cards(playerCards)
            )
        )
        val dealer = Dealer.from(
            name = Name.valueOf("딜러"),
            cards = Cards(dealerCards)
        )
        return Gamers.from(dealer, players)
    }
}
