package blackjack.view

import blackjack.domain.BlackJackTable
import blackjack.domain.Card
import blackjack.domain.Player

object OutputView {

    const val SEPERATOR = ", "
    fun showPlayerSet(players: List<Player>) {
        val playersName = players.joinToString(SEPERATOR) {
            it.name
        }
        println("${playersName}에게 ${BlackJackTable.START_CARD_SIZE}장의 카드를 나누어 주었습니다.")
        players.forEach {
            showPlayerCards(it)
        }
    }

    fun showPlayerCards(player: Player) {
        println(playerCardInfomation(player))
    }

    private fun playerCardInfomation(player: Player) = "${player.name}카드 : ${getCardsNames(player.cards)}"

    fun showGameResult(player: Player) {
        println("${playerCardInfomation(player)} - 결과: ${player.getCardScore()}")
    }

    private fun getCardsNames(cards: List<Card>): String {
        return cards.sortedBy { it.cardNumber.number }.joinToString(SEPERATOR) {
            it.cardNumber.number + it.cardType.type
        }
    }
}
