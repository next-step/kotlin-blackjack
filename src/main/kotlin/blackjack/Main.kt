package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.game.Money
import blackjack.domain.player.*
import blackjack.view.ConsoleInputView
import blackjack.view.ConsoleOutputView
import blackjack.view.dto.BlackJackResultDto
import blackjack.view.dto.CardDto
import blackjack.view.dto.GamerDto
import blackjack.view.dto.GamersDto

fun main() {
    val names: List<PlayerName> = PlayerName.from(ConsoleInputView.getNames())
    val betMoneys: List<Money> = names.map {
        Money(ConsoleInputView.getBetMoney(it))
    }
    val playerAfterHit = AfterHitWhileCallback { ConsoleOutputView.printGamer(GamerDto(it)) }
    val playerDtos = PlayerDto.from(names, betMoneys)
    val players = playerDtos.map { it.toPlayer(playerAfterHit) }

    val gamers = Gamers.from(
        players = players,
        dealerAfterHit = { ConsoleOutputView.printDealerHit() },
    )
    val deck = Deck.create()

    gamers.hitAtGameStart(deck)
    ConsoleOutputView.giveFirstTwoCards(getGamersAtFirst(gamers))

    gamers.hitWhileWant(deck) {
        PlayerAnswer.from(ConsoleInputView.getAnswer(it.name.value))
    }

    ConsoleOutputView.printResult(GamersDto(gamers))
    ConsoleOutputView.printBlackJackResult(BlackJackResultDto(gamers.getResult()))
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
    return gamer.firstOpenCards.map { CardDto(it) }
}
