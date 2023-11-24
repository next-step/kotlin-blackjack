package blackjack.model

object IdGenerator {
    private var currentLastId = -1;
    val holderId
        get() = getId()

    private fun getId(): Int {
        currentLastId += 1
        return currentLastId;
    }
}
