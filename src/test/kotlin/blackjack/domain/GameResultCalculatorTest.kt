package blackjack.domain

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol
import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameResultCalculatorTest {
    @Test
    fun `딜러 버스트 시 모든 플레이어 승리`() {
        // Arrange
        val dealer = Dealer("딜러")
        dealer.getDealerBlackJack().addCardCount(
            mutableListOf(
                mutableMapOf(CardSymbol.HEART to Card.TEN) to true,
                mutableMapOf(CardSymbol.SPADE to Card.KING) to true,
                mutableMapOf(CardSymbol.DIAMOND to Card.THREE) to true,
            ),
        )

        val players =
            setOf(
                Player("문장호").apply {
                    getPlayerBlackJack().addCardCount(
                        mutableListOf(
                            mutableMapOf(CardSymbol.HEART to Card.EIGHT) to true,
                            mutableMapOf(CardSymbol.SPADE to Card.NINE) to true,
                        ),
                    )
                },
                Player("장호").apply {
                    getPlayerBlackJack().addCardCount(
                        mutableListOf(
                            mutableMapOf(CardSymbol.HEART to Card.SEVEN) to true,
                            mutableMapOf(CardSymbol.DIAMOND to Card.SIX) to true,
                        ),
                    )
                },
            )

        val game = Game(dealer, players)

        val result = GameResultCalculator.calculateGameResult(game)

        result.dealerResult.winCount shouldBe 0
        result.dealerResult.loseCount shouldBe 2
        result.dealerResult.drawCount shouldBe 0

        result.playerResults[0].winCount shouldBe 1
        result.playerResults[1].winCount shouldBe 1
    }

    @Test
    fun `딜러와 플레이어 점수 비교`() {
        val dealer = Dealer("딜러")
        dealer.getDealerBlackJack().addCardCount(
            mutableListOf(
                mutableMapOf(CardSymbol.HEART to Card.TEN) to true,
                mutableMapOf(CardSymbol.SPADE to Card.SEVEN) to true,
            ),
        )

        val players =
            setOf(
                Player("장").apply {
                    getPlayerBlackJack().addCardCount(
                        mutableListOf(
                            mutableMapOf(CardSymbol.HEART to Card.EIGHT) to true,
                            mutableMapOf(CardSymbol.SPADE to Card.NINE) to true,
                        ),
                    )
                },
                Player("호").apply {
                    getPlayerBlackJack().addCardCount(
                        mutableListOf(
                            mutableMapOf(CardSymbol.DIAMOND to Card.THREE) to true,
                            mutableMapOf(CardSymbol.HEART to Card.TEN) to true,
                        ),
                    )
                },
                Player("제이크").apply {
                    getPlayerBlackJack().addCardCount(
                        mutableListOf(
                            mutableMapOf(CardSymbol.DIAMOND to Card.TEN) to true,
                            mutableMapOf(CardSymbol.SPADE to Card.KING) to true,
                        ),
                    )
                },
            )

        val game = Game(dealer, players)

        val result = GameResultCalculator.calculateGameResult(game)

        result.dealerResult.winCount shouldBe 1
        result.dealerResult.loseCount shouldBe 1
        result.dealerResult.drawCount shouldBe 1

        result.playerResults[0].drawCount shouldBe 1
        result.playerResults[1].loseCount shouldBe 1
        result.playerResults[2].winCount shouldBe 1
    }
}
