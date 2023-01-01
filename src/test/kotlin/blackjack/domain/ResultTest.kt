package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.state.Bust
import blackjack.domain.state.FirstTurn
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ResultTest {
    @Test
    fun `승리 결과`() {
        Result.WIN.value shouldBe "승리"
    }

    @Test
    fun `패배 결과`() {
        Result.LOSE.value shouldBe "패배"
    }

    @Test
    fun `딜러와 플레이어를 받아 승패를 구분할 수 있다`() {
        val ten = Card(Shape.DIAMOND, Denomination.TEN)
        val nine = Card(Shape.DIAMOND, Denomination.NINE)
        val six = Card(Shape.DIAMOND, Denomination.SIX)
        val five = Card(Shape.DIAMOND, Denomination.FIVE)

        val dealer = Dealer(FirstTurn.draw(ten, six))
        val player1 = Player("one", FirstTurn.draw(ten, nine))
        val player2 = Player("one", FirstTurn.draw(ten, five))
        val players = listOf(player1, player2)

        Result.dealerResult(dealer, players) shouldContainExactlyInAnyOrder listOf(Result.WIN, Result.LOSE)
        Result.playerResult(dealer, player1) shouldBe Result.WIN
        Result.playerResult(dealer, player2) shouldBe Result.LOSE
    }

    @Test
    fun `딜러가 Bust상태이면 플레이어가 승리한다`() {
        val ten = Card(Shape.DIAMOND, Denomination.TEN)
        val nine = Card(Shape.DIAMOND, Denomination.NINE)
        val five = Card(Shape.DIAMOND, Denomination.FIVE)

        val dealer = Dealer(Bust(Hand(ten)))
        val player1 = Player("one", FirstTurn.draw(ten, nine))
        val player2 = Player("one", FirstTurn.draw(ten, five))
        val players = listOf(player1, player2)

        Result.dealerResult(dealer, players) shouldContainExactlyInAnyOrder listOf(Result.LOSE, Result.LOSE)
        Result.playerResult(dealer, player1) shouldBe Result.WIN
        Result.playerResult(dealer, player2) shouldBe Result.WIN
    }

    @Test
    fun `딜러가 Bust상태가 아니고 플레이어가 Bust이면 딜러가 승리한다`() {
        val ten = Card(Shape.DIAMOND, Denomination.TEN)
        val nine = Card(Shape.DIAMOND, Denomination.NINE)

        val dealer = Dealer(FirstTurn.draw(ten, nine))
        val player1 = Player("one", Bust(Hand(ten)))
        val player2 = Player("one", Bust(Hand(ten)))

        val players = listOf(player1, player2)

        Result.dealerResult(dealer, players) shouldContainExactlyInAnyOrder listOf(Result.WIN, Result.WIN)
        Result.playerResult(dealer, player1) shouldBe Result.LOSE
        Result.playerResult(dealer, player2) shouldBe Result.LOSE
    }
}
