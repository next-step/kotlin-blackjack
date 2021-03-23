package blackjack

import blackjack.domain.Card
import blackjack.domain.CardPlayer
import blackjack.domain.Symbol
import org.assertj.core.api.Assertions

fun playerWith(name: String, vararg cardNames: String, assert: (CardPlayer) -> Unit = {}): CardPlayer {
    val cards = cardNames.map { Card(it, Symbol.values().random()) }.toList()
    return CardPlayer.Player(name, cards)
        .also {
            assert(it)
        }
}

fun dealerWith(vararg cardNames: String, assert: (CardPlayer) -> Unit): CardPlayer.Dealer {
    return CardPlayer.Dealer(playerWith("dealer", *cardNames, assert = assert))
}

infix fun CardPlayer.scoreIs(score: Int) {
    Assertions.assertThat(score()).isEqualTo(score)
}
