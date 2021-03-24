package blackjack.view

import blackjack.Player
import blackjack.card.Card
import blackjack.const.BlackjackConst.CARD_NAME_MAP

object OutputView {

    fun showPlayersCard(players: List<Player>) {
        players.forEach(::showCard)
    }

    fun showCard(player: Player) {
        println("${player.name}카드: ${getCardNames(player.cards)}")
    }

    fun showResult(players: List<Player>) {
        players.forEach { println("${it.name}카드: ${getCardNames(it.cards)} - 결과: ${it.getTotalSum()}") }
    }

    private fun getCardNames(cards: List<Card>): String {
        return cards.joinToString { "${it.cardValue.desc}${CARD_NAME_MAP[it.cardType]}" }
    }
}
