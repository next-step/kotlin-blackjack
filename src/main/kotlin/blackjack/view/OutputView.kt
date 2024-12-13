package blackjack.view

import blackjack.dto.BlackJackResult
import blackjack.dto.DealerResult
import blackjack.dto.PlayerResult
import blackjack.entity.BlackJack
import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player

object OutputView {

    fun results(blackJackResult: BlackJackResult) {
        println("## 최종 승패")
        dealerResult(blackJackResult.dealerResult)
        blackJackResult.playerResults.forEach {
            playerResult(it)
        }
    }

    fun dealerResult(dealerResult: DealerResult) {
        println("${dealerResult.dealerName}: ${dealerResult.winCount}승 ${dealerResult.loseCount}패")
    }

    fun playerResult(playerResult: PlayerResult) {
        println("${playerResult.playerName}: ${playerResult.getResult()}")
    }

    private fun resultCardInfos(blackJack: BlackJack): String {
        val cards = blackJack.cards
        return cards
            .flatMap { (cardMap, _) ->
                cardMap.map { (symbol, card) ->
                    String.format("%s%s", card.getValue(), symbol.getType())
                }
            }
            .joinToString(", ")
    }

    fun gameCardResult(game: Game) {
        dealerInfo(game.dealer)
        game.players.forEach {
            playerInfo(it)
        }
    }

    private fun dealerInfo(dealer: Dealer) {
        val dealerCardInfo = resultCardInfos(dealer.getDealerBlackJack())
        println(
            "${dealer.name}카드: $dealerCardInfo - ${
                dealer.getDealerBlackJack().getTotalCardValue()
            }"
        )
    }

    private fun playerInfo(player: Player) {
        val playerCardInfo = resultCardInfos(player.getPlayerBlackJack())
        println(
            "${player.name}카드: $playerCardInfo - ${
                player.getPlayerBlackJack().getTotalCardValue()
            }"
        )
    }

    fun gameStart(game: Game) {
        val dealer = game.dealer
        val players = game.players
        val playersTString = players.map { player -> player.name } .joinToString(", ")
        println("${dealer.name}와 ${playersTString}에게 2장의 나누었습니다.")
        gameStartDealerInfo(dealer)
        players.forEach { gameStartPlayerInfo(it) }
    }

    private fun gameStartDealerInfo(dealer: Dealer) {
        val cardInfo = resultCardInfos(dealer.getDealerBlackJack())
        println(
            "${dealer.name} 카드: $cardInfo - 결과: ${
                dealer.getDealerBlackJack().getTotalCardValue()
            }"
        )
    }

    private fun gameStartPlayerInfo(player: Player) {
        val playerCardInfo = resultCardInfos(player.getPlayerBlackJack())
        println(
            "${player.name} 카드: $playerCardInfo - 결과: ${
                player.getPlayerBlackJack().getTotalCardValue()
            }"
        )
    }

}
