package blackjack.domain.holder

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.state.Hands
import blackjack.domain.value.Point
import blackjack.view.dto.PlayerGameResult

private val DEALER_MIN_POINT = Point(17)

class Dealer {
    private val player = Player("딜러")
    val name: String
        get() = player.name
    val hands: Hands
        get() = player.hands

    fun firstTurn(cards: Set<Card>): Dealer {
        player.firstTurn(cards)
        return this
    }

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

    fun firstCard(): Set<Card> = player.firstCard()
    fun result(players: List<Player>): List<PlayerGameResult> {
        val playerGameResults = players.map { PlayerGameResult(it, it.flip(this)) }
        val dealerBettingAmount = -playerGameResults.sumOf { it.winningAmount }
        return listOf(PlayerGameResult(this, dealerBettingAmount)) + playerGameResults
    }
}
