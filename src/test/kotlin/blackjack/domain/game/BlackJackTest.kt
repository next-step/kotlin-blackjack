package blackjack.domain.game

import blackjack.domain.card.TestCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackJackTest {
    @Test
    internal fun `게임이 시작되면 플레이어에게 두장의 카드가 주어진다`() {
        val player = Player("pobi")
        player.cards.values.size shouldBe 0
        val blackJack = BlackJack(listOf(player))
        blackJack.distributeInitialCard()
        player.cards.values.size shouldBe 2
        blackJack.dealer.cards.values.size shouldBe 2
    }

    @Test
    internal fun `플레이어가 21점이 초과하지않으면 턴은 계속된다`() {
        val cards = TestCards.getTwentyPointCards()
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))

        player.score() shouldBe 21
        game.isEnd() shouldBe false
    }

    @Test
    internal fun `플레이어가 21점을 초과하면 더이상 카드를 뽑을 수 없어 턴이 종료된다`() {
        val cards = TestCards.getBurstCards()
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))

        player.score() shouldBe 22
        game.isEnd() shouldBe true
    }

    @Test
    internal fun `플레이어가 y를 대답하면 카드의 갯수가 늘어난다`() {
        val cards = TestCards.getSixteenPointCards()
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))
        player.cards.values.size shouldBe 2
        game.playGameTurn(true)
        player.cards.values.size shouldBe 3
    }

    @Test
    internal fun `플레이어가 n를 대답하면 턴이 종료된다`() {
        val cards = TestCards.getSixteenPointCards()
        val player = Player("pobi", cards)
        val game = BlackJack(listOf(player))
        game.playGameTurn(false)
        game.isEnd() shouldBe true
    }

    @Test
    internal fun `이전 플레이어가 끝나면 게임차례는 넘어간다`() {
        val cards = TestCards.getSixteenPointCards()
        val player1 = Player("pobi", cards)
        val player2 = Player("ryan", cards)
        val game = BlackJack(listOf(player1, player2))

        game.getNowPlayer() shouldBe player1
        game.playGameTurn(false)
        game.getNowPlayer() shouldBe player2
        game.isEnd() shouldBe false
    }

    @Test
    internal fun `딜러는 점수 16점 이하면 카드 한장을 더 받는다`() {
        val cards = TestCards.getSixteenPointCards()
        val player1 = Player("pobi", cards)
        val dealer = Dealer(cards)
        val game = BlackJack(listOf(player1), dealer)

        dealer.cards.score() shouldBe 16
        dealer.cards.values.size shouldBe 2
        game.shouldDealerDrawCard() shouldBe true
        game.distributeCardForDealer()
        dealer.cards.values.size shouldBe 3
    }

    @Test
    internal fun `딜러는 점수 16점 초과면 카드 한장을 더 받지않는다`() {
        val cards = TestCards.getSeventeenPointCards()
        val player1 = Player("pobi", cards)
        val dealer = Dealer(cards)
        val game = BlackJack(listOf(player1), dealer)

        dealer.cards.score() shouldBe 17
        dealer.cards.values.size shouldBe 2
        game.shouldDealerDrawCard() shouldBe false
        dealer.cards.values.size shouldBe 2
    }
}
