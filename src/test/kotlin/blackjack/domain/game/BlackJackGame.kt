package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.gamer.PlayerNames
import blackjack.domain.shuffle.CardShuffler
import blackjack.domain.shuffle.Shuffler

fun blackJackGame(
    playerNames: PlayerNames,
    shuffler: Shuffler<Card> = CardShuffler(),
): BlackJackGame {
    return BlackJackGame(
        shuffler = shuffler,
        playerNames = playerNames,
    )
}
