package blackjack.view

import blackjack.model.Player

interface InputView {
    val readPlayers: () -> String
    val readPickAnswer: (Player) -> Boolean
}
