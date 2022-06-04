package blackjack.view

class Console : IO {
    override fun read(): String {
        return readLine() ?: ""
    }

    override fun print(text: String) {
        println(text)
    }
}
