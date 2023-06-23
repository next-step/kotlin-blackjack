package blackjack.view

import blackjack.domain.*

object OutputView {

    private const val SEPERATOR = ", "

    fun showPlayerSet(players: Players) {
        val playersName = players.players.joinToString(SEPERATOR) {
            it.name.name
        }
        println("${playersName}에게 ${BlackJackTable.START_CARD_COUNT}장의 카드를 나누어 주었습니다.")
        players.players.forEach {
            showPlayerCards(it)
        }
        println()
    }

    fun showPlayerCards(player: Player) {
        println(getPlayerCardInfomation(player))
    }

    private fun getPlayerCardInfomation(player: Player) = "${player.name.name}카드 : ${getCardsNames(player.cards)}"

    fun showGameResult(player: Player) {
        println("${getPlayerCardInfomation(player)} - 결과: ${player.getCardScore()}")
    }

    private fun getCardsNames(cards: Cards): String {
        return cards.cards.sortedBy { it.cardNumber.score }.joinToString(SEPERATOR) {
            getCardName(it)
        }
    }

    private fun getCardName(card: Card): String {
        return when(card.cardNumber) {
            CardNumber.CARD_ACE -> "A"
            CardNumber.CARD_KING -> "K"
            CardNumber.CARD_QUEEN -> "Q"
            CardNumber.CARD_JACK -> "J"
            else -> card.cardNumber.score.toString()
        } + when(card.cardType) {
            CardType.SPADE -> "하트"
            CardType.CLOVER -> "클로버"
            CardType.HEART -> "하트"
            CardType.DIAMOND -> "다이아"
        }
    }
}
