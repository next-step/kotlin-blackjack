package blackjack.domain

import blackjack.InputView
import blackjack.ResultView
import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.model.Bet
import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import blackjack.model.PlayerProfit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class GameTest {

    private val betMoney = Bet(1000)

    @Test
    fun `게임이 생성되면 딜러는 각 플에이어에게 2장의 카드 전달하고 본인 자신도 2장의 카드를 가진다`() {
        val firstGamePlayer = GamePlayer("고니", betMoney)
        val secondGamePlayer = GamePlayer("아귀", betMoney)

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = GameDealer()
        val game = Game(players, dealer)
        assertThat(game.players.value.all { it.play.cards.size == INITIAL_CARDS_COUNT }).isTrue
        assertThat(game.dealer.play.cards.size).isEqualTo(INITIAL_CARDS_COUNT)
    }

    @Test
    fun `각 플에이어에게 2장의 카드 전달하지 않고 게임을 시작하면 에러가 발생한다`() {
        val firstGamePlayer = FakePlayer()
        val secondGamePlayer = FakePlayer("아귀", play = FakeGamePlay(readyToPlay = false))

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = GameDealer()
        val game = Game(players, dealer)
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { game.playPlayers(InputView::shouldHit, ResultView::printPlayerCards) }
            .withMessage("모든 플레이어가 게임을 시작할 준비가 되어야 합니다.")
    }

    @Test
    fun `딜러가 2장의 카드 받지 않고 게임을 시작하면 에러가 발생한다`() {
        val firstGamePlayer = FakePlayer()
        val secondGamePlayer = FakePlayer()

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = FakeDealer(play = FakeGamePlay(readyToPlay = false))
        val game = Game(players, dealer)
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { game.playDealer { } }
            .withMessage("딜러가 게임을 시작할 준비가 되어야 합니다.")
    }

    @Test
    fun `딜러와 플레이어가 블랙잭을 완성하였을 시 플레이어 수익금이 없다(무)`() {
        val fakeDealer = GameDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers =
            Players(List(2) { count ->
                GamePlayer(
                    count.toString(),
                    bet = betMoney,
                    play = FakeGamePlay(_blackjack = true)
                )
            })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        assertThat(dealerResult.profit).isEqualTo(0.0)
        assertThat(playerResult.sumOf { it.profit }).isEqualTo(0.0)
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 stay 상태이면 딜러는 플레이어 배당금을 가져온다`() {
        val profit = betMoney.value.toDouble()
        val fakeDealer = GameDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers = Players(List(2) { conut ->
            GamePlayer(
                name = conut.toString(),
                bet = betMoney,
                play = FakeGamePlay(_stay = true)
            )
        })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * profit))
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 Bust 상태 딜러는 플레이어 배당금을 가져온다`() {
        val profit = betMoney.value.toDouble()
        val fakeDealer = GameDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers =
            Players(List(2) { GamePlayer(name = it.toString(), play = FakeGamePlay(_bust = true), bet = betMoney) })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * profit))
    }

    @Test
    fun `딜러 Stay 상태, 플레이어가 블랙잭 완성하였을 시 플레이어는 배당금의 1점5배 받는다`() {
        val profit = betMoney.value * 1.5
        val fakeDealer = GameDealer(play = FakeGamePlay(_stay = true))
        val fakePlayers = Players(List(2) {
            GamePlayer(
                name = it.toString(),
                play = FakeGamePlay(_blackjack = true),
                bet = betMoney
            )
        })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * profit)
    }

    @Test
    fun `딜러 Bust 상태, 플레이어가 블랙잭 완성하였을 시 플레이어는 배당금의 1점5배를 받는다`() {
        val profit = betMoney.value * 1.5
        val fakeDealer = GameDealer(play = FakeGamePlay(_bust = true))
        val fakePlayers = Players(List(2) {
            GamePlayer(
                name = it.toString(),
                play = FakeGamePlay(_blackjack = true),
                bet = betMoney
            )
        })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * profit)
    }

    @Test
    fun `딜러 Stay 상태, 플레이어 Bust 상태 시 딜러는 플레이어의 배당금을 가져온다`() {
        val profit = betMoney.value.toDouble()
        val fakeDealer = GameDealer(play = FakeGamePlay(_stay = true, score = 19))
        val fakePlayers =
            Players(List(2) {
                GamePlayer(
                    name = it.toString(),
                    play = FakeGamePlay(_bust = true, score = 24),
                    bet = betMoney
                )
            })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * profit))
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Bust 시 딜러는 플레이어의 배당금을 가져온다`() {
        val profit = betMoney.value.toDouble()
        val fakeDealer = GameDealer(play = FakeGamePlay(_bust = true, score = 24))
        val fakePlayers =
            Players(List(2) {
                GamePlayer(
                    name = it.toString(),
                    play = FakeGamePlay(_bust = true, score = 24),
                    bet = betMoney
                )
            })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * profit))
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Stay 시 플레이어는 배당금만큼 수익을 얻는다`() {
        val profit = betMoney.value.toDouble()
        val fakeDealer = GameDealer(play = FakeGamePlay(_bust = true))
        val fakePlayers =
            Players(List(2) { GamePlayer(name = it.toString(), play = FakeGamePlay(_stay = true), bet = betMoney) })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * profit)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 동일하면 플레이어는 수익금이 없다`() {
        val cards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val fakeDealer = GameDealer(play = FakeGamePlay(_stay = true, score = cards.sum()))
        val fakePlayers =
            Players(List(2) {
                GamePlayer(
                    name = it.toString(),
                    play = FakeGamePlay(_stay = true, score = cards.sum()),
                    bet = betMoney
                )
            })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(0.0)
        assertThat(playerProfit).isEqualTo(0.0)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 딜러가 크면 딜러는 플레이어의 배당금을 가져온다`() {
        val profit = betMoney.value.toDouble()
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
        val fakeDealer = GameDealer(play = FakeGamePlay(_stay = true, score = dealerCards.sum()))
        val fakePlayers =
            Players(List(2) {
                GamePlayer(
                    name = it.toString(),
                    play = FakeGamePlay(_stay = true, score = playerCards.sum()),
                    bet = betMoney
                )
            })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(-(playerResult.size * profit))
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 플레이어가 크면 플레이어는 배당금남큼 수익을 얻는다`() {
        val profit = betMoney.value.toDouble()
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
        val fakeDealer = GameDealer(play = FakeGamePlay(_stay = true, score = dealerCards.sum()))
        val fakePlayers =
            Players(List(2) {
                GamePlayer(
                    name = it.toString(),
                    play = FakeGamePlay(_stay = true, score = playerCards.sum()),
                    bet = betMoney
                )
            })
        val game = Game(fakePlayers, fakeDealer)

        val result = game.profits()
        val dealerResult = result.value.filterIsInstance<PlayerProfit.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerProfit.Player>()

        val playerProfit = playerResult.sumOf { it.profit }
        assertThat(dealerResult.profit).isEqualTo(-(playerProfit))
        assertThat(playerProfit).isEqualTo(playerResult.size * profit)
    }
}
