package blackjack.domain.model

import blackjack.domain.PointCalculator
import blackjack.domain.Rule
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음에 카드 2장을 가지고 있다`() {
        val player = Player(Trump())
        player.cards.items.size shouldBe 2
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 합을 구한다`() {
        val player = Player(Trump())
        val cards = player.cards
        val expectedSum = PointCalculator.sum(cards)

        player.cards.sum shouldBe expectedSum
    }

    @Test
    fun `가지고 있는 카드의 합이 21이 되지 않으면 카드를 계속 뽑을 수 있다`() {
        val trump = Trump()
        val player = Player(trump)

        while (player.cards.sum < 21) {
            player.canDrawCard() shouldBe true
            player.drawCard(trump)
        }

        player.canDrawCard() shouldBe false
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(Trump(), PlayerInfo(name = "홍길동"))
        player.info.name shouldBe "홍길동"
    }

    @Test
    fun `플레이어의 승패가 기록된다`() {
        val trump = Trump()
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.ACE), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(trump, cards = Cards(dealerCard, trump))

        val playerCards =
            mutableListOf(Card.from(CardType.SPADE, CardValue.TEN), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(trump, cards = Cards(playerCards, trump))

        val winner = Rule.decisionWinner(dealer, player)

        winner shouldBe dealer

        player.info.result.record.win shouldBe 0
        player.info.result.record.lose shouldBe 1

        dealer.info.result.record.win shouldBe 1
        dealer.info.result.record.lose shouldBe 0
    }

    @Test
    fun `무승부일 때는 승패가 기록되지 않는다`() {
        val trump = Trump()
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.ACE), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(trump, cards = Cards(dealerCard, trump))

        val playerCards =
            mutableListOf(Card.from(CardType.HEART, CardValue.ACE), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(trump, cards = Cards(playerCards, trump))

        val winner = Rule.decisionWinner(dealer, player)

        winner shouldBe null

        player.info.result.record.win shouldBe 0
        player.info.result.record.lose shouldBe 0

        dealer.info.result.record.win shouldBe 0
        dealer.info.result.record.lose shouldBe 0
    }

    @Test
    fun `플레이어는 돈을 얻을 수 있다`() {
        val trump = Trump()
        val player = Player(trump)

        player.addMoney(Money(10000))
        player.info.result.profit shouldBe Money(10000)
    }

    @Test
    fun `플레이어는 돈을 잃을 수 있다`() {
        val trump = Trump()
        val player = Player(trump)

        player.minusMoney(Money(10000))
        player.info.result.profit shouldBe Money(-10000)
    }

    @Test
    fun `플레이어 카드가 2장인 경우를 알 수 있다`() {
        val trump = Trump()
        val cards = Cards(
            listOf(
                Card.from(CardType.SPADE, CardValue.NINE),
                Card.from(CardType.HEART, CardValue.FOUR)
            ), trump
        )
        val player = Player(trump, cards = cards)

        player.hasTwoCards() shouldBe true
    }

    @Test
    fun `플레이어 카드가 2장인 경우를 알 수 있다 - 2장 이상인 경우`() {
        val trump = Trump()
        val cards = Cards(
            listOf(
                Card.from(CardType.SPADE, CardValue.NINE),
                Card.from(CardType.HEART, CardValue.FOUR),
                Card.from(CardType.DIAMOND, CardValue.FOUR)
            ), trump
        )
        val player = Player(trump, cards = cards)

        player.hasTwoCards() shouldBe false
    }

    @Test
    fun `플레이어 카드가 블랙잭인 경우를 알 수 있다`() {
        val trump = Trump()
        val cards = Cards(
            listOf(
                Card.from(CardType.SPADE, CardValue.ACE),
                Card.from(CardType.HEART, CardValue.KING),
            ), trump
        )
        val player = Player(trump, cards = cards)

        player.isBlackJack() shouldBe true
    }

    @Test
    fun `플레이어 카드가 블랙잭인 경우를 알 수 있다 - 21점이 미만`() {
        val trump = Trump()
        val cards = Cards(
            listOf(
                Card.from(CardType.SPADE, CardValue.ACE),
                Card.from(CardType.HEART, CardValue.KING),
                Card.from(CardType.HEART, CardValue.SIX),
            ), trump
        )
        val player = Player(trump, cards = cards)

        player.isBlackJack() shouldBe false
    }

    @Test
    fun `플레이어 카드가 블랙잭인 경우를 알 수 있다 - 21점이 초과`() {
        val trump = Trump()
        val cards = Cards(
            listOf(
                Card.from(CardType.SPADE, CardValue.QUEEN),
                Card.from(CardType.HEART, CardValue.KING),
                Card.from(CardType.HEART, CardValue.SIX),
            ), trump
        )
        val player = Player(trump, cards = cards)

        player.isBlackJack() shouldBe false
    }
}
