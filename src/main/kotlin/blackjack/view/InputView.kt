package blackjack.view

class InputView {
    fun printMessage(message: String) {
        println(message)
    }

    fun readInput(): String {
        return readln()
    }
}
