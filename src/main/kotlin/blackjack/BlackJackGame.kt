package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Participant
import blackjack.domain.participantion.Player
import blackjack.domain.participantion.Players
import blackjack.domain.result.GameResult
import blackjack.domain.result.Winners
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGame {
    fun play(cardDeck: CardDeck) {
        val players = getPlayers(cardDeck)
        val dealer = Dealer(cardDeck)
        val participants = listOf(dealer) + players

        printCurrentState(participants)
        hitCard(dealer, players, cardDeck)

        participants.forEach(ResultView::printResult)
        printWinner(dealer, players)
    }

    private fun printWinner(dealer: Dealer, players: List<Player>) {
        val winners = Winners.from(dealer, players)

        ResultView.printMessage(ResultView.Message.WINNER)
        ResultView.printRank(dealer.name, GameResult.from(dealer, players))

        players.forEach { player ->
            ResultView.printWinner(player.name, winners.exist(player.name))
        }
    }

    private fun getPlayers(cardDeck: CardDeck): List<Player> {
        ResultView.printMessage(ResultView.Message.REQUEST_PLAYERS)
        val names: List<String> = InputView.requestStringList()

        return Players(names, cardDeck).players
    }

    private fun printCurrentState(participants: List<Participant>) {
        ResultView.newLine()
        ResultView.printHit(participants.map { it.name })

        participants.forEach(ResultView::printState)
        ResultView.newLine()
    }

    private fun hitCard(dealer: Dealer, players: List<Player>, cardDeck: CardDeck) {
        players.forEach { player ->
            hitCard(player, cardDeck)
        }

        ResultView.newLine()

        hitCard(dealer, cardDeck)
    }

    private fun hitCard(dealer: Dealer, cardDeck: CardDeck) {
        while (dealer.isHittable()) {
            ResultView.printMessage(ResultView.Message.DEALER_HIT)
            val card = cardDeck.draw()
            dealer.hit(card)
        }
    }

    private fun hitCard(player: Player, cardDeck: CardDeck) {
        while (!player.isBust()) {
            ResultView.printMessage(player.name, ResultView.Message.REQUEST_HIT)
            val isHit = InputView.requestConfirm()

            if (!isHit) {
                break
            }

            val card = cardDeck.draw()
            player.hit(card)

            ResultView.printState(player)
        }

        if (player.isBust()) {
            ResultView.printMessage(player.name, ResultView.Message.BUST)
            ResultView.newLine()
        }
    }
}

fun main() {
    BlackJackGame().play(CardDeck.shuffle())
}
