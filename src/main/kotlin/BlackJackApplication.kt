import domain.Players
import view.InputView
import view.OutputView

fun main() {
    OutputView.printPlayerNames()
    val inputPlayerNames = InputView.inputPlayerNames()
    val players = Players.of(inputPlayerNames)
}
