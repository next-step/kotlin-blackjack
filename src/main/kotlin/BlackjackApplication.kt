import presentation.InputView

fun main(args: Array<String>) {
    println("Welcome to the Blackjack Game!")
    val players = InputView.inputPlayers()
    println(players)
}