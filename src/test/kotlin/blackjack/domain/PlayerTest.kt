package blackjack.domain

import blackjack.domain.BlackjackCard.CardNumber
import blackjack.domain.BlackjackCard.Emblem
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 시작과 동시에 두 장의 카드를 가지고 있는다`() {
        val dealer = Dealer()
        val player = BlackjackPlayer("test")

        dealer initCardTo player

        player.cards.size shouldBe 2
    }

    @Test
    fun `플레이어는 가지고 있는 카드에서 가장 21에 가까운 값을 가져온다`() {
        val dealer = Dealer(
            deck = Deck(
                mutableListOf(
                    BlackjackCard(CardNumber.A, Emblem.다이아),
                    BlackjackCard(CardNumber.J, Emblem.다이아),
                    BlackjackCard(CardNumber.Q, Emblem.다이아),
                ),
            ),
        )
        val player = BlackjackPlayer("test")
        dealer initCardTo player
        dealer giveCardTo player

        player.score shouldBe 21
    }

    @Test
    fun `A는 11로도 계산될 수 있다`() {
        val dealer = Dealer(
            deck = Deck(
                mutableListOf(
                    BlackjackCard(CardNumber.A, Emblem.다이아),
                    BlackjackCard(CardNumber.`2`, Emblem.다이아),
                    BlackjackCard(CardNumber.`8`, Emblem.다이아),
                ),
            ),
        )
        val player = BlackjackPlayer("test")
        dealer initCardTo player
        dealer giveCardTo player

        player.score shouldBe 21
    }

    @Test
    fun `21이 넘는 경우 가장 큰 값이 반환 된다`() {
        val dealer = Dealer(
            deck = Deck(
                mutableListOf(
                    BlackjackCard(CardNumber.K, Emblem.다이아),
                    BlackjackCard(CardNumber.Q, Emblem.다이아),
                    BlackjackCard(CardNumber.J, Emblem.다이아),
                ),
            ),
        )
        val player = BlackjackPlayer("test")
        dealer initCardTo player
        dealer giveCardTo player

        player.score shouldBe 30
    }
}
