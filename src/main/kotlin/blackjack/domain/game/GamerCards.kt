package blackjack.domain.game

import blackjack.domain.card.Cards
import blackjack.domain.gamer.PlayerCards

data class GamerCards(
    val dealerCards: Cards,
    val allPlayerCards: List<PlayerCards>,
)
