package blackjack

import domain.Score
import domain.State
import domain.card.Cards

fun DealerState(cards: Cards): State {
    val result = cards.score()
    return when {
        Score.isBust(result) -> State.Bust
        Score.isBlackJack(result) -> State.BlackJack
        Score.isDealerStayOn(result) -> State.Stay
        else -> State.Hit
    }
}

fun PlayerState(cards: Cards): State {
    val result = cards.score()
    return when {
        Score.isBlackJack(result) -> State.BlackJack
        Score.isBust(result) -> State.Bust
        else -> State.Hit
    }
}
