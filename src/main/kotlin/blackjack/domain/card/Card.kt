package blackjack.domain.card

import blackjack.domain.score.Score

data class Card(val cardNumber: CardNumber, val cardSymbol: CardSymbol) {
    fun toScore(): Score {
        return Score(cardNumber.value)
    }
}
