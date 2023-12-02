package blackjack.domain.card

data class Card(val character: CardCharacter, val shape: CardShape) {
    fun isAce(): Boolean = character == CardCharacter.ACE
}
