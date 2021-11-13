package blackjack

import blackjack.domain.*
import blackjack.view.ConsoleInputView
import blackjack.view.ConsoleOutputView
import blackjack.view.dto.PlayerDto
import blackjack.view.dto.PlayersDto

fun main() {
    val names: List<PlayerName> = PlayerName.from(ConsoleInputView.getNames())
    val players = Players(names.map { Player(it) })

    val deck = Deck.create()

    players.drawAtGameStart(deck)
    ConsoleOutputView.giveFirstTwoCards(PlayersDto(players))

    players.forEach {
        while (it.canHit() && it.hitIfWant(deck).success) {
            ConsoleOutputView.printPlayer(PlayerDto(it))
        }
    }
    ConsoleOutputView.printResult(PlayersDto(players))
}

private fun Player.hitIfWant(deck: Deck): Player.DrawResult {
    val answer = PlayerAnswer.from(ConsoleInputView.getAnswer(name.value))
    return draw(deck, answer)
}
