package blackjack.model.player

import blackjack.model.card.CardDeck

class Players(private val players: List<Player>) {
    fun getNames(): String {
        return players.joinToString(separator = ",") { it.name }
    }

    fun drawCard(cardDeck: CardDeck) {
        players.forEach { it.drawCard(cardDeck.draw()) }
    }

    fun progressTurn(cardDeck: CardDeck) {
        players.forEach { it.progressTurnForEach(cardDeck) }
    }

    fun checkWinOrLose() {
        players.forEach { it.checkWinOrLose(players) }
    }

    fun getDrawCardResults(): List<String> {
        return players.map { "${it.name}카드:${it.getDisplayCards()}" }
    }

    fun getPointResults(): List<String> {
        return players.map { "${it.name}카드: ${it.getDisplayCards()} - 결과: ${it.getTotalPointForBlackJack()}" }
    }

    fun getWinnerResults(): List<String> {
        return players.map { "${it.name}:${it.winLoseResult}" }
    }
}
