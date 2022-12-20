package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.model.GameResult
import blackjack.model.PlayerGameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun `게임이 생성되면 딜러는 각 플에이어에게 2장의 카드 전달하고 본인 자신도 2장의 카드를 가진다`() {
        val firstGamePlayer = GamePlayer("고니")
        val secondGamePlayer = GamePlayer("아귀")

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = GameDealer()
        val game = Game(players, dealer)
        assertThat(game.players.value.all { it.cards.size == INITIAL_CARDS_COUNT }).isTrue
        assertThat(game.dealer.cards.size).isEqualTo(INITIAL_CARDS_COUNT)
    }

    @Test
    fun `딜러와 플레이어가 블랙잭을 완성하였을 시 무승부(push)`() {
        val player = FakePlayer("고니", blackjack = true)
        val dummyPlayer = FakePlayer("팽 경장", blackjack = true)
        val dealer = FakeDealer(blackjack = true)
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.push).isEqualTo(playerResult.count { it.gameResult == GameResult.PUSH })
        assertThat(dealerResult.win).isEqualTo(0)
        assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 블랙잭 미완성하였을 시 딜러 승`() {
        val player = FakePlayer("고니", blackjack = false)
        val dummyPlayer = FakePlayer("팽 경장", blackjack = false)
        val dealer = FakeDealer(blackjack = true)
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        assertThat(dealerResult.push).isEqualTo(0)
        assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러 블랙잭 미완성, 플레이어가 블랙잭 완성하였을 시 플레이어 승`() {
        val player = FakePlayer("고니", blackjack = true)
        val dummyPlayer = FakePlayer("팽 경장", blackjack = true)
        val dealer = FakeDealer(blackjack = false)
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.lose).isEqualTo(playerResult.count { it.gameResult == GameResult.WIN })
        assertThat(dealerResult.win).isEqualTo(0)
        assertThat(dealerResult.push).isEqualTo(0)
    }

    @Test
    fun `딜러 버스트, 플레이어 버스트 상태 아닌 경우 플레이어 승`() {
        val player = FakePlayer("고니", bust = false)
        val dummyPlayer = FakePlayer("팽 경장", bust = false)
        val dealer = FakeDealer(bust = true)
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.lose).isEqualTo(playerResult.count { it.gameResult == GameResult.WIN })
        assertThat(dealerResult.win).isEqualTo(0)
        assertThat(dealerResult.push).isEqualTo(0)
    }

    @Test
    fun `플레이어 버스트 상태이면 딜러 승`() {
        val player = FakePlayer("고니", bust = true)
        val dummyPlayer = FakePlayer("팽 경장", bust = true)
        val dealer = FakeDealer()
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        assertThat(dealerResult.push).isEqualTo(0)
        assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러, 플레이어 각각 블랙잭, 버스트 상태 아니고 카드의 총합이 동일하면 무승부(push)`() {
        val player = FakePlayer("고니", sumCards = 20)
        val dummyPlayer = FakePlayer("팽 경장", sumCards = 20)
        val dealer = FakeDealer(sumCards = 20)
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.push).isEqualTo(playerResult.count { it.gameResult == GameResult.PUSH })
        assertThat(dealerResult.win).isEqualTo(0)
        assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러, 플레이어 각각 블랙잭, 버스트 상태 아니고 카드의 총합이 딜러가 크면 딜러 승`() {
        val player = FakePlayer("고니", sumCards = 20)
        val dummyPlayer = FakePlayer("팽 경장", sumCards = 20)
        val dealer = FakeDealer(sumCards = 21)
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.win).isEqualTo(playerResult.count { it.gameResult == GameResult.LOSE })
        assertThat(dealerResult.push).isEqualTo(0)
        assertThat(dealerResult.lose).isEqualTo(0)
    }

    @Test
    fun `딜러, 플레이어 각각 블랙잭, 버스트 상태 아니고 카드의 총합이 플레이어가 크면 플레이어 승`() {
        val player = FakePlayer("고니", sumCards = 21)
        val dummyPlayer = FakePlayer("팽 경장", sumCards = 21)
        val dealer = FakeDealer(sumCards = 20)
        val game = Game(Players(listOf(player, dummyPlayer)), dealer)
        val result = game.results()
        val dealerResult = result.value.filterIsInstance<PlayerGameResult.Dealer>().first()
        val playerResult = result.value.filterIsInstance<PlayerGameResult.Player>()
        assertThat(dealerResult.lose).isEqualTo(playerResult.count { it.gameResult == GameResult.WIN })
        assertThat(dealerResult.push).isEqualTo(0)
        assertThat(dealerResult.win).isEqualTo(0)
    }
}
