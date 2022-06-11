package blackjack.view.output

class StubOutput : Output {
    var message: String = ""

    override fun print(message: String) {
        this.message = message
    }
}
