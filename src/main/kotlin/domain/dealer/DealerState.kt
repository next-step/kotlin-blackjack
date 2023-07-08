package domain.dealer

import domain.card.Card
import domain.card.Cards

sealed interface DealerState {
    val cards: Cards

    val canReceiveMoreCard: Boolean

    class Hit(override val cards: Cards) : DealerState {
        fun hit(card: Card): DealerState {
            cards.add(card)
            return DealerState(cards)
        }

        override val canReceiveMoreCard: Boolean = true
    }

    class Bust(override val cards: Cards) : DealerState {
        override val canReceiveMoreCard: Boolean = false
    }

    class Stay(override val cards: Cards) : DealerState {
        override val canReceiveMoreCard: Boolean = false
    }

    class BlackJack(override val cards: Cards) : DealerState {
        override val canReceiveMoreCard: Boolean = false
    }
}

fun DealerState(cards: Cards): DealerState {
    val result = cards.result()
    return when {
        result > Cards.BLACKJACK_POINT -> DealerState.Bust(cards)
        result == Cards.BLACKJACK_POINT -> DealerState.BlackJack(cards)
        result > Dealer.DEALER_MAX_POINT -> DealerState.Stay(cards)
        else -> DealerState.Hit(cards)
    }
}
