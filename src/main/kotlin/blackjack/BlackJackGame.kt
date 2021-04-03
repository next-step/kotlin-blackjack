package blackjack

import blackjack.domain.Score
import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckFactory
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames
import blackjack.domain.state.StateFactory
import blackjack.dto.GamerCardsDto
import blackjack.dto.PlayerDto
import blackjack.dto.ResultsDto
import blackjack.userinterface.Console
import blackjack.userinterface.UserInterface

fun main() {
    val game = BlackJackGame(Console)
    game.run()
}

class BlackJackGame(private val userInterface: UserInterface) {

    private val deck: Deck = DeckFactory.create()

    fun run() {
        val dealer = Dealer("딜러", StateFactory.init(deck.draw(), deck.draw()))

        val players = run {
            val userNames = PlayerNames(userInterface.inputPlayerNames())
            userNames.map { Player(it, StateFactory.init(deck.draw(), deck.draw())) }
        }

        userInterface.outputGamerCards(GamerCardsDto(dealer, players))
        players.forEach { takeCardsIfNecessary(it) }

        takeCardsIfNeccessary(dealer)

        userInterface.outputGameResult(ResultsDto(dealer, players.map { Pair(it, it.matchResult(dealer)) }))
    }

    private tailrec fun takeCardsIfNeccessary(dealer: Dealer) {
        if (!dealer.isTakeable()) {
            return
        }

        dealer.takeCard(deck.draw())
        userInterface.outputDealerTaken(Score.DEALER_TAKEABLE_LIMIT.value)
        takeCardsIfNeccessary(dealer)
    }

    private tailrec fun takeCardsIfNecessary(player: Player) {
        if (!player.isTakeable()) {
            return
        }

        player.stayIfNotWantDraw(userInterface.inputCardTakenWhether(player.name))

        if (!player.isTakeable()) {
            return
        }

        player.takeCard(deck.draw())
        userInterface.outputCurrentCards(PlayerDto(player))
        takeCardsIfNecessary(player)
    }
}
