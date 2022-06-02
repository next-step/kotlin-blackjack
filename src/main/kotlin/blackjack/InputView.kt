package blackjack

object InputView {
    fun getUserNames(): List<String> {
        return readln().split(",")
    }

    fun getYorN(): String {
        return readln()
    }
}