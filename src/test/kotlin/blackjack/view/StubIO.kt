package blackjack.view

class StubIO : IO {
    private val inputs = mutableListOf<String>()
    private val outputs = mutableListOf<String>()

    val printed: List<String>
        get() = outputs

    fun addInput(input: String) {
        inputs.add(input)
    }

    override fun read(): String = inputs.removeAt(0)

    override fun print(text: String) {
        outputs.add(text)
    }
}
