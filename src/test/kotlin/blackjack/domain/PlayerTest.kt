package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player("이름1", bettingMoney = BettingMoney(1000))
        assertThat(player.name).isEqualTo("이름1")
    }

    @Test
    fun `플레이어가 카드를 받고 저장한다`() {
        val player = Player("이름1", bettingMoney = BettingMoney(1000))
        val card = Card.of(Suit.HEART, Rank.ACE)
        player.receiveCard(card)
        assertThat(player.cards).contains(card)
    }

    @Test
    fun `플레이어는 21점을 초과하여 카드를 받을 수 없다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        assertThrows<IllegalStateException>(
            message = "21점을 초과하여 카드를 받을 수 없습니다. 현재 점수: 21",
        ) {
            player.receiveCard(Card.of(Suit.HEART, Rank.TWO))
        }
    }

    @Test
    fun `카드의 총 합이 21이 넘으면 패배이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        assertThat(player.isWinner(dealer = Dealer())).isFalse()
    }

    @Test
    fun `딜러의 카드의 총 합이 21이 넘으면 승리이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.isWinner(dealer)).isTrue()
    }

    @Test
    fun `플레이어의 카드의 총 합이 딜러의 카드의 총 합보다 크면 승리이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.NINE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.isWinner(dealer)).isTrue()
    }

    @Test
    fun `플레이어의 카드의 총 합이 딜러의 카드의 총 합보다 작으면 패배이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.NINE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.isWinner(dealer)).isFalse()
    }

    @Test
    fun `플레이어가 블랙잭으로 승리시 베팅금액의 1_5배를 받는다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )
        player.checkBlackJack()

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(1500)
    }

    @Test
    fun `플레이어가 블랙잭으로 승리시 딜러가 블랙잭이면 베팅금액을 돌려받는다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        player.checkBlackJack()

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        player.checkBlackJack()
        dealer.checkBlackJack()

        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(0L)
    }

    @Test
    fun `플레이어가 승리시 베팅금액을 받는다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.NINE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(1000)
    }

    @Test
    fun `플레이어가 패배시 베팅금액을 잃는다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.NINE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(-1000)
    }

    @Test
    fun `블랙잭인 경우 수익이 1_5배이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        player.checkBlackJack()
        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(1500)
    }

    @Test
    fun `블랙잭인 경우 딜러도 블랙잭이면 수익이 0이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.ACE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        player.checkBlackJack()
        dealer.checkBlackJack()
        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(0)
    }

    @Test
    fun `블랙잭이 아닌 경우 승리하면 수익이 1배이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.NINE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(1000)
    }

    @Test
    fun `블랙잭이 아닌 경우 패배하면 수익이 -1배이다`() {
        val player = Player(
            name = "이름1",
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.NINE))
                add(Card.of(Suit.HEART, Rank.KING))
            },
            bettingMoney = BettingMoney(1000),
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.calculateEarningMoney(dealer)).isEqualTo(-1000)
    }
}
