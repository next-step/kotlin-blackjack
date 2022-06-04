package blackjack.domain

import blackjack.domain.card.Deck

data class Participants(
    val players: List<Participant>,
    private val deck: Deck
) {
    fun giveCardFirstTime() {
        players.forEach { participant ->
            repeat(INITIAL_GIVE_CARD_COUNT) { participant.addCard() }
        }
    }

    companion object {
        fun of(playerNames: List<String>, deck: Deck): Participants {
            return Participants(
                playerNames.map { Participant.of(it, deck) }, deck
            )
        }

        private const val INITIAL_GIVE_CARD_COUNT = 2
        private const val ADD_CARD_COUNT = 1
    }
}