package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음에 카드 2장을 가지고 있다`() {
        val player = Player()
        player.cards.size shouldBe 2
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 합을 구한다`() {
        val player = Player()
        val cards = player.cards
        val expectedSum = PointCalculator.sum(cards)

        player.sum shouldBe expectedSum
    }

    @Test
    fun `가지고 있는 카드의 합이 21이 되지 않으면 카드를 계속 뽑을 수 있다`() {
        val round = Round()
        val player = Player()

        while (player.sum < 21) {
            player.canGetCard() shouldBe true
            player.addCard(round.getCard())
        }

        player.canGetCard() shouldBe false
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(name = "홍길동")
        player.name shouldBe "홍길동"
    }

    @Test
    fun `플레이어의 승패가 기록된다`() {
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.ACE), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(cards = dealerCard)

        val playerCards =
            mutableListOf(Card.from(CardType.SPADE, CardValue.TEN), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(_cards = playerCards)

        val winner = Rule.decisionWinner(dealer, player)

        winner shouldBe dealer

        player.win shouldBe 0
        player.lose shouldBe 1

        dealer.win shouldBe 1
        dealer.lose shouldBe 0
    }

    @Test
    fun `무승부일 때는 승패가 기록되지 않는다`() {
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.ACE), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(cards = dealerCard)

        val playerCards =
            mutableListOf(Card.from(CardType.HEART, CardValue.ACE), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(_cards = playerCards)

        val winner = Rule.decisionWinner(dealer, player)

        winner shouldBe null

        player.win shouldBe 0
        player.lose shouldBe 0

        dealer.win shouldBe 0
        dealer.lose shouldBe 0
    }
}
