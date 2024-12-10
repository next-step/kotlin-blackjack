package blackjack.infra

import blackjack.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AmountStatisticsTest {
    private lateinit var amountStatisticsBuilder: AmountStatisticsBuilder
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        amountStatisticsBuilder = AmountStatisticsBuilder()
        dealer = Dealer()
    }

    @Test
    fun `플레이어의 첫 두 장 카드 합이 blackjack이면 베팅금액 evenMoney 판정`() {
        val player = createPlayerWithBlackjack("player", 1000)
        amountStatisticsBuilder.onWin(player)

        val actual = amountStatisticsBuilder.build()

        assertAll(
            { assertThat(actual.playerProfits["player"]).isEqualTo(Money(1_500)) },
            { assertThat(actual.dealerProfit).isEqualTo(Money(-500)) },
        )
    }

    @Test
    fun `딜러만 blackjack 이면 딜러가 이긴다`() {
        dealer.receive(Deck(listOf(Card(CardRank.ACE, Suit.SPADE), Card(CardRank.KING, Suit.SPADE))))
        val players = listOf(createPlayerWithNonBlackjack("player", 1000))

        val actual = dealer.calculateStatistics(players, amountStatisticsBuilder)

        assertAll(
            { assertThat(actual.playerProfits["player"]).isEqualTo(Money(-1000)) },
            { assertThat(actual.dealerProfit).isEqualTo(Money(1000)) },
        )
    }

    @Test
    fun `딜러가 버스트면 플레이어가 버스트라도 플레이어가 이긴다`() {
        dealer.receive(Deck(listOf(Card(CardRank.KING, Suit.SPADE), Card(CardRank.KING, Suit.SPADE), Card(CardRank.KING, Suit.SPADE))))
        val players = listOf(createPlayerWithBust("player", 1000))

        val actual = dealer.calculateStatistics(players, amountStatisticsBuilder)

        assertAll(
            { assertThat(actual.playerProfits["player"]).isEqualTo(Money(1000)) },
            { assertThat(actual.dealerProfit).isEqualTo(Money(0)) },
        )
    }

    @Test
    fun `딜러와 동시에 블랙잭이면 베팅 금액을 반환받는다`() {
        dealer.receive(Deck(listOf(Card(CardRank.ACE, Suit.SPADE), Card(CardRank.KING, Suit.SPADE))))
        val players = listOf(createPlayerWithBlackjack("player", 1000))

        val actual = dealer.calculateStatistics(players, amountStatisticsBuilder)

        assertAll(
            { assertThat(actual.playerProfits["player"]).isEqualTo(Money(1000)) },
            { assertThat(actual.dealerProfit).isEqualTo(Money(0)) },
        )
    }

    @Test
    fun `플레이어가 bust 상태면 베팅 금액 전액을 잃는다`() {
        dealer.receive(Deck(listOf(Card(CardRank.KING, Suit.SPADE), Card(CardRank.KING, Suit.SPADE))))
        val players = listOf(createPlayerWithBust("player", 1000))

        val actual = dealer.calculateStatistics(players, amountStatisticsBuilder)

        assertAll(
            { assertThat(actual.playerProfits["player"]).isEqualTo(Money(-1000)) },
            { assertThat(actual.dealerProfit).isEqualTo(Money(1000)) },
        )
    }

    private fun createPlayerWithBlackjack(
        name: String,
        betAmount: Int,
    ): Player {
        val player = Player.from(name)
        player.betting(Money(betAmount))
        player.receive(
            Deck(
                listOf(
                    Card(CardRank.ACE, Suit.SPADE),
                    Card(CardRank.KING, Suit.SPADE),
                ),
            ),
        )
        return player
    }

    private fun createPlayerWithNonBlackjack(
        name: String,
        betAmount: Int,
    ): Player {
        val player = Player.from(name)
        player.betting(Money(betAmount))
        player.receive(
            Deck(
                listOf(
                    Card(CardRank.ACE, Suit.SPADE),
                    Card(CardRank.TWO, Suit.SPADE),
                ),
            ),
        )
        return player
    }

    private fun createPlayerWithBust(
        name: String,
        betAmount: Int,
    ): Player {
        val player = Player.from(name)
        player.betting(Money(betAmount))
        player.receive(
            Deck(
                listOf(
                    Card(CardRank.ACE, Suit.SPADE),
                    Card(CardRank.QUEEN, Suit.SPADE),
                    Card(CardRank.QUEEN, Suit.DIAMOND),
                ),
            ),
        )
        return player
    }
}
