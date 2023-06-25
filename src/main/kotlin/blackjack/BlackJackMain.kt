package blackjack

import blackjack.card.score.BlackJackScoringStrategy

fun main() {
    BlackjackGame(Dealer(), BlackJackScoringStrategy()).start()
}
