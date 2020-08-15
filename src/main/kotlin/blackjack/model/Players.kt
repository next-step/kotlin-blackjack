package blackjack.model

import blackjack.model.card.CardDeck

class Players(
    names: List<String>
) {
    val players = names.map(::Player)

    fun gameBatting(cardDeck: CardDeck) =
        players.forEach { player ->
            player.gameBatting(cardDeck.popTwoCard())
        }

    override fun toString() = players.joinToString("\n", transform = Player::toString)
}
