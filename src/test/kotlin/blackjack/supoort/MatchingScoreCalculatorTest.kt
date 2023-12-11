package blackjack.supoort

import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import blackjack.participant.Result
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class MatchingScoreCalculatorTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

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

        MatchingScoreCalculator.matchingScore(player, dealer).shouldBeInstanceOf<Result.Win>()
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

        MatchingScoreCalculator.matchingScore(player, dealer).shouldBeInstanceOf<Result.Lose>()
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

        MatchingScoreCalculator.matchingScore(player, dealer).shouldBeInstanceOf<Result.Win>()
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

        MatchingScoreCalculator.matchingScore(player, dealer).shouldBeInstanceOf<Result.Lose>()
    }
}
