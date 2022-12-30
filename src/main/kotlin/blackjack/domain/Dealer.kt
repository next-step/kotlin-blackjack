package blackjack.domain

import blackjack.view.dto.PlayerGameResult

private val DEALER_MIN_POINT = Point(17)

class Dealer(playingCards: PlayingCards = PlayingCards()) {
    private val player = Player("딜러", playingCards)
    val name: String
        get() = player.name
    val playingCards: PlayingCards
        get() = player.playingCards

    fun hitUntil(deck: CardDeck): Int {
        var count = 0
        while (cardPoint() < DEALER_MIN_POINT && !player.bust()) {
            player.addCard(deck.deal())
            count++
        }
        return count
    }

    fun cardPoint(): Point = player.cardPoint()
    fun blackJack(): Boolean = player.blackJack()
    fun bust(): Boolean = player.bust()

    fun addCard(cards: Set<Card>) {
        player.addCard(cards)
    }

    fun firstCard(): Set<Card> = player.firstCard()
    fun result(players: List<Player>): List<PlayerGameResult> {
        val playerGameResults = players.map { PlayerGameResult(it, it.flip(this)) }
        val dealerBettingAmount = -playerGameResults.sumOf { it.winningAmount }
        return listOf(PlayerGameResult(this, dealerBettingAmount)) + playerGameResults
    }
}
