package blackjack.domain.game

import blackjack.domain.card.TestCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.State
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackJackTest {
    @Test
    internal fun `게임이 시작되면 플레이어에게 두장의 카드가 주어진다`() {

        //  given
        val player = Player("pobi")
        player.state.cards.values.size shouldBe 0

        //  when
        val blackJack = BlackJack(listOf(player))
        blackJack.distributeInitialCard()

        //  then
        player.state.cards.values.size shouldBe 2
        blackJack.dealer.state.cards.values.size shouldBe 2
    }

    @Test
    internal fun `플레이어가 21점이 초과하지않으면 턴은 계속된다`() {
        val cards = TestCards.getTwentyPointCards()
        val player = Player("pobi", State(cards))
        val game = BlackJack(listOf(player))

        player.state.score() shouldBe 21
        game.isEnd() shouldBe false
    }

    @Test
    internal fun `플레이어가 21점을 초과하면 더이상 카드를 뽑을 수 없어 턴이 종료된다`() {
        val cards = TestCards.getBurstCards()
        val player = Player("pobi", State(cards))
        val game = BlackJack(listOf(player))

        player.state.score() shouldBe 22
        game.isEnd() shouldBe true
    }

    @Test
    internal fun `플레이어가 y를 대답하면 카드의 갯수가 늘어난다`() {
        //  given
        val cards = TestCards.getSixteenPointCards()
        val player = Player("pobi", State(cards))
        val game = BlackJack(listOf(player))
        player.state.cards.values.size shouldBe 2

        //  when
        game.playGameTurn(true)

        //  then
        player.state.cards.values.size shouldBe 3
    }

    @Test
    internal fun `플레이어가 n를 대답하면 턴이 종료된다`() {
        //  given
        val cards = TestCards.getSixteenPointCards()
        val player = Player("pobi", State(cards))
        val game = BlackJack(listOf(player))

        //  when
        game.playGameTurn(false)

        //  then
        game.isEnd() shouldBe true
    }

    @Test
    internal fun `이전 플레이어가 끝나면 게임차례는 넘어간다`() {
        // given
        val cards = TestCards.getSixteenPointCards()
        val player1 = Player("pobi", State(cards))
        val player2 = Player("ryan", State(cards))
        val game = BlackJack(listOf(player1, player2))
        game.getNowPlayer() shouldBe player1

        // wen
        game.playGameTurn(false)

        // then
        game.getNowPlayer() shouldBe player2
        game.isEnd() shouldBe false
    }

    @Test
    internal fun `딜러는 점수 16점 이하면 카드 한장을 더 받는다`() {
        // given
        val cards = TestCards.getSixteenPointCards()
        val player = Player("pobi", State(cards))
        val dealer = Dealer(State(cards))
        val game = BlackJack(listOf(player), dealer)
        dealer.state.cards.values.size shouldBe 2

        // when
        game.dealer.continueDrawing shouldBe true
        game.distributeCardForDealer()

        // then
        dealer.state.cards.values.size shouldBe 3
    }

    @Test
    internal fun `딜러는 점수 16점 초과면 카드 한장을 더 받지않는다`() {
        // given
        val cards = TestCards.getSeventeenPointCards()
        val player = Player("pobi", State(cards))
        val dealer = Dealer(State(cards))
        val game = BlackJack(listOf(player), dealer)
        dealer.state.cards.values.size shouldBe 2

        // when
        game.dealer.continueDrawing shouldBe false

        // then
        dealer.state.cards.values.size shouldBe 2
    }
}
