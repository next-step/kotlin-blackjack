package blackjack

import domain.State
import domain.card.Cards
import domain.gamer.dealer.Dealer

fun DealerState(cards: Cards): State {
    val result = cards.score()
    return when {
        result > Cards.BLACKJACK_POINT -> State.Bust
        result == Cards.BLACKJACK_POINT -> State.BlackJack
        result > Dealer.DEALER_MAX_POINT -> State.Stay
        else -> State.Hit
    }
}

fun PlayerState(cards: Cards): State {
    val result = cards.score()
    return when {
        result == Cards.BLACKJACK_POINT -> State.BlackJack
        result < Cards.BLACKJACK_POINT -> State.Hit
        else -> State.Bust
    }
}
