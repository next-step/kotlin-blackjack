package blackjack.model

import blackjack.model.score.Score
import blackjack.model.trump.Cards

interface Rule {
    fun getScore(cards: Cards): Score
}
