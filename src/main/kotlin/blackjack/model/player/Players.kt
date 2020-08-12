package blackjack.model.player

import blackjack.model.card.CardDeck

class Players(private val players: List<Player>) {
    fun getNames(): String {
        return players.joinToString(separator = ",") { it.name }
    }

    fun drawCard(cardDeck: CardDeck) {
        doAction { it.drawCard(cardDeck.pick()) }
    }

    fun progressTurn(cardDeck: CardDeck) {
        doAction { it.progressTurnForEach(cardDeck) }
    }

    fun checkWinOrLose() {
        doAction { it.checkWinOrLose(players) }
    }

    fun getDrawCardResults(): List<String> {
        return getResult { "${it.name}카드:${it.getDisplayCards()}" }
    }

    fun getPointResults(): List<String> {
        return getResult { "${it.name}카드: ${it.getDisplayCards()} - 결과: ${it.getTotalPointForBlackJack()}" }
    }

    fun getWinnerResults(): List<String> {
        return getResult { "${it.name}:${it.winLoseResult}" }
    }

    private fun doAction(getAction: (Player) -> Unit) {
        for (player in players) {
            getAction(player)
        }
    }

    private fun getResult(getDetails: (Player) -> String): List<String> {
        val results = mutableListOf<String>()
        for (player in players) {
            results.add(getDetails(player))
        }
        return results
    }
}
