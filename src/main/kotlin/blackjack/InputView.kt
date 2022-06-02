package blackjack

object InputView {
    fun getUserNames(): List<String> {
        return readln().split(",")
    }
}