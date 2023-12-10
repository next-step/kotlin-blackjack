package blackjack

import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import blackjack.game.GameResult
import blackjack.game.GameResultGenerator
import blackjack.game.ScoreCalculator
import blackjack.participant.BettingAmount
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameResultGeneratorTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    @Test
    fun `딜러가 버스트면 해당 시점에 살아있는 나머지 사용자는 베팅 금액을 잃지 않는다`() {
        val name = "홍길동"
        val player = Player(Name(name), BettingAmount(1000))
        player.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
            )
        )
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
                PictureCard(CardPicture.JACK, CardPattern.CLOVER),
                NormalCard(2, CardPattern.CLOVER),
            )
        )
        val result = GameResultGenerator.generateGameResult(listOf(player), dealer)

        result.resultMap[player.name]?.amount shouldBe 1000
    }

    @Test
    fun `딜러가 버스트여도 사용자가 버스트이면 사용자는 배팅 금액을 잃는다`() {
        val name = "홍길동"
        val player = Player(Name(name), BettingAmount(1000))
        player.drawCard(
            listOf(
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
                PictureCard(CardPicture.JACK, CardPattern.CLOVER),
                NormalCard(2, CardPattern.CLOVER),
            )
        )
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
                PictureCard(CardPicture.JACK, CardPattern.CLOVER),
                NormalCard(2, CardPattern.CLOVER),
            )
        )
        val result = GameResultGenerator.generateGameResult(listOf(player), dealer)

        result.resultMap[player.name]?.amount shouldBe -1000
    }
}
