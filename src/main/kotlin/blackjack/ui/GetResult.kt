package blackjack.ui

import blackjack.entity.Card
import blackjack.entity.Dealer
import blackjack.entity.Person
import blackjack.entity.Player

object GetResult {

    fun informGameStart(names: List<String>) {
        println()
        println("딜러와 " + names.toString() + "에게 " + "2장을 나누었습니다.")
    }

    fun printAllStatus(players: List<Person>, dealer: Dealer){
        printPlayerStatus(dealer)
        players.map { player: Person -> printPlayerStatus(player) }
        println()
    }

    fun printPlayerStatus(player: Person) {
        val cardStatus = getCardStatus(player)
        println(player.name + "카드: " + cardStatus)
    }

    fun printAllStatusWithResult(players: List<Person>, dealer:Person){
        printPlayerStatusWithResult(dealer)
        players.map { player: Person -> printPlayerStatusWithResult(player) }
    }

    fun printPlayerStatusWithResult(player: Person) {
        val cardStatus = getCardStatus(player)
        println(player.name + "카드: " + cardStatus + " - 결과: " + player.getWalletSum())
    }

    fun getCardStatus(player: Person): String {
        return player.getWalletCards().joinToString(JOIN_SEPARATOR) { card: Card -> card.getNumberValue().toString() + card.getShapeValue() }
    }

    fun addDealerSingleCard(){
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    private const val JOIN_SEPARATOR = ", "
}
