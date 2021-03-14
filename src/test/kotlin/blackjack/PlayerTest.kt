package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    internal fun `플레이어는 받은 카드 목록이 있다`() {
        val player = Player.Person("pobi")
            .apply {
                accept(Card("2", Symbol.HEARTS))
                accept(Card("8", Symbol.SPADES))
            }
        assertThat(player.cards).containsExactly(
            Card("2", Symbol.HEARTS),
            Card("8", Symbol.SPADES)
        )
    }

    @Test
    internal fun `카드목록의 합을 계산한다`() {
        val player = Player.Person("pobi")
            .apply {
                accept(Card("2", Symbol.HEARTS))
                accept(Card("8", Symbol.SPADES))
            }
        assertThat(player.score()).isEqualTo(10)
    }

    @Test
    fun `Ace 는 21 에 가까운 수로 선택한다`() {
        assertThat(
            Player.Person("pobi")
                .apply {
                    accept(Card("A", Symbol.HEARTS))
                    accept(Card("K", Symbol.SPADES))
                }.score()
        ).isEqualTo(21)
    }

    @Test
    fun `Ace 가 포함된 합이 21을 초과하면 1로 계산한다`() {
        assertThat(
            Player.Person("pobi")
                .apply {
                    accept(Card("A", Symbol.HEARTS))
                    accept(Card("10", Symbol.DIAMONDS))
                    accept(Card("2", Symbol.CLUBS))
                }.score()
        ).isEqualTo(13)
    }

    @Test
    fun `21을 초과할 수 있다`() {
        assertThat(
            Player.Person("pobi")
                .apply {
                    accept(Card("8", Symbol.HEARTS))
                    accept(Card("8", Symbol.DIAMONDS))
                    accept(Card("8", Symbol.CLUBS))
                }.score()
        ).isEqualTo(24)
    }

    @Test
    fun `딜러는 16 미만이면 한장 더 받는다`() {
        val dealer = Player.Dealer()
            .apply {
                accept(Card("10", Symbol.HEARTS))
                accept(Card("6", Symbol.DIAMONDS))
            }
        dealer.take { Card("2", Symbol.CLUBS) }
        assertThat(dealer.score()).isEqualTo(18)
    }
}
