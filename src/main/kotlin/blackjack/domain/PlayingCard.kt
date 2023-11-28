package blackjack.domain


class PlayingCard {
    val cards: Cards = Cards(
        CardShape.values()
            .flatMap { shape -> CardCharacter.values().map { cardCharacter -> Card(cardCharacter, shape) } }
            .toMutableSet()
    )
}
