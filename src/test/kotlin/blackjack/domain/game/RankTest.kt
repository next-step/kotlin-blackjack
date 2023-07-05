package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.Shape
import blackjack.domain.card.TestCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.State
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class RankTest {

    @Test
    internal fun `블랙잭이면 수익은 150%다`() {
        val bettingAmount = 10000

        Rank.BLACKJACK * bettingAmount shouldBe 15000
    }

    @Test
    internal fun `이긴경우 베팅금액을 돌려받는다`() {
        val bettingAmount = 10000

        Rank.WON * bettingAmount shouldBe 10000
    }

    @Test
    internal fun `지면 베팅금액을 잃는다`() {
        val bettingAmount = 10000

        Rank.LOST * bettingAmount shouldBe -10000
    }

    @Test
    internal fun `비기면 배팅금액을 돌려 받는다`() {
        val bettingAmount = 10000

        Rank.DRAW * bettingAmount shouldBe 10000
    }
}
