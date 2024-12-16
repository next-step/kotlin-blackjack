package blackjack.domain

import blackjack.domain.BlackJackGame.DEFAULT_FACE_UP
import blackjack.domain.BlackJackGame.INIT_FACE_UP
import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol
import blackjack.entity.CardInfo
import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackJackResultTest {
    @Test
    fun `사용자 이름을 받아 게임 생성 테스트`() {
        val initDealerCard =
            (Deal.giveCards(DEFAULT_FACE_UP, false) + Deal.giveCards(DEFAULT_FACE_UP, true))
        val dealer = Dealer("딜러", initDealerCard)
        val playersName = listOf("문장호", "제이크")
        val players = playersName.map { Player(it, 1000, Deal.giveCards(INIT_FACE_UP)) }.toSet()
        BlackJackGame.setGameRepository(Game(dealer, players))

        val game = BlackJackGame.getGameInfo()
        game.players.shouldHaveSize(2)
        game.dealer.name shouldBe "딜러"
        game.players.first().name shouldBe "문장호"
    }

    @Test
    fun `딜러 버스트 시 모든 플레이어가 승리`() {
        val initDealerCard =
            listOf(
                CardInfo(mapOf(CardSymbol.HEART to Card.KING), false),
                CardInfo(mapOf(CardSymbol.DIAMOND to Card.QUEEN), true),
                CardInfo(mapOf(CardSymbol.SPADE to Card.TWO), true),
            )

        val dealer = Dealer("딜러", initDealerCard)

        val playersName = listOf("문장호", "제이크")
        val players = playersName.map { Player(it, 10000, Deal.giveCards(INIT_FACE_UP)) }.toSet()
        BlackJackGame.setGameRepository(Game(dealer, players))

        val result = BlackJackGame.getGameResult()
        val dealerResult = result.getDealerResult()
        dealerResult.dealerProfitAmount shouldBe result.getPlayerResults().sumOf { -it.getResult() }

        val playerResult = result.getPlayerResults().first { it.playerName == "문장호" }
        playerResult.getResult() shouldBe 10000
    }

    @Test
    fun `딜러와 플레이어 점수 비교`() {
        val initDealerCard =
            listOf(
                CardInfo(mapOf(CardSymbol.HEART to Card.KING), false),
            )

        val dealer = Dealer("딜러", initDealerCard)
        val players =
            setOf(
                Player(
                    "문장호",
                    1000,
                    listOf(
                        CardInfo(mapOf(CardSymbol.HEART to Card.KING), false),
                        CardInfo(mapOf(CardSymbol.DIAMOND to Card.QUEEN), false),
                    ),
                ),
                Player(
                    "장호",
                    1000,
                    listOf(
                        CardInfo(mapOf(CardSymbol.HEART to Card.KING), false),
                        CardInfo(mapOf(CardSymbol.DIAMOND to Card.TWO), false),
                    ),
                ),
            )
        BlackJackGame.setGameRepository(Game(dealer, players))

        val result = BlackJackGame.getGameResult()
        val dealerResult = result.getDealerResult()
        dealerResult.dealerProfitAmount shouldBe result.getPlayerResults().sumOf { -it.getResult() }

        val playerResult = result.getPlayerResults().first { it.playerName == "문장호" }
        playerResult.getResult() shouldBe 1000

        val playerResultTwo = result.getPlayerResults().first { it.playerName == "장호" }
        playerResult.getResult() shouldBe 1000
    }
}
