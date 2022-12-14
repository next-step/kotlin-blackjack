package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class BlackjackController {
    private val inputView = ConsoleInput
    private val outputView = ConsoleOutput

    fun playGame() {
        val game = Game(inputView.inputPlayers())
        val dealer = game.getDealer()
        val players = game.initialCard()
        outputView.printInitialCards(dealer, players)

        val playersCards = scratchPlayers(players, game.deck)
        val dealerCards = scratchDealer(dealer, game.deck)

        outputView.printResultCards(dealerCards, playersCards)

        println("## 최종 승패")
        val resultDealer: Map<GameResult, Int> =
            playersCards.list.groupingBy { dealer.compareTo(it) }.eachCount()
        println("딜러:${resultDealer.toSortedMap().map { " ${it.value}${it.key.label}" }}")

        playersCards.list.map { println("${it.name.value}: ${it.compareTo(dealer).label}") }
    }

    private fun scratchDealer(dealer: Player, deck: Deck): Player {
        return if (dealer.canHit()) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
            dealer.hit(deck)
        } else {
            println("딜러는 17이상이라 카드를 받지 않았습니다.\n")
            dealer
        }
    }

    private fun scratchPlayers(players: Players, deck: Deck): Players {
        val result = mutableListOf<Player>()
        players.list.map {
            var player = it
            while (player.canHit() && ConsoleInput.inputScratch(player)) {
                player = player.hit(deck)
                ConsoleOutput.printPlayerCards(player)
            }
            result.add(player)
        }

        println()
        return Players(result)
    }
}

fun main() {
    BlackjackController().playGame()
}
