package blackjack.view

import blackjack.model.Player

interface InputView {
    val readPlayers: () -> String
    val readPlayerAnswer: (Player) -> String
}
