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

    gamers.players.forEach {
        hitWhileWant(it, deck)
    }
    hitUntilImpossible(gamers.dealer, deck)

    ConsoleOutputView.printResult(GamersDto(gamers))
}

private fun hitWhileWant(player: Player, deck: Deck) {
    while (player.canHit() && player.wantHit()) {
        player.hit(deck)
        ConsoleOutputView.printGamer(GamerDto(player))
    }
}

private fun Player.wantHit(): Boolean {
    return PlayerAnswer.from(ConsoleInputView.getAnswer(name.value)).hit
}

private fun hitUntilImpossible(dealer: Dealer, deck: Deck) {
    while (dealer.canHit()) {
        dealer.hit(deck)
        ConsoleOutputView.printDealerHit()
    }
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
