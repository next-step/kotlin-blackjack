package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Cards
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGame {
    fun play(cardDeck: CardDeck) {
        val players: List<Player> = getPlayers(cardDeck)
        printCurrentState(players)
        hitCard(players, cardDeck)
        printResult(players)
    }

    private fun getPlayers(cardDeck: CardDeck): List<Player> {
        ResultView.printMessage(ResultView.Message.REQUEST_PLAYERS)
        val names: List<String> = InputView.requestStringList()
        val initCards = List(INIT_HIT_COUNT) { cardDeck.draw() }

        return names.map { name -> Player(name, Cards(initCards)) }
    }

    private fun printCurrentState(players: List<Player>) {
        ResultView.newLine()
        ResultView.printHit(players.map { it.name })

        players.forEach { player ->
            val cards = toCardNames(player)

            ResultView.printState(player.name, cards)
        }

        ResultView.newLine()
    }

    private fun hitCard(players: List<Player>, cardDeck: CardDeck) {
        players.forEach { player ->
            hitCard(player, cardDeck)
        }

        ResultView.newLine()
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

            val cards = toCardNames(player)

            ResultView.printState(player.name, cards)
        }

        if (player.isBust()) {
            ResultView.printMessage(player.name, ResultView.Message.BUST)
            ResultView.newLine()
        }
    }

    private fun printResult(players: List<Player>) {
        players.forEach { player ->
            val cards = toCardNames(player)
            val point = player.point

            ResultView.printResult(player.name, cards, point)
        }
    }

    private fun toCardNames(player: Player): List<String> =
        player.cards.cardStack.map { card -> "${card.number.value}${card.suit.value}" }

    companion object {
        private const val INIT_HIT_COUNT = 2
    }
}

fun main() {
    BlackJackGame().play(CardDeck.shuffle())
}
