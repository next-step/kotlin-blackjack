package blackjack.view

import blackjack.domain.BlackJackResult
import blackjack.domain.DealerResult
import blackjack.domain.PlayerResult
import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player

object OutputView {
    fun results(blackJackResult: BlackJackResult) {
        println("## 최종 승패")
        dealerResult(blackJackResult.getDealerResult())
        blackJackResult.getPlayerResults().forEach {
            playerResult(it)
        }
    }

    fun dealerResult(dealerResult: DealerResult) {
        println("${dealerResult.dealerName}: ${dealerResult.winCount}승 ${dealerResult.loseCount}패")
    }

    fun playerResult(playerResult: PlayerResult) {
        println("${playerResult.playerName}: ${playerResult.getResult()}")
    }

    fun gameStart(game: Game) {
        val dealer = game.dealer
        val players = game.players
        val playersTString = players.map { player -> player.name }.joinToString(", ")
        println("${dealer.name}와 ${playersTString}에게 2장의 나누었습니다.")
        gameStartDealerInfo(dealer)
        players.forEach { gameStartPlayerInfo(it) }
    }

    private fun gameStartDealerInfo(dealer: Dealer) {
        println(
            "${dealer.name}: ${dealer.hand.showCards()}",
        )
    }

    private fun gameStartPlayerInfo(player: Player) {
        println(
            "${player.name}: ${player.hand.showCards()}",
        )
    }

    private fun endDealerInfo(dealer: Dealer) {
        println(
            "${dealer.name} 카드: ${dealer.hand.showCards()} - 결과: ${
                dealer.hand.getTotalCardValue()
            }",
        )
    }

    private fun endPlayerInfo(player: Player) {
        println(
            "${player.name} 카드: ${player.hand.showCards()} - 결과: ${
                player.hand.getTotalCardValue()
            }",
        )
    }

    fun gameCardResult(game: Game) {
        endDealerInfo(game.dealer)
        game.players.forEach {
            endPlayerInfo(it)
        }
    }
}
