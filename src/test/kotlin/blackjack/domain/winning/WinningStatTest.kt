package blackjack.domain.winning

import blackjack.domain.card.Card
import blackjack.domain.card.RandomCardDeck
import blackjack.domain.card.RandomCardDeck.Companion.DIAMOND
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.score.PlayerScore
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class WinningStatTest : FreeSpec({

    "playerGameResult" - {

        "플레이어가 딜러한테 진 경우 게임 결과에 PLAYER_LOOSE이 포함된다." {
            val winningStat = createWinningStat(2, 3)

            val result = winningStat.playerGameResult()
            result.size shouldBe 1
            result[0].player.name shouldBe "test"
            result[0].playerWinningState shouldBe WinningState.PLAYER_LOOSE
        }

        "플레이어가 딜러한테 이긴 경우 게임 결과에 PLAYER_WIN이 포함된다." {
            val winningStat = createWinningStat(2, 1)

            val result = winningStat.playerGameResult()
            result.size shouldBe 1
            result[0].player.name shouldBe "test"
            result[0].playerWinningState shouldBe WinningState.PLAYER_WIN
        }

        "딜러가 21점을 초과한 경우 DEALER_BUST가 포함된다." {
            val winningStat = createWinningStat(2, 22)

            val result = winningStat.playerGameResult()
            result.size shouldBe 1
            result[0].player.name shouldBe "test"
            result[0].playerWinningState shouldBe WinningState.DEALER_BUST
        }

        "플레이어가 21점을 초과한 경우 PLAYER_BUST가 포함된다." {
            val winningStat = createWinningStat(22, 20)

            val result = winningStat.playerGameResult()
            result.size shouldBe 1
            result[0].player.name shouldBe "test"
            result[0].playerWinningState shouldBe WinningState.PLAYER_BUST
        }

        "플레이어와 딜러가 동일한 점수일 경우 PLAYER_TIE가 포함된다." {
            val winningStat = createWinningStat(20, 20)

            val result = winningStat.playerGameResult()
            result.size shouldBe 1
            result[0].player.name shouldBe "test"
            result[0].playerWinningState shouldBe WinningState.PLAYER_TIE
        }

        "플레이어가 블랙잭일 경우 PLAYER_BLACKJACK이 포함된다." {
            val player = Player("test", 1000)
            player.addCard(Card(DIAMOND, RandomCardDeck.ACE),)
            player.addCard(Card(DIAMOND, RandomCardDeck.JACK))
            val winningStat = WinningStat(
                listOf(PlayerScore(player, 21)),
                PlayerScore(Dealer(), 20)
            )

            val result = winningStat.playerGameResult()
            result.size shouldBe 1
            result[0].player.name shouldBe "test"
            result[0].playerWinningState shouldBe WinningState.PLAYER_BLACKJACK
        }
    }
})

fun createWinningStat(playerScore: Int, dealerScore: Int): WinningStat {
    val player = Player("test", 1000)
    return WinningStat(
        listOf(PlayerScore(player, playerScore)),
        PlayerScore(Dealer(), dealerScore)
    )
}
