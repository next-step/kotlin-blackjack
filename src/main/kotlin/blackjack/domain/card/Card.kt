package blackjack.domain.card

data class Card(val character: CardCharacter, val shape: CardShape) {
    val cardCharacterDisplayName
        get() = character.displayName
    val cardShapeDisplayName
        get() = shape.displayName

    fun isAce(): Boolean = character == CardCharacter.ACE
}
