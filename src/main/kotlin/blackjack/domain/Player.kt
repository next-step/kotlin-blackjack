package blackjack.domain

import blackjack.service.ParticipantCardsHandler
import blackjack.view.input.ConsoleInputView.Companion.WRONG_PLAYER_NAME_MESSAGE

data class Player(val name: String, val cardsHandler: ParticipantCardsHandler) {
    init {
        require(name.isNotBlank()) { WRONG_PLAYER_NAME_MESSAGE }
    }
}
