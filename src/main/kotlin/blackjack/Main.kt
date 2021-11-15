package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.*
import blackjack.view.ConsoleInputView
import blackjack.view.ConsoleOutputView
import blackjack.view.dto.CardDto
import blackjack.view.dto.GamerDto
import blackjack.view.dto.GamersDto

fun main() {
    val names: List<PlayerName> = PlayerName.from(ConsoleInputView.getNames())
    val gamers = Gamers.from(names)

    val deck = Deck.create()

    gamers.hitAtGameStart(deck)
    ConsoleOutputView.giveFirstTwoCards(getGamersAtFirst(gamers))

    gamers.forEach {
        while (it.canHit() && it.hitIfYes(deck).success) {
            ConsoleOutputView.printGamer(GamerDto(it))
        }
    }
    while(gamers.dealer.canHit()) {
        gamers.dealer.hit(deck)
        ConsoleOutputView.printDealerHit()
    }
    ConsoleOutputView.printResult(GamersDto(gamers))
}

private fun getGamersAtFirst(gamers: Gamers): GamersDto {
    val gamerDtos = gamers.gamers.map {
        GamerDto(
            name = it.name.value,
            cards = getFirstOpenCards(it),
            score = it.score.value,
        )
    }
    return GamersDto(gamerDtos)
}

private fun getFirstOpenCards(gamer: Gamer): List<CardDto> {
    return gamer.firstOpenCards().map { CardDto(it) }
}

private fun Player.hitIfYes(deck: Deck): Player.DrawResult {
    val answer = PlayerAnswer.from(ConsoleInputView.getAnswer(name.value))
    return hitIfYes(deck, answer)
}
