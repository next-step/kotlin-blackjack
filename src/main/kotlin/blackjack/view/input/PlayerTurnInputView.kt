package blackjack.view.input

object PlayerTurnInputView {
    private const val CONTINUE_DRAW = "y"

    fun continueDraw(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        return readln() == CONTINUE_DRAW
    }
}
