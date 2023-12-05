@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어의 점수가 21을 초과하면 딜러의 점수와 상관없이 패배한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.SPADE)
        val player = Player(name = "a", cards = Cards(card1, card2, card3))
        val dealer = Dealer()

        val gameResult = GameResult(listOf(player), dealer)
        val actual = gameResult.playerResults

        assertThat(actual).containsExactly(PlayerResult.LOSE)
    }

    @Test
    fun `딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관없이 승리한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.CLUB)
        val player = Player(name = "a", cards = Cards(card1, card2))
        val dealer = Dealer(cards = Cards(card1, card2, card3))

        val gameResult = GameResult(listOf(player), dealer)
        val actual = gameResult.playerResults

        assertThat(actual).containsExactly(PlayerResult.WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 승리한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.CLUB)
        val player = Player(name = "a", cards = Cards(card1, card2))
        val dealer = Dealer(cards = Cards(card1, card3))

        val gameResult = GameResult(listOf(player), dealer)
        val actual = gameResult.playerResults

        assertThat(actual).containsExactly(PlayerResult.WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수와 같으면 무승부이다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val player = Player(name = "a", cards = Cards(card1, card2))
        val dealer = Dealer(cards = Cards(card1, card2))

        val gameResult = GameResult(listOf(player), dealer)
        val actual = gameResult.playerResults

        assertThat(actual).containsExactly(PlayerResult.DRAW)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 낮으면 플레이어가 패배한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.CLUB)
        val player = Player(name = "a", cards = Cards(card1, card3))
        val dealer = Dealer(cards = Cards(card1, card2))

        val gameResult = GameResult(listOf(player), dealer)
        val actual = gameResult.playerResults

        assertThat(actual).containsExactly(PlayerResult.LOSE)
    }

    @Test
    fun `플레이어 승패에 따른 딜러의 승패 결과를 반환한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.CLUB)
        val player = Player(name = "a", cards = Cards(card1, card2))
        val dealer = Dealer(cards = Cards(card1, card3))

        val gameResult = GameResult(listOf(player), dealer)
        val actual = gameResult.getDealerResult()

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                PlayerResult.WIN to 0,
                PlayerResult.DRAW to 0,
                PlayerResult.LOSE to 1,
            )
        )
    }
}
