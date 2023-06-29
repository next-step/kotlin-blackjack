package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardType
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.GameResultState
import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {

    private const val SEPERATOR = ", "
    private const val ZERO_COUNT = 0

    fun showPlayerSet(players: Players) {
        val playersName = players.getPlayers().joinToString(SEPERATOR) {
            it.name.name
        }
        println("${playersName.replaceFirst(",", "와")}에게 ${Players.START_CARD_COUNT}장의 카드를 나누어 주었습니다.")
        players.getPlayers().forEach {
            showPlayerCards(it)
        }
        println()
    }

    fun showPlayerCards(player: Player) {
        println(getPlayerCardInformation(player))
    }

    private fun getPlayerCardInformation(player: Player) = "${player.name.name}카드: ${getCardsNames(player.cards)}"

    fun showGameResult(players: Players) {
        players.getPlayers().forEach {
            showDealerGetMoreCardState(it)
            println("${getPlayerCardInformation(it)} - 결과: ${it.getScore()}")
        }
        showFinalResults(players)
    }

    private fun showDealerGetMoreCardState(it: Player) {
        if (it is Dealer) {
            val dealerState = if (it.hasGetMoreCard) "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n" else "\n딜러는 17이상이라 카드를 받지 않았습니다.\n"
            println(dealerState)
        }
    }

    private fun showFinalResults(players: Players) {
        println("## 최종 승패")
        players.getPlayers().forEach {
            showResultPlayer(it)
        }
    }

    private fun showResultPlayer(player: Player) {
        var result = when (player.gameResultState) {
            GameResultState.WIN -> "승"
            GameResultState.DRAW -> "무"
            GameResultState.LOSE -> "패"
        }
        if (player is Dealer) {
            result = makeDealerResult(player)
        }
        println("${player.name.name}: $result")
    }

    private fun makeDealerResult(dealer: Dealer): String {
        val winCount = dealer.getCountOfResult(GameResultState.LOSE)
        val drawCount = dealer.getCountOfResult(GameResultState.DRAW)
        val loseCount = dealer.getCountOfResult(GameResultState.WIN)
        val result = StringBuffer()
        if (winCount > ZERO_COUNT) {
            result.append("${winCount}승 ")
        }
        if (drawCount > ZERO_COUNT) {
            result.append("${drawCount}무 ")
        }
        if (loseCount > ZERO_COUNT) {
            result.append("${loseCount}패 ")
        }
        return result.toString()
    }

    private fun getCardsNames(cards: Cards): String {
        return cards.getCardsSortByScore().joinToString(SEPERATOR) {
            getCardName(it)
        }
    }

    private fun getCardName(card: Card): String {
        return when (card.cardNumber) {
            CardNumber.CARD_ACE -> "A"
            CardNumber.CARD_KING -> "K"
            CardNumber.CARD_QUEEN -> "Q"
            CardNumber.CARD_JACK -> "J"
            else -> card.cardNumber.score.toString()
        } + when (card.cardType) {
            CardType.SPADE -> "하트"
            CardType.CLOVER -> "클로버"
            CardType.HEART -> "하트"
            CardType.DIAMOND -> "다이아"
        }
    }
}
