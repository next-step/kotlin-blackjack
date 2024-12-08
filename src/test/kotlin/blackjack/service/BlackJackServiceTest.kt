package blackjack.service

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol
import blackjack.repository.GameRepository
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
        blackJackService.initPlayers(listOf("문장호", "제이크"))
        val games = gameRepository.findAll()
        games.shouldHaveSize(2)
        games[0].player shouldBe "문장호"
        games[1].player shouldBe "제이크"
    }

    @Test
    fun `게임 진행중  추가 카드 발급 되었는지 확인 테스트`() {
        val players = listOf("문장호")
        blackJackService.initPlayers(players)
        val games = blackJackService.startGame()

        val cardSize = games.first().getPlayerBlackJack().card.size
        cardSize shouldBe 3
    }

    @Test
    fun `21 초과시 버스트로 게임 종료`() {
        val players = listOf("문장호")
        blackJackService.initPlayers(players)
        blackJackService.startGame()

        val player = gameRepository.findByName("문장호")!!
        val playerBlackJack = player.getPlayerBlackJack()
        playerBlackJack.addCardCount(
            mutableListOf(
                mutableMapOf(CardSymbol.HEART to Card.KING),
                mutableMapOf(CardSymbol.DIAMOND to Card.QUEEN),
                mutableMapOf(CardSymbol.SPADE to Card.A),
            ),
        )

        val exception = runCatching { blackJackService.gameContinue("문장호") }.exceptionOrNull()
        exception shouldNotBe null
        exception!!.message shouldInclude "Player 문장호 has busted with a total of"
    }
}
