package blackjack.domain.model

import blackjack.domain.PointCalculator
import blackjack.domain.Rule
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음에 카드 2장을 가지고 있다`() {
        val player = Player(Game())
        player.cards.items.size shouldBe 2
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 합을 구한다`() {
        val player = Player(Game())
        val cards = player.cards
        val expectedSum = PointCalculator.sum(cards)

        player.cards.sum shouldBe expectedSum
    }

    @Test
    fun `가지고 있는 카드의 합이 21이 되지 않으면 카드를 계속 뽑을 수 있다`() {
        val game = Game()
        val player = Player(game)

        while (player.cards.sum < 21) {
            player.canGetCard() shouldBe true
            player.addCard(game.getCard())
        }

        player.canGetCard() shouldBe false
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(Game(), PlayerInfo(name = "홍길동"))
        player.info.name shouldBe "홍길동"
    }

    @Test
    fun `플레이어의 승패가 기록된다`() {
        val game = Game()
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.ACE), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(game, cards = Cards(dealerCard, game))

        val playerCards =
            mutableListOf(Card.from(CardType.SPADE, CardValue.TEN), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(game, cards = Cards(playerCards, game))

        val winner = Rule.decisionWinner(dealer, player)

        winner shouldBe dealer

        player.info.record.win shouldBe 0
        player.info.record.lose shouldBe 1

        dealer.info.record.win shouldBe 1
        dealer.info.record.lose shouldBe 0
    }

    @Test
    fun `무승부일 때는 승패가 기록되지 않는다`() {
        val game = Game()
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.ACE), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(game, cards = Cards(dealerCard, game))

        val playerCards =
            mutableListOf(Card.from(CardType.HEART, CardValue.ACE), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(game, cards = Cards(playerCards, game))

        val winner = Rule.decisionWinner(dealer, player)

        winner shouldBe null

        player.info.record.win shouldBe 0
        player.info.record.lose shouldBe 0

        dealer.info.record.win shouldBe 0
        dealer.info.record.lose shouldBe 0
    }
}
