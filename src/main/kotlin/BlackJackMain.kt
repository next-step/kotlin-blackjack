import view.InputView

fun main(args: Array<String>) {
    val inputView = InputView()
    val players = inputView.inputPlayers()
    println(players)
}