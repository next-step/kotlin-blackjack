package domain.player

import domain.card.Card
import domain.card.Cards

sealed interface PlayerState {
    val cards: Cards

    val canReceiveMoreCard: Boolean

    class Hit(override val cards: Cards) : PlayerState {
        fun hit(card: Card): PlayerState {
            cards.add(card)
            return PlayerState(cards)
        }

        override val canReceiveMoreCard: Boolean = true
    }

    class Bust(override val cards: Cards) : PlayerState {
        override val canReceiveMoreCard: Boolean = false
    }

    class Stay(override val cards: Cards) : PlayerState {
        override val canReceiveMoreCard: Boolean = false
    }

    class BlackJack(override val cards: Cards) : PlayerState {
        override val canReceiveMoreCard: Boolean = false
    }
}

fun PlayerState(cards: Cards): PlayerState {
    val result = cards.result()
    return when {
        result == Cards.BLACKJACK_POINT -> PlayerState.BlackJack(cards)
        result < Cards.BLACKJACK_POINT -> PlayerState.Hit(cards)
        else -> PlayerState.Bust(cards)
    }
}
