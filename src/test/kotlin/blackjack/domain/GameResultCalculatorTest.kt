package blackjack.domain

import blackjack.model.Bet
import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import blackjack.model.PlayerGameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameResultCalculatorTest {

    lateinit var resultCalculator: ResultCalculator
    private val betMoney = 1000

    @BeforeEach
    fun setUp() {
        resultCalculator = GameResultCalculator()
    }

    @Test
    fun `딜러와 플레이어가 블랙잭을 완성하였을 시 플레이어는 배당금을 돌려 받는다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_blackjack = true), bet = Bet(betMoney)) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        assertThat(dealerResult.profit).isEqualTo(0.0)
        assertThat(playerResult.sumOf { it.profit }).isEqualTo(playerResult.size * betMoney.toDouble())
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 stay 상태이면 딜러는 플레이어 배당금을 가져온다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_stay = true), bet = Bet(betMoney)) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * betMoney.toDouble()))
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 Bust 상태 딜러는 플레이어 배당금을 가져온다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_bust = true), bet = Bet(betMoney)) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * betMoney.toDouble()))
    }

    @Test
    fun `딜러 Stay 상태, 플레이어가 블랙잭 완성하였을 시 플레이어는 배당금의 1점5배 받는다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_blackjack = true), bet = Bet(betMoney)) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * 1.5 * betMoney.toDouble())
    }

    @Test
    fun `딜러 Bust 상태, 플레이어가 블랙잭 완성하였을 시 플레이어는 배당금의 1점5배를 받는다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_blackjack = true), bet = Bet(betMoney)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * 1.5 * betMoney.toDouble())
    }

    @Test
    fun `딜러 Stay 상태, 플레이어 Bust 상태 시 딜러는 플레이어의 배당금을 가져온다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = 19))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_bust = true, score = 24), bet = Bet(betMoney)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * betMoney.toDouble()))
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Bust 시 딜러는 플레이어의 배당금을 가져온다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true, score = 24))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_bust = true, score = 24), bet = Bet(betMoney)) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * betMoney.toDouble()))
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Stay 시 플레이어는 배당금만큼 수익을 얻는다`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_stay = true), bet = Bet(betMoney)) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * betMoney.toDouble())
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 동일하면 플레이어는 수익금이 없다`() {
        val cards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = cards.sum()))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_stay = true, score = cards.sum()), bet = Bet(betMoney)) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(0.0)
        assertThat(playerProfit).isEqualTo(0.0)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 딜러가 크면 딜러는 플레이어의 배당금을 가져온다`() {
        val dealerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.ACE, CardShape.DIAMOND),
            )
        )
        val playerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = dealerCards.sum()))
        val fakePlayers =
            Players(List(2) { FakePlayer(play = FakeGamePlay(_stay = true, score = playerCards.sum())) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * betMoney.toDouble()))
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 플레이어가 크면 플레이어는 배당금남큼 수익을 얻는다`() {
        val dealerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val playerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.ACE, CardShape.DIAMOND),
            )
        )
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = dealerCards.sum()))
        val fakePlayers =
            Players(List(2) { FakePlayer(play = FakeGamePlay(_stay = true, score = playerCards.sum())) })

        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * betMoney.toDouble())
    }
}
