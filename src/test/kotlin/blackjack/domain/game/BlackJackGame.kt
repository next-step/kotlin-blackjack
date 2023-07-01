package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.PlayerInitProperty
import blackjack.domain.shuffle.CardShuffler
import blackjack.domain.shuffle.Shuffler

fun blackJackGame(
    playerInitProperties: List<PlayerInitProperty>,
    shuffler: Shuffler<Card> = CardShuffler(),
): BlackJackGame {
    return BlackJackGame(
        shuffler = shuffler,
        gamers = Gamers.create(playerInitProperties),
    )
}
