package blackjack.view

import blackjack.BlackjackResult
import blackjack.Dealer
import blackjack.Player
import blackjack.card.Card
import blackjack.card.CardType

object OutputView {

    private fun CardType.getCardName(): String = when (this) {
        CardType.SPADE -> "스페이드"
        CardType.HEART -> "하트"
        CardType.DIAMOND -> "다이아몬드"
        CardType.CLOVER -> "클로버"
    }

    fun showPlayersCard(players: List<Player>, dealer: Dealer) {
        println("${dealer.name}와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        showDealerCard(dealer)
        players.forEach(::showCard)
        println()
    }

    fun showCard(player: Player) {
        println("${player.name}카드: ${getCardNames(player.cards)}")
    }

    fun showDealerCard(dealer: Dealer) {
        println("${dealer.name}: ${getCardNames(dealer.cards)}")
    }

    fun showDealerMoreCard(dealer: Dealer) {
        println()
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun showResult(players: List<Player>) {
        players.forEach { println("${it.name}카드: ${getCardNames(it.cards)} - 결과: ${it.getTotalSum()}") }
    }

    fun showResultWinAndLose(results: List<BlackjackResult>) {
        println()
        println("## 최종 승패")
        results.forEach { println("${it.name}: ${if (it.isWinner) "승" else "패"}") }
    }

    private fun getCardNames(cards: List<Card>): String {
        return cards.joinToString { "${it.cardValue.desc}${it.cardType.getCardName()}" }
    }
}
