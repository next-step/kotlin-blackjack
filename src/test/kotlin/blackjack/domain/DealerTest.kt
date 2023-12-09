package blackjack.domain

import blackjack.game.ScoreCalculator
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import blackjack.participant.Result
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class DealerTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    @Test
    fun `딜러는 점수가 16점이하면 카드를 뽑는다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                NormalCard(3, CardPattern.CLOVER),
            )
        )

        dealer.shouldDraw() shouldBe true
    }

    @Test
    fun `딜러는 점수가 17점이상이면 카드를 뽑을 수 없다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
            )
        )

        dealer.shouldDraw() shouldBe false
    }

    @Test
    fun `딜러가 버스트 상태이고 플레이어가 버스트 상태가 아니라면 플레이어가 승리한다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(5, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
                NormalCard(8, CardPattern.CLOVER),
            )
        )

        val player = Player(Name("홍길동"), scoreCalculator)
        player.drawCard(
            listOf(
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.SPADE)
            )
        )

        dealer.matchingScore(player).shouldBeInstanceOf<Result.Win>()
    }

    @Test
    fun `플레이어가 버스트 상태라면 플레이어가 패배한다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(5, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
                NormalCard(8, CardPattern.CLOVER),
            )
        )

        val player = Player(Name("홍길동"), scoreCalculator)
        player.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.SPADE),
                NormalCard(8, CardPattern.CLOVER),
            )
        )

        dealer.matchingScore(player).shouldBeInstanceOf<Result.Lose>()
    }

    @Test
    fun `플레이어가 점수가 딜러보다 높으면 플레이어가 승리한다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(5, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
            )
        )

        val player = Player(Name("홍길동"), scoreCalculator)
        player.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
            )
        )

        dealer.matchingScore(player).shouldBeInstanceOf<Result.Win>()
    }

    @Test
    fun `플레이어가 점수가 딜러보다 낮으면 플레이어가 패배한다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(5, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
            )
        )

        val player = Player(Name("홍길동"), scoreCalculator)
        player.drawCard(
            listOf(
                NormalCard(4, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
            )
        )

        dealer.matchingScore(player).shouldBeInstanceOf<Result.Lose>()
    }
}
