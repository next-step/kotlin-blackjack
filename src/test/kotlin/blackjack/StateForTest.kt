package blackjack

import domain.Score
import domain.State
import domain.card.Cards

fun DealerState(cards: Cards): State {
    val result = cards.score()
    return when {
        result > Score.BLACKJACK -> State.Bust
        result == Score.BLACKJACK -> State.BlackJack
        result > Score.DEALER_HIT_ON_MAX -> State.Stay
        else -> State.Hit
    }
}

fun PlayerState(cards: Cards): State {
    val result = cards.score()
    return when {
        result == Score.BLACKJACK -> State.BlackJack
        result < Score.BLACKJACK -> State.Hit
        else -> State.Bust
    }
}
