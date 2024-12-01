package blackjack

import blackjack.deck.Deck
import blackjack.machine.BlackJackMachine

fun main() {
    BlackJackMachine(Deck()).play()
}
