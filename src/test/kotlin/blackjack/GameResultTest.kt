package blackjack

import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameResultTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    @Test
    fun `딜러가 버스트면 해당 시점에 살아있는 나머지 사용자는 승리한다`() {
        val name = "홍길동"
        val player = Player(name, scoreCalculator)
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
        val result = GameResult(listOf(dealer, player))

        result.resultMap[name] shouldBe "승"
    }

    @Test
    fun `딜러가 버스트여도 사용자가 버스트이면 사용자는 패배한다`() {
        val name = "홍길동"
        val player = Player(name, scoreCalculator)
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
        val result = GameResult(listOf(dealer, player))

        result.resultMap[name] shouldBe "패"
    }
}
