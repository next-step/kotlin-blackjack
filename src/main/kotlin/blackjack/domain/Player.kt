package blackjack.domain

import blackjack.service.PlayerCardAdditionDecider
import blackjack.service.ResultCalculator
import blackjack.view.input.ConsoleInputView.Companion.WRONG_PLAYER_NAME_MESSAGE

data class Player(val name: String) {
    val cardsHandler = PlayerCardsHandler(PlayerCardAdditionDecider(), ResultCalculator())

    init {
        require(name.isNotBlank()) { WRONG_PLAYER_NAME_MESSAGE }
    }
}
