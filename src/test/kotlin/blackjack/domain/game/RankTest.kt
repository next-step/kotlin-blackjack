package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.Shape
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class RankTest {

    @Test
    internal fun `둘 다 블랙잭이면 플레이어는 베팅한 금액을 돌려 받는다`() {
        val blackJack = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.A),
            )
        )
        val player1 = Player("pobi", blackJack)
        val dealer = Dealer(blackJack)
        Rank.of(player1, dealer) shouldBe Rank.DRAW
    }

    @Test
    internal fun `처음 두장의 카드 합이 21일 경우 블랙잭이 나온다`() {
        val blackJack = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.A),
            )
        )

        val dealerCard = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SIX),
            )
        )
        val player1 = Player("pobi", blackJack)
        val dealer = Dealer(dealerCard)
        Rank.of(player1, dealer) shouldBe Rank.BLACKJACK
    }

    @Test
    internal fun `딜러가 21을 초과하면 플레이어들은 베팅 금액을 돌려 받는다`() {
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
                Card(Shape.DIAMOND, Character.SIX),
            )
        )
        val player1 = Player("pobi", playerCard)
        val dealer = Dealer(dealerCard)
        Rank.of(player1, dealer) shouldBe Rank.WON
    }

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
        Rank.of(player1, dealer) shouldBe Rank.WON
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
        Rank.of(player1, dealer) shouldBe Rank.LOST
    }
}
