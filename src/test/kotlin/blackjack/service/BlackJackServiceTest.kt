package blackjack.service

import blackjack.entity.Game
import blackjack.repository.GameRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackJackServiceTest {
    private val gameRepository = GameRepository()
    private val blackJackService = BlackJackService(gameRepository)

    @Test
    fun `사용자 이름을 받아 게임 생성 테스트`() {
        val names = listOf("문장호", "제이크")
        blackJackService.initPlayers(listOf("문장호", "제이크"))
        val games = gameRepository.findAll()
        names.joinToString { "," } shouldBe games.map { it.player }.joinToString { "," }
    }

    @Test
    fun `게임 시작 시 초기 생성된 게이머들에게 각각  2장의 랜덤한 카드를 발급하도록 한다`() {
        val name = "문장호"
        gameRepository.savePlayer(Game(name))
        val games = blackJackService.startGame()
        val game = games.first()
        game.getPlayerBlackJack().card.size shouldBe 2
    }
}
