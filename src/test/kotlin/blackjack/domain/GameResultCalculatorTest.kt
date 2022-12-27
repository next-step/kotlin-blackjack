package blackjack.domain

import blackjack.domain.Finished.Blackjack
import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import blackjack.model.GameResult
import blackjack.model.PlayerGameResult
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameResultCalculatorTest {

    lateinit var resultCalculator: ResultCalculator

    @BeforeEach
    fun setUp() {
        resultCalculator = GameResultCalculator()
    }

    @Test
    fun `딜러와 플레이어가 블랙잭을 완성하였을 시 무승부(push)`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(state = Blackjack()))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(state = Blackjack())) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.push).isEqualTo(playerResult.count { it.gameResult == GameResult.PUSH })
        Assertions.assertThat(dealerResult.win).isEqualTo(0)
        Assertions.assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 Hit 상태이면 딜러 승`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_hit = true)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
        Assertions.assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 Bust 상태이면 딜러 승`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_bust = true)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
        Assertions.assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러 Hit 상태, 플레이어가 블랙잭 완성하였을 시 플레이어 승`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_hit = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_blackjack = true)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.lose).isEqualTo(playerResult.count { it.gameResult == GameResult.WIN })
        Assertions.assertThat(dealerResult.win).isEqualTo(0)
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
    }

    @Test
    fun `딜러 Bust 상태, 플레이어가 블랙잭 완성하였을 시 플레이어 승`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_blackjack = true)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.lose).isEqualTo(playerResult.count { it.gameResult == GameResult.WIN })
        Assertions.assertThat(dealerResult.win).isEqualTo(0)
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
    }

    @Test
    fun `딜러 Hit 상태, 플레이어 Bust 상태 시 딜러 승`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_hit = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_bust = true)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
        Assertions.assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Bust 시 딜러 승`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_bust = true)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        Assertions.assertThat(dealerResult.lose).isEqualTo(0)
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Hit 시 플레이어 승`() {
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_hit = true)) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.lose).isEqualTo(playerResult.count { it.gameResult == GameResult.WIN })
        Assertions.assertThat(dealerResult.win).isEqualTo(0)
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 동일하면 무승부(push)`() {
        val cards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = cards.sum()))
        val fakePlayers = Players(List(2) { FakePlayer(play = FakeGamePlay(_stay = true, score = cards.sum())) })
        val result = resultCalculator.calculate(fakeDealer, fakePlayers)
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        Assertions.assertThat(dealerResult.push).isEqualTo(playerResult.count { it.gameResult == GameResult.PUSH })
        Assertions.assertThat(dealerResult.win).isEqualTo(0)
        Assertions.assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 딜러가 크면 딜러 승`() {
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

        Assertions.assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
        Assertions.assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 플레이어가 크면 플레이어 승`() {
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

        Assertions.assertThat(dealerResult.lose).isEqualTo(playerResult.count { it.gameResult == GameResult.WIN })
        Assertions.assertThat(dealerResult.push).isEqualTo(0)
        Assertions.assertThat(dealerResult.win).isEqualTo(0)
    }
}
