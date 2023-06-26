package blackjack.domain.model

import blackjack.domain.PointCalculator
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 이름의 기본 값은 딜러이다`() {
        val dealer = Dealer(Game())
        dealer.info.name shouldBe "딜러"
    }

    @Test
    fun `딜러는 처음에 카드 2장을 가지고 있다`() {
        val dealer = Dealer(Game())
        dealer.cards.items.size shouldBe 2
    }

    @Test
    fun `딜러가 가지고 있는 카드의 합을 구한다`() {
        val dealer = Dealer(Game())
        val cards = dealer.cards
        val expectedSum = PointCalculator.sum(cards)

        dealer.cards.sum shouldBe expectedSum
    }

    @Test
    fun `딜러의 초기 카드의 합이 17이 되지 않으면 카드를 한 장 뽑는다`() {
        val game = Game()

        val cards = Cards(
            cards = mutableListOf(
                Card.from(CardType.SPADE, CardValue.SEVEN),
                Card.from(CardType.SPADE, CardValue.FIVE),
            ),
            game
        )
        val dealer = Dealer(game, cards)

        dealer.cards.sum shouldBeLessThan 17
        dealer.canGetCard() shouldBe true
        dealer.addCard(game.getCard())
        dealer.addCard(Card.from(CardType.HEART, CardValue.THREE))

        dealer.canGetCard() shouldBe false
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(Game(), PlayerInfo(name = "홍길동"))
        player.info.name shouldBe "홍길동"
    }
}
