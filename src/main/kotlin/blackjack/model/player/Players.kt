package blackjack.model.player

import blackjack.model.card.CardDeck
import blackjack.model.status.PlayerStatus

class Players(private val players: List<Player>) {
    fun getNames(): String {
        return players.joinToString(separator = ",") { it.name }
    }

    fun drawCard(cardDeck: CardDeck) {
        players.forEach {
            it.drawCard(cardDeck.draw())
        }
    }

    fun progressTurn(cardDeck: CardDeck) {
        players.forEach { it.progressTurnForEach(cardDeck) }
    }

    fun checkGameDone(): Boolean {
        return players.none { it.getStatus() == PlayerStatus.PLAYING }
    }

    fun checkPrize() {
        val dealer = players.filterIsInstance<Dealer>()[0]
        dealer.checkPrize(players)
    }

    fun getDrawCardResults(): List<String> {
        return players.map { "${it.name}카드:${it.getDisplayCards()}" }
    }

    fun getPointResults(): List<String> {
        return players.map { "${it.name}카드: ${it.getDisplayCards()} - 결과: ${it.getBlackJackPoint()}" }
    }

    fun getPrize(): List<String> {
        return players.map { "${it.name}:${it.money.money}" }
    }
}
