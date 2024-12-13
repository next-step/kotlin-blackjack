package blackjack.service

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol
import blackjack.repository.GameRepository
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldInclude
import org.junit.jupiter.api.Test

class BlackJackServiceTest {
    private val gameRepository = GameRepository()
    private val blackJackService = BlackJackService(gameRepository)

    @Test
    fun `사용자 이름을 받아 게임 생성 테스트`() {
        blackJackService.initPlayers("딜러", listOf("문장호", "제이크"))
        val game = gameRepository.findAll()
        game.players.shouldHaveSize(2)
        game.dealer shouldBe "딜러"
        game.players.first() shouldBe "문장호"
    }

    @Test
    fun `게임 진행중  추가 카드 발급 되었는지 확인 테스트`() {
        blackJackService.initPlayers("딜러", listOf("문장호"))
        val game = blackJackService.startGame()
        val cardSize = game.players.first().getPlayerBlackJack().cards.size
        cardSize shouldBe 3
    }


    @Test
    fun `딜러 버스트 시 모든 플레이어가 승리`() {
        blackJackService.initPlayers("딜러", listOf("문장호", "제이크"))
        val game = gameRepository.findAll()
        val dealer = game.dealer
        val dealerBlackJack = dealer.getDealerBlackJack()

        dealerBlackJack.addCardCount(
            mutableListOf(
                mutableMapOf(CardSymbol.HEART to Card.KING) to true,
                mutableMapOf(CardSymbol.DIAMOND to Card.QUEEN) to true,
                mutableMapOf(CardSymbol.SPADE to Card.A) to true
            ),
        )

        val result = blackJackService.getGameResult()
        val dealerResult = result.dealerResult
        dealerResult.winCount shouldBe 0
        dealerResult.loseCount shouldBe 1
        dealerResult.drawCount shouldBe 0

        val playerResult = result.playerResults.first { it.playerName == "문장호" }
        playerResult.winCount shouldBe 1
        playerResult.loseCount shouldBe 0
        playerResult.drawCount shouldBe 0

    }

    @Test
    fun `딜러와 플레이어 점수 비교`() {

        blackJackService.initPlayers("딜러", listOf("문장호", "장호"))
        val game = gameRepository.findAll()
        val dealer = game.dealer
        val dealerBlackJack = dealer.getDealerBlackJack()
        dealerBlackJack.addCardCount(
            mutableListOf(
                mutableMapOf(CardSymbol.HEART to Card.KING) to true,
            ),
        )

        val player = gameRepository.findPlayerByName("문장호")
        val playerBlackJack = player.getPlayerBlackJack()
        playerBlackJack.addCardCount(
            mutableListOf(
                mutableMapOf(CardSymbol.HEART to Card.KING) to true,
                mutableMapOf(CardSymbol.DIAMOND to Card.QUEEN) to true,
            ),
        )
        val playerTwo = gameRepository.findPlayerByName("장호")
        val playerBlackJackTwo = playerTwo.getPlayerBlackJack()
        playerBlackJackTwo.addCardCount(
            mutableListOf(
                mutableMapOf(CardSymbol.HEART to Card.KING) to true,
                mutableMapOf(CardSymbol.DIAMOND to Card.TWO) to true,
            ),
        )

        val result = blackJackService.getGameResult()
        val dealerResult = result.dealerResult
        dealerResult.winCount shouldBe 0
        dealerResult.loseCount shouldBe 2
        dealerResult.drawCount shouldBe 0

        val playerResult = result.playerResults.first { it.playerName == "문장호" }
        playerResult.winCount shouldBe 1
        playerResult.loseCount shouldBe 0
        playerResult.drawCount shouldBe 0

        val playerResultTwo = result.playerResults.first { it.playerName == "장호" }
        playerResultTwo.winCount shouldBe 1
        playerResultTwo.loseCount shouldBe 0
        playerResultTwo.drawCount shouldBe 0

    }
}
