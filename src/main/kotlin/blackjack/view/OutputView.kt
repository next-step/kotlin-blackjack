package blackjack.view

import blackjack.BlackJackGame
import blackjack.GameResult
import blackjack.domain.CardMark
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerWinLoseResult
import blackjack.domain.Players

object OutputView {
    private const val SPADE_STR = "스페이드"
    private const val HEART_STR = "하트"
    private const val DIAMOND_STR = "다이아몬드"
    private const val CLOVER_STR = "클로버"

    fun printPlayerCards(player: Player) {
        println(createPlayerCardMessage(player))
    }

    private fun printDealerCards(dealer: Dealer) {
        println(createDealerCardMessage(dealer))
    }

    fun printCurrentStatus(game: BlackJackGame) {
        printDealerCards(game.dealer)
        game.players.members.forEach(::printPlayerCards)
        println()
    }

    fun printGameResult(game: BlackJackGame) {
        println()
        printDealerResult(game.dealer)
        printPlayersResult(game.players)
        printGameWinLoseResult(game.getGameResult())
    }

    fun printDealerChance(dealerChance: Boolean) {
        if (dealerChance) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }
    }

    private fun printGameWinLoseResult(gameResult: GameResult) {
        val dealerResult = gameResult.dealerResult
        val playerGameResults = gameResult.playerGameResults

        println()
        println("딜러: ${dealerResult.winCount}승 ${dealerResult.pushCount}무 ${dealerResult.loseCount}패")
        playerGameResults.forEach { println("${it.name}: ${convertResultToMessage(it.result)}") }
    }

    private fun convertResultToMessage(result: PlayerWinLoseResult): String {
        return when (result) {
            PlayerWinLoseResult.WIN -> "승"
            PlayerWinLoseResult.LOSE -> "패"
            PlayerWinLoseResult.PUSH -> "무"
        }
    }

    private fun printDealerResult(dealer: Dealer) {
        println("${createDealerCardMessage(dealer)} - 결과: ${dealer.hand.getCardsSum()}")
    }

    private fun printPlayersResult(players: Players) {
        players.members.forEach { printPlayerResult(it) }
    }

    private fun printPlayerResult(player: Player) {
        println("${createPlayerCardMessage(player)} - 결과: ${player.hand.calculateCardsMaxSum()}")
    }

    private fun convertMarkToString(mark: CardMark) =
        when (mark) {
            CardMark.SPADE -> SPADE_STR
            CardMark.HEART -> HEART_STR
            CardMark.DIAMOND -> DIAMOND_STR
            CardMark.CLOVER -> CLOVER_STR
        }

    private fun createDealerCardMessage(dealer: Dealer): String {
        return buildString {
            append("딜러: ")
            append(createCardsOutputMessage(dealer.hand.cards))
        }
    }

    private fun createPlayerCardMessage(player: Player): String {
        return buildString {
            append(player.name)
            append("카드: ")
            append(createCardsOutputMessage(player.hand.cards))
        }
    }

    private fun createCardsOutputMessage(cards: Cards): String {
        return cards.group
            .filter { it.isFaceUp }
            .joinToString { it.number.symbol + convertMarkToString(it.mark) }
    }
}
