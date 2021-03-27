package blackjack.model

import blackjack.model.trump.Cards

interface Rule {
    fun getScore(cards: Cards): Score
}
