package fixtures

import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.state.*

private const val NAME = "player"
private val HIT_CARDS = createUnderBlackjackCards()
private val BUST_CARDS = createBustCards()
private val BLACKJACK_CARDS = createBlackjackCards()

/**
 * Player, Players
 */
fun createStartedPlayer(
    name: String = NAME,
    cards: Cards = Cards()
): Player {
    return Player(name, Started(cards))
}

fun createHitPlayer(
    name: String = NAME,
    cards: Cards = HIT_CARDS
): Player {
    return Player(name, Hit(cards))
}

fun createBustPlayer(
    name: String = NAME,
    cards: Cards = BUST_CARDS
): Player {
    return Player(name, Bust(cards))
}

fun createBlackjackPlayer(
    name: String = NAME,
    cards: Cards = BLACKJACK_CARDS
): Player {
    return Player(name, Blackjack(cards))
}

fun createPlayers(vararg players: Player): Players {
    return Players(
        players.toList()
    )
}

/**
 * Dealer
 */
fun createStartedDealer(
    cards: Cards = Cards()
): Dealer {
    return Dealer(Started(cards))
}

fun createHitDealer(
    cards: Cards = HIT_CARDS
): Dealer {
    return Dealer(Hit(cards))
}

fun createBustDealer(
    cards: Cards = BUST_CARDS
): Dealer {
    return Dealer(Bust(cards))
}

fun createBlackjackDealer(
    cards: Cards = BLACKJACK_CARDS
): Dealer {
    return Dealer(Blackjack(cards))
}
