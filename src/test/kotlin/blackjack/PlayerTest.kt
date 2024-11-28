package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player("이름1")
        assertThat(player.name).isEqualTo("이름1")
    }

    @Test
    fun `플레이어가 카드를 받고 저장한다`() {
        val player = Player("이름1")
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
        )

        val dealer = Dealer(
            cards = Cards().apply {
                add(Card.of(Suit.HEART, Rank.KING))
                add(Card.of(Suit.HEART, Rank.KING))
            },
        )

        assertThat(player.isWinner(dealer)).isFalse()
    }
}
