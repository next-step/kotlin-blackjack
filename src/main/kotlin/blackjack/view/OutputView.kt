package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.player.BlackJackPlayer
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object OutputView {

    private const val SEPERATOR = ", "

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

    fun showPlayerCards(player: BlackJackPlayer) {
        println(getPlayerCardInformation(player))
    }

    private fun getPlayerCardInformation(player: BlackJackPlayer) = "${player.name.name}카드: ${getCardsNames(player.cards)}"

    fun showGameResult(players: Players) {
        showDealerGetMoreCardState(players.getDealer())
        players.getPlayers().forEach {
            println("${getPlayerCardInformation(it)} - 결과: ${it.getScore()}")
        }
        showFinalResults(players)
    }

    private fun showDealerGetMoreCardState(dealer: Dealer) {
        val dealerState = if (dealer.hasGetMoreCard) "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n" else "\n딜러는 17이상이라 카드를 받지 않았습니다.\n"
        println(dealerState)
    }

    private fun showFinalResults(players: Players) {
        println()
        println("## 최종 수익")
        players.getPlayers().forEach {
            showResultPlayer(it)
        }
    }

    private fun showResultPlayer(player: BlackJackPlayer) {
        val result = when (player) {
            is Player -> player.finalIncome
            is Dealer -> player.earnMoney
            else -> return
        }

        println("${player.name.name}: ${result.money}")
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
