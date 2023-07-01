package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.Shape
import blackjack.domain.game.Rank
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class RankTest {
    @Test
    internal fun `플레이어가 점수가 더 높으면 플레이어는 이긴다`() {
        val playerCard = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SEVEN),
            )
        )
        val dealerCard = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SIX),
            )
        )
        val player1 = Player("pobi", playerCard)
        val dealer = Dealer(dealerCard)
        Rank.of(player1.score(), dealer.score()) shouldBe Rank.WON
    }

    @Test
    internal fun `플레이어가 점수가 더 낮으면 플레이어는 진다`() {
        val dealerCard = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SEVEN),
            )
        )
        val playerCard = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SIX),
            )
        )
        val player1 = Player("pobi", playerCard)
        val dealer = Dealer(dealerCard)
        Rank.of(player1.score(), dealer.score()) shouldBe Rank.LOST
    }
}
