package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Gamer
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

class Casino(private val inputView: InputView, private val resultView: ResultView) {

    val dealer = Dealer()
    lateinit var gamers: List<Gamer>
        private set
    private val game = Game()

    fun prepare() {
        gamers = inputView.inputNames()
    }

    fun drawTwoCards() {
        repeat(gamers.size) {
            game.drawTwoCards(gamers[it])
        }
        game.drawTwoCards(dealer)
    }

    fun names(): String = gamers.joinToString(", ") { player -> player.name }

    fun showPlayers() {
        resultView.showPlayers(this)
    }

    fun showResult(casino: Casino) {
        resultView.showResult(casino)
    }

    fun showReport(casino: Casino) {
        resultView.showReport(casino)
    }

    fun relay() {
        var index = 0
        do {
            val player = gamers[index]

            val next = ask(player)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < gamers.size)

        if (dealer.canDraw()) {
            resultView.showPlayer(dealer)
            game.draw(dealer)
        }
    }

    private fun ask(player: Player): Boolean {
        val skip = question(player)
        if (skip) return true

        game.draw(player)

        if (player.canDraw().not()) return true

        resultView.showPlayer(player)

        return ask(player)
    }

    private fun question(player: Player): Boolean {
        val answer = inputView.ask(player)
        if (answer.isBlank()) return true
        if (answer == No) return true

        return false
    }

    companion object {
        private const val No = "n"
    }
}
